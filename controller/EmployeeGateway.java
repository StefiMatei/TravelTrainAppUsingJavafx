package controller;


import model.Employee;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeGateway {

    public static ArrayList<Employee> getAllEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee employee;
        String sql = "SELECT * from Employee";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(1));
            employee.setName(resultSet.getString(2));
            employee.setEmail(resultSet.getString(3));
            employee.setPhone(resultSet.getString(4));

            employees.add(employee);
        }
        return employees;
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        boolean insert = false;
        User user = new User();
        //int userId = this.getUserId(user.getUsername());
        String sql = "INSERT into Employee values(?,?, ?, ?, ?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employee.getEmployeeId());
        statement.setInt(2, employee.getUserId());
        statement.setString(3, employee.getName());
        statement.setString(4, employee.getEmail());
        statement.setString(5, employee.getPhone());

        statement.executeUpdate();
        insert = true;
        // connection.commit();
        connection.close();

        return insert;

    }

    public Employee getEmployee(int employeeId) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from Employee where employeeId = ?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            employee = new Employee();
            employee.setName(resultSet.getString("name"));
            employee.setEmail(resultSet.getString("email"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setEmployeeId(employeeId);
        }
        connection.close();
        return employee;
    }


    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean updated = false;
        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE Employee SET name=?, email=?, phone=? WHERE employeeId=?";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, employee.getName());
        statement.setString(2, employee.getEmail());
        statement.setString(3, employee.getPhone());
        statement.setInt(4, employee.getEmployeeId());
        statement.executeUpdate();
        updated = true;
        //  connection.commit();
        connection.close();

        return updated;
    }

    public static boolean deleteEmployee(int employeeId, int userId) throws SQLException {
        boolean deleted = false;
        String sql1 = "DELETE from Employee where employeeId=?";
        String sql2 = "DELETE from User where userId=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setInt(1, employeeId);
        statement1.executeUpdate();
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setInt(1, userId);
        statement2.executeUpdate();
        deleted = true;
        //connection.commit();
        connection.close();
        return deleted;

    }

    public boolean updateUser(User user) throws SQLException {
        boolean updated = false;
        String sql = "UPDATE User SET username=?, password=?, roleId=? WHERE userId=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, "2");
        statement.setInt(4, user.getUserId());
        statement.executeUpdate();
        updated = true;
        //connection.commit();
        connection.close();
        return updated;

    }

    public boolean addUser(User user) throws SQLException {
        boolean inserted = false;
        String sql = "INSERT into User values(? ,?, ?, ?)";
        Connection connection = DbConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, user.getUserId());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, "2");
        statement.executeUpdate();
        inserted = true;
        //connection.commit();
        connection.close();
        return inserted;
    }

    public int getUserId(String username) throws SQLException {
        int userId;
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT userId from User where userName=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            userId = resultSet.getInt("userId");

        } else {
            userId = 0;
        }
//        connection.commit();
        connection.close();
        return userId;
    }

    public Employee getEmployeeByUserId(int userId) throws SQLException {
        Employee employee = null;
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * from Employee where userId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(1));
            employee.setName(resultSet.getString(2));
            employee.setEmail(resultSet.getString(3));
            employee.setPhone(resultSet.getString(4));
        }
        connection.commit();
        connection.close();
        return employee;


    }


}
