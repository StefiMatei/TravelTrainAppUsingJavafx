package view;

import com.lowagie.text.DocumentException;
import controller.DbConnection;
import controller.TicketController;
import controller.TicketGateway;
import controller.TrainGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Report;
import model.Ticket;
import model.Train;

import javax.naming.ldap.PagedResultsControl;
import java.io.FileNotFoundException;
import java.sql.*;


public class UserView {

    private TicketController ticketController = new TicketController();


    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;

    public void addTicketb1(ActionEvent actionEvent) throws SQLException {
        ticketController.setAddTicketButton(actionEvent);
    }
    public void viewTicketb2(ActionEvent actionEvent) throws SQLException{
        ticketController.setViewTicketsButton(actionEvent);
    }
    public void sellTicketb3(ActionEvent actionEvent) throws SQLException, FileNotFoundException, DocumentException {
        ticketController.setSellTicket(actionEvent);
    }

    public void addTrainViewb4(ActionEvent actionEvent) throws SQLException{
        ticketController.setAddTrainButton(actionEvent);
    }
    public void updateTrainViewb5(ActionEvent actionEvent) throws SQLException{
        ticketController.setUpdateTrainButton(actionEvent);
    }
    public void viewTrainb6(ActionEvent actionEvent) throws SQLException{
        ticketController.setViewTrainsButton(actionEvent);
    }
    public void searchTrainViewb7(ActionEvent actionEvent) throws SQLException{
        ticketController.setFindTrainButton(actionEvent);
    }

}
