/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.utils.MyConnection;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;




import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import tn.leaguestorm.entities.Rating;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.ArrayList;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ArticleDetailsController implements Initializable {

    @FXML
    private Label titleLabel;

    private ImageView imageView;

    @FXML
    private Label priceLabel;


    @FXML
    private Label categoryLabel;

    private String imagePath;
    @FXML
    private ImageView img;
    @FXML
    private Button backButton;
    private Article article;
    private MyConnection ds = MyConnection.getInstance();
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button QRCODE;
private int currentRating = 0;
    @FXML
    private Button btnhear;
    @FXML
    private TextArea descriptiontxt;
    @FXML
    private Button QRCODE1;
    @FXML
    private Button btnwishlist;
    
        private List<Article> wishlist = new ArrayList<>();
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
  
    }    
   

    @FXML
    private void goBack(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Shop.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

  public void setTitre(String titre){
        titleLabel.setText(titre);
         titleLabel.setStyle("-fx-font-size: 22pt; -fx-font-weight: bold;");
       
    }
    
    public void setPrix(String prix){
        priceLabel.setText(prix);
    }
    
 public void setDescription(String description){
       // descriptionLabel.setText(description);
        descriptiontxt.setText(description);
        descriptiontxt.setEditable(false);
        descriptiontxt.setWrapText(true);
        descriptiontxt.setStyle("-fx-font-size: 14pt;");
    }
    
    public void setCategory(String category){
        categoryLabel.setText(category);
    }
    
public void setImage(Image image) {
        img.setImage(image);
    }

@FXML
private void generateQRCode(ActionEvent event) {
String qrCodeData = titleLabel.getText() + " " + priceLabel.getText() + " " + descriptiontxt.getText(); // Set the data for the QR code to be the product title, price, and description
String filePath = "C:/Users/Nadine/Pidev/public/upload/qr_codes/" + titleLabel.getText() + ".png"; // Set the file path and name for the QR code image

try {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400); // Set the size of the QR code image

    // Convert the BitMatrix to a BufferedImage
    BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < 400; x++) {
        for (int y = 0; y < 400; y++) {
            bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
        }
    }

    // Write the BufferedImage to a file
    File qrCodeFile = new File(filePath);
    ImageIO.write(bufferedImage, "png", qrCodeFile);

    // Display a success message
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("QR Code Generated");
    alert.setHeaderText(null);
    alert.setContentText("The QR code image was generated successfully!");
    alert.showAndWait();
} catch (WriterException | IOException e) {
    // Display an error message
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("An error occurred while generating the QR code image.");
    alert.showAndWait();
}
File qrCodeFile = new File(filePath);
Image qrCodeImage = new Image(qrCodeFile.toURI().toString());
qrCodeImageView.setImage(qrCodeImage);

}

/*

 private void showRatingDialog() {
        Dialog<Rating> dialog = new Dialog<>();
        dialog.setTitle("Rate Article");

        TextArea commentTextArea = new TextArea();
        commentTextArea.setPromptText("Enter your comments here");

        Button rateButton = new Button("Rate");
        rateButton.setOnAction(event -> {
            Rating ratingResult = new Rating(ratingSystem.getCurrentRating(), commentTextArea.getText());
            dialog.setResult(ratingResult);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialog.setResult(null));

        dialog.getDialogPane().setContent(new VBox(commentTextArea, ratingSystem, rateButton, cancelButton));

        Window window = root.getScene().getWindow();
        dialog.initOwner(window);

        Optional<Rating> result = dialog.showAndWait();

        result.ifPresent(ratingResult -> {
            double newRating = ratingResult.getStars();
            String comment = ratingResult.getComment();
            article.setRating(newRating);
            article.addComment(comment);
            ratingSystem.setCurrentRating((int) newRating);
        });
    }
*/



   // Define the FreeTTS voice to use
    private static final String VOICE_NAME = "kevin16";
    @FXML
    private void hear(ActionEvent event) {
     // Get the article description from the text area
        String description = descriptiontxt.getText();

        // Define a new FreeTTS voice
        Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);

        // Check if the voice was successfully initialized
        if (voice != null) {
            voice.allocate();

            // Use the voice to speak the description
            if (description != null && !description.isEmpty()) {
                voice.speak(description);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Article Description");
                alert.setHeaderText("The article description is empty");
                alert.setContentText("Sorry, the description for this article is not available.");
                alert.showAndWait();
            }

            // Deallocate the voice when finished
            voice.deallocate();
        } else {
            // Handle case where the voice is null
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Text-to-Speech Error");
            alert.setHeaderText("Could not initialize FreeTTS voice");
            alert.setContentText("Sorry, there was an error initializing the text-to-speech engine.");
            alert.showAndWait();
        }
    }

    @FXML
    private void addToWishlist(ActionEvent event) {
      for (Article article : wishlist) {
            int articleId = article.getId();
            btnwishlist.setOnAction(e -> {
                // Get the current user
                // User user = getCurrentUser();
                int userId = 22;
                // Add the selected article to the wishlist database for the current user
                try {

                    PreparedStatement stmt = ds.getCnx().prepareStatement("INSERT INTO user_article (article_id, user_id) VALUES (?, ?)");
                    stmt.setInt(1, articleId);
                    stmt.setInt(2, userId);
                    stmt.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
      Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("The selected article has been added to your wishlist.");
        alert.showAndWait();  }
        
    }
}


