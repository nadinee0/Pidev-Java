package tn.leaguestorm.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import tn.leaguestorm.utils.MyConnection;
import org.mindrot.jbcrypt.BCrypt;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;


public class SigninController implements Initializable {

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink lnkForgotPassword;

    private MyConnection connect = MyConnection.getInstance();
    private Alert alert;
    private ResultSet result;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String selectData = "SELECT * FROM user WHERE email = ?";
        try {
            if (tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                PreparedStatement ps = connect.getCnx().prepareStatement(selectData);
                ps.setString(1, tfEmail.getText());
                ResultSet result = ps.executeQuery();

                if (result.next()) {
                    String hashedPassword = result.getString("password");
                    if (BCrypt.checkpw(tfPassword.getText(), hashedPassword)) {
                        
                        int id = result.getInt("id");
                        String email = result.getString("email");
                        String firstName = result.getString("first_name");
                        String lastName = result.getString("last_name");
                        String country = result.getString("country");
                        String phoneNumber = result.getString("phone_number");
                        String profilePictureName = result.getString("profile_picture_name");
                        java.sql.Date sqlDate = result.getDate("birth_date");
                        LocalDate birthDate = sqlDate.toLocalDate();
                        User user = new User(id, email, firstName, lastName, country, phoneNumber, profilePictureName, birthDate);
                        CurrentUser.setUser(user);
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Logged In");
                        alert.showAndWait();
                        
                        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Home.fxml", "Home");

                    } else {
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect Password");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Email");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize controller logic here
    }
    
    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleForgotPasswordLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Forgot.fxml", "Forgot");
    }
    
    @FXML
    private void handleSignupLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signup.fxml", "Sign up");
    }

}
