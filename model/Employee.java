package model;


public class Employee extends User {
    private int employeeId;
    private int userId;
    private String name;
    private String email;
    private String phone;

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Employee(int employeeId, int userId, String name, String email, String phone) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Employee() {
    }


    public Employee(int employeeId, int userId, String name, String email, String phone, String username, String password, String role) {
        super(username, password, role);
        this.userId = userId;
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public Employee(String username, String password, String role) {
        super(username, password, role);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
