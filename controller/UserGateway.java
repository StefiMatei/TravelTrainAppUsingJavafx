package controller;


import model.User;

import java.sql.*;


public class UserGateway {

    public static User searchUser(User user) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String sql = "select* from user u join role r on u.userId = r.roleId";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("password").equals(user.getPassword()) && resultSet.getString("userName").equals(user.getUsername())) {
                user.setUserId(resultSet.getInt("userId"));
                user.setRole(resultSet.getString("label"));
            }

        }
        connection.commit();
        connection.close();
        return user;
    }

    public static boolean existUsername(String username) throws SQLException {
        boolean exists = false;
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * from user";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            if (username.equals(resultSet.getString("userName")))
                exists = true;
        }
        connection.close();
        return exists;
    }

    public static boolean loginAdmin(String userName, String password) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String databaseUserName = "";
        String databasePassword = "";
        boolean logged;
        String sql = "SELECT * from User where username=? && password=? && roleId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setInt(3, 1);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            databaseUserName = resultSet.getString("username");
            databasePassword = resultSet.getString("password");
        }
        if (userName.equals(databaseUserName) && password.equals(databasePassword)) {
            logged = true;
        } else {
            logged = false;
        }
        return logged;
    }

    public static boolean loginUser(String userName, String password) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String databaseUserName = "";
        String databasePassword = "";
        boolean logged;
        String sql = "SELECT * from User where username=? && password=? && roleId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setInt(3, 2);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            databaseUserName = resultSet.getString("username");
            databasePassword = resultSet.getString("password");
        }
        if (userName.equals(databaseUserName) && password.equals(databasePassword)) {
            logged = true;
        } else {
            logged = false;
        }
        return logged;
    }


}
