package controller;

import com.lowagie.text.DocumentException;
import dao.TicketDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Report;
import model.Ticket;
import model.Train;

import java.io.FileNotFoundException;
import java.sql.*;

/**
 * Created by matei on 6/1/2017.
 */
public class TicketController {
    private TicketDao ticketDao = new TicketDao();
    Ticket ticket = new Ticket();

    @FXML
    private TableView<Train> trainTableView;




    @FXML
    private TableColumn<Train, Integer> numberTc;
    @FXML
    private TableColumn<Train, Date> departureTc;
    @FXML
    private TableColumn<Train, Date> arrivalTc;
    @FXML
    private TableColumn<Train, String> fromTc;
    @FXML
    private TableColumn<Train, String> toTc;
    @FXML
    private TextField trainIdTf;
    @FXML
    private TextField numberTf;
    @FXML
    private TextField departureTf;
    @FXML
    private TextField arrivalTf;
    @FXML
    private TextField fromTf;
    @FXML
    private TextField toTf;
    @FXML
    private TableColumn<Train, Integer> trainIdTc;
    @FXML
    private Button addTicket;
    @FXML
    private Button sellTicket;
    @FXML
    private Button viewTickets;
    @FXML
    private Button addTrain;
    @FXML
    private Button updateTrain;
    @FXML
    private Button findTrain;
    @FXML
    private Button viewTrains;


    @FXML
    private TableView<Ticket> ticketTableView;
    @FXML
    private TableColumn<Ticket, Integer> ticketIdTc;
    @FXML
    private TableColumn<Ticket, Integer> trainTIdTc;
    @FXML
    private TableColumn<Ticket,Double> priceTc;
    @FXML
    private TableColumn<Ticket, Integer> quantityTc;
    @FXML
    private TableColumn<Ticket, Integer> placeNumberTc;
    @FXML
    private TextField ticketIdTf;
    @FXML
    private TextField trainTIdTf;
    @FXML
    private TextField priceTf;
    @FXML
    private TextField quantityTf;
    @FXML
    private TextField placeNumberTf;

    @FXML
    public void setAddTicketButton(ActionEvent actionEvent) throws SQLException{
        boolean added;
        int id = Integer.parseInt(ticketIdTf.getText());
        int idT = Integer.parseInt(trainTIdTf.getText());
        Double price = Double.valueOf(priceTf.getText());
        int quantity = Integer.parseInt(quantityTf.getText());
        int placeNumber = Integer.parseInt(placeNumberTf.getText());

        Ticket ticket = new Ticket(id, idT, price, quantity, placeNumber);
        added = ticketDao.addTicket(ticket);
        if(added){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ticket inserted successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }

    }
    private ObservableList<Ticket> tickets;
    @FXML
    public void setViewTicketsButton(ActionEvent actionEvent) throws SQLException{
        tickets = FXCollections.observableArrayList();
        String sql = "Select * from Ticket";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            tickets.add(new Ticket(resultSet.getInt(1), resultSet.getInt(2),resultSet.getDouble(3), resultSet.getInt(4), resultSet.getInt(5)));

        }
        connection.close();

        ticketIdTc.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("ticketId"));
        trainTIdTc.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("trainId"));
        priceTc.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("price"));
        quantityTc.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("quantity"));
        placeNumberTc.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("placeNumber"));

        ticketTableView.setItems(tickets);
    }

    @FXML
    private TextField raportTickedId;
    @FXML
    private TextField raportTrainId;
    @FXML
    private TextField raportDestination;
    @FXML
    private TextField raportTrainNumber;

    @FXML
    public void setSellTicket(ActionEvent actionEvent) throws SQLException, FileNotFoundException, DocumentException {
        boolean sell;
        int ticketId = Integer.parseInt(ticketIdTf.getText());
        sell = ticketDao.sellTicket(ticketId);
        Report report = new Report();
        report.generateReportQuantity();
        if(sell) {
            if (trainTIdTc.getText().equals(trainIdTc.getText())) ;
            {
                report.generateReceipt(ticketId);
                raportTickedId.setText(ticketIdTf.getText());
                raportTrainId.setText(trainTIdTf.getText());


            }
        }
    }
    private TrainGateway trainGateway = new TrainGateway();

    @FXML
    public void setAddTrainButton(ActionEvent actionEvent) throws SQLException {
        boolean add;
        int id = Integer.parseInt(trainIdTf.getText());
        int number = Integer.parseInt(numberTf.getText());
        Date departure = Date.valueOf(departureTf.getText());
        Date arrival = Date.valueOf(arrivalTf.getText());
        String from = fromTf.getText();
        String to = toTf.getText();


        Train train = new Train(id, number, departure, arrival, from, to);
        add = trainGateway.addTrain(train);
        if (add) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Train added successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }

    }

    private ObservableList<Train> trains;
    @FXML
    public void setViewTrainsButton(ActionEvent actionEvent) throws SQLException{
        trains = FXCollections.observableArrayList();
        String sql = "Select * from Train";
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            trains.add(new Train(resultSet.getInt(1),resultSet.getInt(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6)));
        }
        connection.close();

        trainIdTc.setCellValueFactory(new PropertyValueFactory<Train, Integer>("trainId"));
        numberTc.setCellValueFactory(new PropertyValueFactory<Train,Integer>("number"));
        departureTc.setCellValueFactory(new PropertyValueFactory<Train, Date>("dateOfDeparture"));
        arrivalTc.setCellValueFactory(new PropertyValueFactory<Train, Date>("dateOfArrival"));
        fromTc.setCellValueFactory(new PropertyValueFactory<Train, String>("leavesFrom"));
        toTc.setCellValueFactory(new PropertyValueFactory<Train, String>("goesTo"));

        trainTableView.setItems(trains);
    }

    public void setUpdateTrainButton(ActionEvent actionEvent) throws SQLException{
        boolean update;
        int id = Integer.parseInt(trainIdTf.getText());
        int number = Integer.parseInt(numberTf.getText());
        Date departure = Date.valueOf(departureTf.getText());
        Date arrival = Date.valueOf(arrivalTf.getText());
        String from = fromTf.getText();
        String to = toTf.getText();

        Train train = new Train(id, number, departure, arrival, from, to);
        update = trainGateway.updateTrain(train);

        if(update){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Train updated successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();

        }

    }

    public void setFindTrainButton(ActionEvent actionEvent) throws SQLException{

        trains = FXCollections.observableArrayList();
        int number = Integer.parseInt(numberTf.getText());
        Train train;
        train = trainGateway.getTrain(number);
        trains.add(train);

        trainIdTc.setCellValueFactory(new PropertyValueFactory<Train, Integer>("trainId"));
        numberTc.setCellValueFactory(new PropertyValueFactory<Train,Integer>("number"));
        departureTc.setCellValueFactory(new PropertyValueFactory<Train, Date>("dateOfDeparture"));
        arrivalTc.setCellValueFactory(new PropertyValueFactory<Train, Date>("dateOfArrival"));
        fromTc.setCellValueFactory(new PropertyValueFactory<Train, String>("leavesFrom"));
        toTc.setCellValueFactory(new PropertyValueFactory<Train, String>("goesTo"));

        trainTableView.setItems(trains);
    }


}
