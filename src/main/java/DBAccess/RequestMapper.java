
package DBAccess;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.RequestObject;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Shed;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Meningen med denne klasse er at samle alle SQL til databasen baseret på Request
 */
public class RequestMapper {

    private static final String INSERT_INTO_REQUEST = "INSERT INTO `REQUEST` (`pricedefault`, `priceemployee`, `customer_id`, `carport_id`) VALUES (?,?,?,?)";
    private static final String INSERT_INTO_SHED = "INSERT INTO `SHED` (`heigth`, `width`, `length`, `shedcladding`) VALUES (?,?,?,?)";
    private static final String INSERT_INTO_CARPORT = "INSERT INTO `CARPORT` (`heigth`, `width`, `length`, `roofslopecelsius`, `roofcladding`, `shed_id`) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_INTO_CARPORT_HAS_MATERIALS = "INSERT INTO `CARPORT_HAS_MATERIALS` (`carport_id`, `materials_id`) VALUES (?,?)";

    private static final String GET_REQUESTID_BY_CUSTOMERID = "SELECT `id` FROM `REQUEST` WHERE `customer_id` = ?";
    private static final String GET_REQUESTID_BY_EMPLOYEE_NULL = "SELECT `id` FROM `REQUEST` WHERE `employee_id` IS NULL";
    private static final String GET_REQUESTID_BY_EMPLOYEE_NOTNULL = "SELECT `id` FROM `REQUEST` WHERE `employee_id` IS NOT NULL";
    private static final String GET_REQUESTDETAILS_BY_REQUESTID = "SELECT `REQUEST`.`id`, `requestdate`, `offerdate`, `paymentdate`, `pricedefault`, `priceemployee`, `customer_id`, `employee_id`, `CARPORT`.`id` AS `carport_id`, `CARPORT`.`heigth` AS `carport_heigth`, `CARPORT`.`width` AS `carport_width`, `CARPORT`.`length` AS `carport_length`, `roofslopecelsius`, `roofcladding`,\n"
            + "`SHED`.`id` AS `shed_id`, ifnull(`SHED`.`heigth`, 0.0) AS `shed_heigth`, ifnull(`SHED`.`width`, 0.0) AS `shed_width`, ifnull(`SHED`.`length`, 0.0) AS `shed_length`, ifnull(`shedcladding`,0) AS `shedcladding`\n"
            + "FROM `REQUEST`\n"
            + "LEFT JOIN `CARPORT` ON `REQUEST`.`carport_id` = `CARPORT`.`id`\n"
            + "LEFT JOIN `SHED` ON `SHED`.`id` = `CARPORT`.`shed_id`\n"
            + "WHERE `REQUEST`.`id` = ?";
    private static final String UPDATE_REQUEST_PAYMENT_BY_CUSTOMER = "UPDATE `REQUEST` SET `paymentdate` = current_timestamp() WHERE `id` = ?";

    /**
     * Meningen med denne metode er at lave et request til databasen med information fra de angivne parametre
     * @param customer
     * @param price
     * @param carport
     * @throws SystemException 
     */
    public static void createRequest(Customer customer, Price price, Carport carport) throws SystemException {
        try {
            Connection conn = DBConnector.connection();
            PreparedStatement shedPstmt = conn.prepareStatement(INSERT_INTO_SHED, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement carportPstmt = conn.prepareStatement(INSERT_INTO_CARPORT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement requestPstmt = conn.prepareStatement(INSERT_INTO_REQUEST);
            PreparedStatement billOfMaterialPstmt = conn.prepareStatement(INSERT_INTO_CARPORT_HAS_MATERIALS);
            ResultSet rsShed = null;
            int shedId = 0;
            int resultShed = 0;
            try {
                conn.setAutoCommit(false);
                if (carport.getShed() != null) {
                    shedPstmt.setDouble(1, carport.getShed().getHeigth());
                    shedPstmt.setDouble(2, carport.getShed().getWidth());
                    shedPstmt.setDouble(3, carport.getShed().getLength());
                    shedPstmt.setInt(4, carport.getShed().getShedCladding());
                    resultShed = shedPstmt.executeUpdate();
                    rsShed = shedPstmt.getGeneratedKeys();
                    rsShed.next();
                    shedId = rsShed.getInt(1);
                } else {
                    resultShed = 1;
                }
                if (resultShed == 1) {
                    ResultSet rsCarport = null;
                    int carportId = 0;
                    carportPstmt.setDouble(1, carport.getHeigth());
                    carportPstmt.setDouble(2, carport.getWidth());
                    carportPstmt.setDouble(3, carport.getLength());
                    carportPstmt.setInt(4, carport.getRoof().getRoofSlopeCelsius());
                    carportPstmt.setInt(5, carport.getRoof().getRoofCladding());
                    if (carport.getShed() != null) {
                        carportPstmt.setInt(6, shedId);
                    } else {
                        carportPstmt.setNull(6, java.sql.Types.INTEGER);
                    }
                    int resultCarport = carportPstmt.executeUpdate();
                    rsCarport = carportPstmt.getGeneratedKeys();
                    rsCarport.next();
                    carportId = rsCarport.getInt(1);
                    if (resultCarport == 1) {
                        requestPstmt.setDouble(1, price.getSellprice());
                        requestPstmt.setDouble(2, price.getBuyprice());
                        requestPstmt.setInt(3, customer.getId());
                        requestPstmt.setInt(4, carportId);
                        requestPstmt.executeUpdate();
                        billOfMaterialPstmt.setInt(1, carportId);
                        billOfMaterialPstmt.setInt(2, carport.getBillOfmaterial().getLineItems().get(0).getMaterial().getId());
                        billOfMaterialPstmt.executeUpdate();
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                } else {
                    conn.rollback();
                }
            } catch (Exception ex) {
                conn.rollback();
                throw new SystemException(ex);               
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

    /**
     * Meningen med denne metode er at hente alle forespørgsler fra databasen baseret på informationer fra det angivne parameter
     * @param customer
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Integer> getListCustomerRequestId(Customer customer) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_CUSTOMERID);
            ps.setInt(1, customer.getId());

            ResultSet rs = ps.executeQuery();

            List<Integer> listCustomerRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listCustomerRequestId.add(id);
            }

            if (listCustomerRequestId.isEmpty()) {
                throw new MaterialException();
            } else {
                return listCustomerRequestId;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

    /**
     * Meningen med denne metode er at hente alle forespørgsler som ikke er koblet til en medarbejder endnu
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Integer> getListEmployeeUnassignedRequestId() throws MaterialException, SystemException {

        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_EMPLOYEE_NULL);

            ResultSet rs = ps.executeQuery();

            List<Integer> listEmployeeOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeOpenRequestId.add(id);
            }

            if (listEmployeeOpenRequestId.isEmpty()) {
                throw new MaterialException();
            } else {
                return listEmployeeOpenRequestId;
            }
        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

    /**
     * Meningen med denne metode er at hente alle forespørgsler som er koblet til en medarbejder
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Integer> getListEmployeeAssignedRequestId() throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_EMPLOYEE_NOTNULL);

            ResultSet rs = ps.executeQuery();

            List<Integer> listEmployeeNotOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeNotOpenRequestId.add(id);
            }

            if (listEmployeeNotOpenRequestId.isEmpty()) {
                throw new MaterialException();
            } else {
                return listEmployeeNotOpenRequestId;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

    /**
     * Meningen med denne metode er at hente alle detaljer på en forespørgsel
     * @param request_id
     * @return RequestObject
     * @throws MaterialException
     * @throws SystemException 
     */
    public static RequestObject getRequestDetailsByRequestId(int request_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTDETAILS_BY_REQUESTID);
            ps.setInt(1, request_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String requestDate = rs.getString("requestdate");
                String offerDate = rs.getString("offerdate");
                String paymentDate = rs.getString("paymentdate");
                double pricedefault = rs.getDouble("pricedefault");
                double priceemployee = rs.getDouble("priceemployee");
                int customer_id = rs.getInt("customer_id");
                int employee_id = rs.getInt("employee_id");
                int carport_id = rs.getInt("carport_id");
                double carport_heigth = rs.getDouble("carport_heigth");
                double carport_width = rs.getDouble("carport_width");
                double carport_length = rs.getDouble("carport_length");
                int roofslopecelsius = rs.getInt("roofslopecelsius");
                int roofcladding = rs.getInt("roofcladding");
                int shed_id = rs.getInt("shed_id");
                double shed_heigth = rs.getDouble("shed_heigth");
                double shed_width = rs.getDouble("shed_width");
                double shed_length = rs.getDouble("shed_length");
                int shedcladding = rs.getInt("shedcladding");

                return new RequestObject(id, requestDate, offerDate, paymentDate, pricedefault, priceemployee,
                        new Customer(customer_id),
                        new Employee(employee_id),
                        new Carport(carport_id, carport_heigth, carport_length, carport_width, new Roof(roofslopecelsius, roofcladding), new Shed(shed_id, shed_heigth, shed_width, shed_length, shedcladding)));
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

        return null;
    }
    
    /**
     * Meningen med denne metode er at sætte en forespørgsel som betalt i datbasen baseret på det angivne parameter
     * @param id
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(UPDATE_REQUEST_PAYMENT_BY_CUSTOMER);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }
}
