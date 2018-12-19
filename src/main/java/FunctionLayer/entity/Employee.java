
package FunctionLayer.entity;

/**
 * Denne klasse indeholder variabler, konstruktør, gettere & settere for en ansat.
 */
public class Employee {
    
    private int id;
    private boolean admin;
    private String firstName, lastName, email, password, role;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, boolean admin, String firstName, String lastName, String email, String password, String role) {
        this.id = id;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
