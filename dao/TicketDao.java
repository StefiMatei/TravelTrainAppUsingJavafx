package dao;

import controller.DbConnection;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by matei on 6/1/2017.
 */
public class TicketDao {
    public boolean addTicket(Ticket ticket) throws SQLException {
        boolean added = false;
        String sql = "Insert into Ticket values(?,?,?,?,?) ";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ticket.getTicketId());
        statement.setInt(2, ticket.getTrainId());
        statement.setDouble(3, ticket.getPrice());
        statement.setInt(4, ticket.getQuantity());
        statement.setInt(5, ticket.getPlaceNumber());
        statement.executeUpdate();
        added = true;
        connection.close();
        return added;


    }

    public boolean updateTicket(Ticket ticket) throws SQLException{
        boolean added = false;
        String sql = "Update Ticket set trainId=?, price=?, quantity=?, placeNumber=? where ticketId=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, ticket.getTrainId());
        statement.setDouble(2, ticket.getPrice());
        statement.setInt(3, ticket.getQuantity());
        statement.setInt(4, ticket.getPlaceNumber());
        statement.setInt(5, ticket.getTicketId());
        statement.executeUpdate();
        added = true;
        connection.close();
        return added;


    }

    public Ticket getTicket(int ticketId) throws SQLException{
        Ticket ticket = null;
        String sql = "Select * from Ticket where ticketId=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, ticketId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            ticket = new Ticket();
            ticket.setTrainId(resultSet.getInt("trainId"));
            ticket.setPrice(resultSet.getDouble("price"));
            ticket.setQuantity(resultSet.getInt("quantity"));
            ticket.setPlaceNumber(resultSet.getInt("placeNumber"));
            ticket.setTicketId(ticketId);
        }
        connection.close();
        return ticket;
    }

    public boolean deleteTicket(int ticketId) throws SQLException {
        boolean deleted = false;
        String sql = "Delete from Ticket where ticketId=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ticketId);
        statement.executeUpdate();
        deleted = true;
        connection.close();
        return deleted;

    }

    public boolean sellTicket(int ticketId) throws SQLException{
        boolean sold;
        Ticket ticket;
        ticket = getTicket(ticketId);
        ticket.setQuantity(0);
        updateTicket(ticket);
        sold = true;
        return sold;

    }
}
