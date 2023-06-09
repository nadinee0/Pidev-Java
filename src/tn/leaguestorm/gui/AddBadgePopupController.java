/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.services.BadgeService;
import tn.leaguestorm.utils.FXMLUtils;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class AddBadgePopupController implements Initializable {

    @FXML
    private TextField tfReference;
    @FXML
    private TextField tfValue;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ImageView badgeIMGV;
    @FXML
    private Button btnApply;
    @FXML
    private Hyperlink cancelHPRL;

    private Alert alert;

    Badge badge = new Badge();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void choosePictureAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String baseName = FilenameUtils.getBaseName(selectedFile.getName());
            String randomPrefix = UUID.randomUUID().toString();
            String fileName = baseName + "-" + randomPrefix.substring(0, 8) + randomPrefix.substring(9, 13) + randomPrefix.substring(14, 18) + randomPrefix.substring(19, 23) + selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            Path destinationPath = Paths.get("C:/leagueStorm/src/tn/leaguestorm/miscs/badge/", fileName);
            try {
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(selectedFile.toURI().toString());
                this.badgeIMGV.setImage(image);
                badge.setBadgeFileName(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File copied to " + destinationPath);
        } else {
            System.out.println("No file selected.");
        }
    }

    @FXML
    private void applyAction(ActionEvent event) {
        String reference = tfReference.getText();
        int value = Integer.parseInt(tfValue.getText());
        String description = tfDescription.getText();

        badge.setLogo(reference);
        badge.setValeur(value);
        badge.setDescription(description);

        BadgeService bs = new BadgeService();
        try {
            bs.ajouter(badge);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/BackBadge.fxml", "Badges");
    }

}
