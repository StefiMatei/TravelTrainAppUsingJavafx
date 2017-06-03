package view;

import controller.DbConnection;
import controller.EmployeeGateway;
import controller.UserGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminView {
    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> employeeId;

    @FXML
    private TableColumn<Employee, Integer> userId;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private TableColumn<Employee, String> phone;

    @FXML
    private TableColumn<Employee, String> userNameColumn;

    @FXML
    private TableColumn<Employee, String> passwordColumn;

    @FXML
    private TableColumn<Employee, String> roleColumn;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField roleField;

    @FXML
    private Button viewButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idUserTextField;

    @FXML
    Button addEmployee;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button findEmployeeButton;

    private ObservableList<Employee> employees;

    @FXML
    public void setViewButton(ActionEvent actionEvent) {
        employees = FXCollections.observableArrayList();
        try {
            String sql2 = "Select * from Employee";
            String sql = "SELECT User.username, User.password, User.roleId from User join Employee  ON User.userId=Employee.userId";

            Connection connection = DbConnection.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(sql2);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet1 = statement1.executeQuery();
            ResultSet resultSet = statement.executeQuery();



            while (resultSet1.next()&&resultSet.next()) {
                employees.add(new Employee(resultSet1.getInt(1), resultSet1.getInt(2), resultSet1.getString(3), resultSet1.getString(4), resultSet1.getString(5), resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
                }


            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        userId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("userId"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));


        employeeTableView.setItems(employees);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidPhoneNumber(String phone) {
        String regexStr = "^[0-9]{10}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStr);
        java.util.regex.Matcher m = p.matcher(phone);
        return m.matches();
    }

    public boolean isValidCnp(String cnp) {
        String regexStr = "^[0-9]{13}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStr);
        java.util.regex.Matcher m = p.matcher(cnp);
        return m.matches();
    }

    EmployeeGateway employeeGateway = new EmployeeGateway();

    UserGateway userGateway  = new UserGateway();
    @FXML

    private void setAddEmployee(ActionEvent actionEvent) throws IOException, SQLException {
        boolean inserted1;
        boolean inserted2;
        int userId = Integer.parseInt(idUserTextField.getText());
        int employeeId = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String role = roleField.getText();


        if (isValidEmailAddress(email) && isValidPhoneNumber(phone)) {
            Employee employee = new Employee(employeeId, userId, name, email, phone);
            User user = new User(userId, username, password, role);
            inserted1 = employeeGateway.addEmployee(employee);
            inserted2 = employeeGateway.addUser(user);
            if (inserted1 == true && inserted2 == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee inserted succsefully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error!");
                alert.showAndWait();
            }
        }
    }

    @FXML

    public void setDeleteButton(ActionEvent actionEvent) throws SQLException {
        boolean deleted;
        int userId = Integer.parseInt(idUserTextField.getText());
        int employeeId = Integer.parseInt(idTextField.getText());
        deleted = employeeGateway.deleteEmployee(employeeId, userId);
        if (deleted == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee deleted succsefully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }

    }

    @FXML

    public void setUpdateButton(ActionEvent actionEvent) throws SQLException {
        int userId = Integer.parseInt(idUserTextField.getText());
        int employeeId = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String role = roleField.getText();
        if (isValidEmailAddress(email) && isValidPhoneNumber(phone)) {
            Employee employee = new Employee(employeeId, userId, name, email, phone);
            User user = new User(userId, username, password, role);


            boolean updated1 = employeeGateway.updateEmployee(employee);
            boolean updated2 = employeeGateway.updateUser(user);

            if (updated1 == true && updated2 == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee updated succsefully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error!");
                alert.showAndWait();
            }

        }
    }


    @FXML
    private void setFindEmployeeButton(ActionEvent actionEvent) throws SQLException {
        int id = Integer.parseInt(idTextField.getText());
        employees = FXCollections.observableArrayList();
        try {
            String sql = "select * from Employee where employeeId = ?";
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));


            }
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        userId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("userId"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));


        employeeTableView.setItems(employees);
    }



}
