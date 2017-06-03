package view;

import controller.DbConnection;
import controller.UserGateway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginView implements Initializable {
    @FXML
    private Label lblTxt;

    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField pasText;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField userNameTxt;

    @FXML
    private PasswordField pasUserTxt;

    @FXML
    private Button loginUserTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DbConnection.getConnection();
            System.out.println("Connection established!");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @FXML

    public void setLoginBtn(ActionEvent actionEvent) throws SQLException {
        UserGateway userGateway = new UserGateway();
        String userName = userTxt.getText();
        String password = pasText.getText();
        boolean l;
        l = userGateway.loginAdmin(userName, password);
        if (l == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("Login succsefully!");
            //alert.showAndWait();
            try {
                Stage secondStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                secondStage.setTitle("Admin Page");
                secondStage.setScene(new Scene(root, 750, 700));
                secondStage.show();
                // Hide this current window (if this is what you want)
                //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    @FXML

    public void setLoginUserBtn(ActionEvent actionEvent) throws SQLException {
        UserGateway userGateway = new UserGateway();
        String userName = userNameTxt.getText();
        String password = pasUserTxt.getText();
        boolean l;
        l = userGateway.loginUser(userName, password);
        if (l == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("Login succsefully!");
            //alert.showAndWait();
            try {
                Stage secondStage = new Stage();
                Stage secondStage1 = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("user1.fxml"));
                Parent root1 = FXMLLoader.load(getClass().getResource("user.fxml"));
                secondStage.setTitle("User Page");
                secondStage1.setTitle("Pt Butoane");
                secondStage.setScene(new Scene(root, 750, 700));
                secondStage1.setScene(new Scene(root1, 750, 700));
                secondStage.show();
                secondStage1.show();
                // Hide this current window (if this is what you want)
                //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

}
