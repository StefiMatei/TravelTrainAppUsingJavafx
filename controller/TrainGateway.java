package controller;

import model.Train;

import java.awt.geom.RectangularShape;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TrainGateway {
    public boolean addTrain(Train train) throws SQLException{
        boolean add = false;
        String sql = "Insert into Train values(?,?,?,?,?,?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, train.getTrainId());
        statement.setInt(2,train.getNumber());
        statement.setDate(3, train.getDateOfDeparture());
        statement.setDate(4, train.getDateOfArrival());
        statement.setString(5, train.getLeavesFrom());
        statement.setString(6, train.getGoesTo());
        statement.executeUpdate();
        add = true;
        connection.close();
        return add;

    }

    public boolean updateTrain(Train train) throws SQLException{
        boolean updated = false;
        Connection connection = DbConnection.getConnection();
        String sql = "Update Train set number=?,dateOfDeparture=?,dateOfArrival=?, leavesFrom=?, goesTo=? where trainId=?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, train.getNumber());
        statement.setDate(2, train.getDateOfDeparture());
        statement.setDate(3, train.getDateOfArrival());
        statement.setString(4, train.getLeavesFrom());
        statement.setString(5, train.getGoesTo());
        statement.setInt(6, train.getTrainId());
        statement.executeUpdate();
        updated = true;
        connection.close();
        return updated;

    }

    public Train getTrain(int number) throws SQLException{
        Train train = new Train();
        String sql = "select * from Train where number=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, number);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            train.setTrainId(resultSet.getInt("trainId"));
            train.setNumber(number);
            train.setDateOfDeparture(resultSet.getDate("dateOfDeparture"));
            train.setDateOfArrival(resultSet.getDate("dateOfArrival"));
            train.setLeavesFrom(resultSet.getString("leavesFrom"));
            train.setGoesTo(resultSet.getString("goesTo"));
        }
        connection.close();
        return train;
    }

}
