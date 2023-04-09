package tn.leaguestorm.gui;

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
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import tn.leaguestorm.utils.MyConnection;
import org.mindrot.jbcrypt.BCrypt;


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
        String selectData = "SELECT email, password FROM user WHERE email = ?";
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
                            
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Logged In");
                        alert.showAndWait();
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.setScene(scene);
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

    @FXML
    private void handleForgotPasswordLinkAction(ActionEvent event) {
        // Handle forgot password logic here
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize controller logic here
    }

    private boolean isValidEmail(String email) {
        // Implement email validation logic here
        return true;
    }

    private boolean isValidPassword(String password) {
        // Implement password validation logic here
        return true;
    }
    
    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

}
