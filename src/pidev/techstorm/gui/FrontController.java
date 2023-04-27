/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import pidev.techstorm.entities.Events;

import pidev.techstorm.services.ServiceEvents;

import pidev.techstorm.tests.MyListener;
import pidev.techstorm.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FrontController implements Initializable {

     private Stage stage;
     private Scene scene;
     private Parent root;
    @FXML
    private VBox chosenEventCard;
    @FXML
    private Label EventNameLabel;
    @FXML
    private ImageView EventImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
     private List<Events> events = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private Events selectedEvents;
    
    Events a = new Events();
    private DataSource ds = DataSource.getInstance();
    ServiceEvents sa = new ServiceEvents();
    @FXML
    private ImageView id_map;
    @FXML
    private Label EventDescriptionLabel;
    
    public void populateGridPane() throws SQLException, FileNotFoundException {
        String query = "SELECT * FROM events";

        // Execute the query and retrieve the result set
        Statement statement = ds.getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Define the column and row indexes for the GridPane
        int colIndex = 0;
        int rowIndex = 0;

        // Iterate through the result set and add each product to the GridPane
        while (resultSet.next()) {
            
            // Create a new Label for the product name
            Label eventstitle = new Label(resultSet.getString("title"));
            grid.add(eventstitle, colIndex, rowIndex + 1);

            // Create a new Label for the product price
            Label location = new Label(resultSet.getString("location"));
            grid.add(location, colIndex, rowIndex + 2);

            // Create a new ImageView for the product image
            String imagePath = "D:/3éme/2éme semestre/pi/Pidev3/src/pidev/techstorm/img/" + resultSet.getString("image"); // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());

            ImageView imageView = new ImageView(image);
//            imageView.setBlendMode(BlendMode.MULTIPLY);

            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels

            // Add a mouse click event handler to the ImageView
            int eventId = resultSet.getInt("id"); // Get the product ID from the result set
            imageView.setOnMouseClicked(e -> {
                // Pass the product ID to the method that will handle the click event
                handleProductClick(eventId, eventstitle.getText(), location.getText(), imagePath);
            });

            // Add the ImageView to the GridPane
            grid.add(imageView, colIndex, rowIndex + 3);

            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 4;
            }
        }
    }
    
     private void handleProductClick(int eventId, String eventstitle, String location, String imagePath) {
         // Update the UI with the selected product details
        EventNameLabel.setText(eventstitle);
        EventDescriptionLabel.setText(location);
      //  DescriptionLabel.setText(description);
        EventImg.setImage(new Image(new File(imagePath).toURI().toString()));
        //Img.setBlendMode(BlendMode.MULTIPLY);
        EventImg.setStyle("-fx-background-color: transparent");

    }
     
     private void setChosenEvents(Events events) {
        selectedEvents = events;
        EventNameLabel.setText(selectedEvents.getTitle());
        EventDescriptionLabel.setText(FirstWindow.CURRENCY + selectedEvents.getLocation());
        Image image = new Image(getClass().getResourceAsStream(selectedEvents.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setBlendMode(BlendMode.MULTIPLY);

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        
        chosenEventCard.getChildren().clear();
        chosenEventCard.getChildren().addAll(imageView, EventNameLabel, EventDescriptionLabel);

        handleProductClick(selectedEvents.getId(), selectedEvents.getTitle(), selectedEvents.getLocation(), selectedEvents.getImage());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_map.setOnMouseClicked(event -> {
            Stage mapStage = new Stage();
            WebView webView = new WebView();
            webView.getEngine().load("https://maps.google.com");
            BorderPane root = new BorderPane();
            root.setCenter(webView);
            Scene scene = new Scene(root, 800, 600);
            mapStage.setScene(scene);
            mapStage.show();
        });
        
        try {
            populateGridPane();
            if (events.size() > 0) {
                setChosenEvents(events.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Events events) {
                        setChosenEvents(events);
                    }
                };
            }

            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < events.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("pidev/techstorm/gui/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(events.get(i), myListener);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row);
                    
                     grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
    
      private String getImageNameFromDatabase(String eventsName) {
        String imageName = null;

        try {
            PreparedStatement pstmt = ds.getCnx().prepareStatement("SELECT image FROM events WHERE title = ?");
            pstmt.setString(1, eventsName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                imageName = rs.getString("image");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageName;
    }
        
        
         
        
        
        
        
        // TODO

    @FXML
    private void ticket(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
    

   
        
    
}
