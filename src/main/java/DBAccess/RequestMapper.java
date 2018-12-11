/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.LineItem;
import FunctionLayer.entity.Request;
import FunctionLayer.entity.Shed;
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
 *
 * @author nr
 */
public class RequestMapper {

    private static final String INSERT_INTO_REQUEST = "INSERT INTO `REQUEST` (`pricedefault`, `priceemployee`, `customer_id`, `carport_id`) VALUES (?,?,?,?)";
    private static final String INSERT_INTO_SHED = "INSERT INTO `SHED` (`heigth`, `width`, `length`, `shedcladding`) VALUES (?,?,?,?)";
    private static final String INSERT_INTO_CARPORT = "INSERT INTO `CARPORT` (`heigth`, `width`, `length`, `roofslopecelsius`, `roofcladding`, `shed_id`) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_INTO_CARPORT_HAS_MATERIALS = "INSERT INTO `CARPORT_HAS_MATERIALS` (`carport_id`, `materials_id`) VALUES (?,?)";

    private static final String GET_REQUESTID_BY_CUSTOMERID = "SELECT id FROM `REQUEST` WHERE customer_id = ?";
    private static final String GET_REQUESTID_BY_EMPLOYEE_NULL = "SELECT id FROM `REQUEST` WHERE employee_id IS NULL?";
    private static final String GET_REQUESTID_BY_EMPLOYEE_NOTNULL = "SELECT id FROM `REQUEST` WHERE employee_id IS NOT NULL?";

    public static void createRequest(Customer customer, Request request, Carport carport, Shed shed) throws SystemException {
        try {
            Connection conn = DBConnector.connection();
            PreparedStatement shedPstmt = conn.prepareStatement(INSERT_INTO_SHED, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement carportPstmt = conn.prepareStatement(INSERT_INTO_CARPORT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement requestPstmt = conn.prepareStatement(INSERT_INTO_REQUEST);
            PreparedStatement billOfMaterialPstmt = conn.prepareStatement(INSERT_INTO_CARPORT_HAS_MATERIALS);
            ResultSet rsShed = null;
            int shedId = 0;
            try {
                shedPstmt.setDouble(1, shed.getHeigth());
                shedPstmt.setDouble(2, shed.getWidth());
                shedPstmt.setDouble(3, shed.getLength());
                shedPstmt.setInt(4, shed.getShedCladding());
                conn.setAutoCommit(false);
                int resultShed = shedPstmt.executeUpdate();
                rsShed = shedPstmt.getGeneratedKeys();
                rsShed.next();
                shedId = rsShed.getInt(1);
                if (resultShed == 1) {
                    ResultSet rsCarport = null;
                    int carportId = 0;
                    carportPstmt.setDouble(1, carport.getHeigth());
                    carportPstmt.setDouble(2, carport.getWidth());
                    carportPstmt.setDouble(3, carport.getLength());
                    carportPstmt.setInt(4, carport.getRoof().getRoofSlopeCelsius());
                    carportPstmt.setInt(5, carport.getRoof().getRoofCladding());
                    carportPstmt.setInt(6, shedId);
                    int resultCarport = carportPstmt.executeUpdate();
                    rsCarport = carportPstmt.getGeneratedKeys();
                    rsCarport.next();
                    carportId = rsCarport.getInt(1);
                    if (resultCarport == 1) {
                        requestPstmt.setDouble(1, request.getPriceDefault());
                        requestPstmt.setDouble(2, request.getPriceEmployee());
                        requestPstmt.setInt(3, customer.getId());
                        requestPstmt.setInt(4, carportId);
                        requestPstmt.executeUpdate();
                        for (LineItem lineItem : carport.getBillOfmaterial().getLineItems()) {
                            billOfMaterialPstmt.setInt(1, carportId);
                            billOfMaterialPstmt.setInt(2, lineItem.getMaterial().getId());
                            billOfMaterialPstmt.executeUpdate();
                        }
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
                //Logging                
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }
    }

    public static List<Integer> getListCustomerRequestId(Customer customer) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_CUSTOMERID);
            ps.setInt(1, customer.getId());

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

            List<Integer> listCustomerRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listCustomerRequestId.add(id);
            }

            if(listCustomerRequestId.isEmpty()){
                throw new MaterialException();
            } else {
                return listCustomerRequestId;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }
    }

    public static List<Integer> getListEmployeeUnassignedRequestId() throws MaterialException, SystemException {

        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_EMPLOYEE_NULL);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

            List<Integer> listEmployeeOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeOpenRequestId.add(id);
            }
            
            if(listEmployeeOpenRequestId.isEmpty()){
                throw new MaterialException();
            } else {
                return listEmployeeOpenRequestId;
            }
        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }
    }

    public static List<Integer> getListEmployeeAssignedRequestId() throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_REQUESTID_BY_EMPLOYEE_NOTNULL);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

            List<Integer> listEmployeeNotOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeNotOpenRequestId.add(id);
            }
            
            if(listEmployeeNotOpenRequestId.isEmpty()){
                throw new MaterialException();
            } else {
                return listEmployeeNotOpenRequestId;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }
    }
}
