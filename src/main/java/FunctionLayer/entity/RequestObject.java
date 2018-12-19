package FunctionLayer.entity;

/**
 * 
 * @author Morten
 */
public class RequestObject {
    
    private int id;
    private String requestDate, offerDate, paymentDate;
    private final String NO_OFFER_SENT = "Tilbud ej sendt!";
    private final String NO_PAYMENT = "Tilbud ej betalt!";
    private double priceDefault, priceEmployee;
    private Customer customer;
    private Employee employee;
    private Carport carport;

    public RequestObject(int id, String requestDate, String offerDate, String paymentDate, double priceDefault, double priceEmployee, Customer customer, Employee employee, Carport carport) {
        this.id = id;
        this.requestDate = requestDate;
        this.priceDefault = priceDefault;
        this.priceEmployee = priceEmployee;
        this.customer = customer;
        this.employee = employee;
        this.carport = carport;
        
        if (offerDate == null) {
            this.offerDate = NO_OFFER_SENT;
        } else {
            this.offerDate = offerDate;
        }
        
        if (paymentDate == null) {
            this.paymentDate = NO_PAYMENT;
        } else {
            this.paymentDate = paymentDate;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPriceDefault() {
        return priceDefault;
    }

    public void setPriceDefault(double priceDefault) {
        this.priceDefault = priceDefault;
    }

    public double getPriceEmployee() {
        return priceEmployee;
    }

    public void setPriceEmployee(double priceEmployee) {
        this.priceEmployee = priceEmployee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

      public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

}
