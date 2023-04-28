/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.ValidationException;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.utils.MyConnection;

//import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ArticleController implements Initializable {

    Article a = new Article();
    @FXML
    private TextField tfTitle;
    private TextField tfImage;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private ListView<Article> articleList;

    private ObservableList<Article> articles;

    @FXML
    private Button btnImport;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnstats;
    @FXML
    private Button btnnotif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Article a = new Article();
// TODO
        ServiceArticle sa = new ServiceArticle();

        try {
            List<String> categoryNames = sa.getAllCategoryNames();
            cbCategory.getItems().addAll(categoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Show the combo box when it is clicked
        cbCategory.setOnMouseClicked(event -> {
            cbCategory.show();
        });
        String catg = cbCategory.getValue();

        try {
            /*            // retrieve the data from the database
            //  String query = "SELECT titre, image, description, prix, stock  FROM article ";
            String query = "SELECT a.titre, a.image, a.description, a.prix, a.stock, c.nom AS category "
                    + "FROM article a "
                    + "INNER JOIN category c ON a.category_id = c.id";

            PreparedStatement stmt = ds.getCnx().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // populate the list view with the data
            while (rs.next()) {
                String titre = rs.getString("titre");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("prix");
                int stock = rs.getInt("stock");
                String category = rs.getString("category");
//Category category = new Category(categoryName);
                // create a new Article object and add it to the list view
                articleList.getItems().add(new Article(titre, image, price, description, stock, category));
            }*/
            List<Article> list = new ArrayList<>();

            String req = "Select * from article";
            Statement st = ds.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Article ar = new Article(rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getString(8));

                articleList.getItems().add(ar);
            }
            ;

            // set the cell factory to customize the appearance of each cell
            articleList.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() {
                @Override
                public ListCell<Article> call(ListView<Article> param) {
                    return new ListCell<Article>() {
                        private final ImageView imageView = new ImageView();
                        private final Label titleLabel = new Label();
                        private final Label descriptionLabel = new Label();
                        private final Label priceLabel = new Label();
                        private final Label stockLabel = new Label();
                        private final Label categoryLabel = new Label();

                        {
                            HBox hbox = new HBox(imageView, titleLabel, descriptionLabel, priceLabel, stockLabel, categoryLabel);
                            hbox.setSpacing(10);
                            setGraphic(hbox);
                        }

                        @Override
                        protected void updateItem(Article item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null) {
                                titleLabel.setText(null);
                                descriptionLabel.setText(null);
                                priceLabel.setText(null);
                                stockLabel.setText(null);
                                imageView.setImage(null);
                                categoryLabel.setText(null);
                            } else {
                                titleLabel.setText(item.getTitre());
                                descriptionLabel.setText(item.getDescription());
                                priceLabel.setText(String.format("%.2f", item.getPrix()) + " $");
                                stockLabel.setText(item.getStock() + " in stock");
//                                categoryLabel.setText(item.getCategory().getNom());

                                // load the image from the database and display it in the cell
                                try {
                                    String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + item.getImage();

                                    Image image = new Image(new File(imagePath).toURI().toString());
                                    imageView.setImage(image);
                                    imageView.setFitWidth(100);
                                    imageView.setFitHeight(100);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        articleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue<? extends Article> observable, Article oldValue, Article newValue) {
                if (newValue != null) {
                    updateUI(newValue);
                }
            }
        });
    }

    private void updateUI(Article article) {
        tfTitle.setText(article.getTitre());
        taDescription.setText(article.getDescription());
        tfStock.setText(String.valueOf(article.getStock()));
        tfPrice.setText(String.valueOf(article.getPrix()));
        cbCategory.setValue(article.getNomcatg());

        // load and display the image
        try {
            String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + article.getImage();
            Image image = new Image(new File(imagePath).toURI().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void saveArticle(ActionEvent event) throws SQLException, IOException {
        ServiceArticle sa = new ServiceArticle();
        Import(event);
        String title = tfTitle.getText();
        String description = taDescription.getText();
        String priceS = /*Float.valueOf(tfPrice.getText()); */ tfPrice.getText();
        float price = Float.valueOf(tfPrice.getText());
        String stockS = tfStock.getText();
        Integer stock = Integer.valueOf(tfStock.getText());
        String category = cbCategory.getValue();
        int categoryId = sa.getCategoryIDByName(category);

        //   Article a = new Article(title, price, description, stock, categoryId subcategoryId);
        try {
            if (title.isEmpty() && description.isEmpty() && priceS.isEmpty() && stockS.isEmpty() && category.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;
            }

            if (title.length() < 2 || title.length() > 20) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("title must must be between 2 and 50 characters !!");
                alert.showAndWait();
                return;
            }
            if (!description.matches("[a-zA-Z0-9\\s'.,-]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Input");
                alert.setContentText("Article description can only contain letters, digits, spaces, apostrophes, commas, periods, and hyphens !");
                alert.showAndWait();
                return;
            }

            if (description.length() < 10 || description.length() > 100) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("Description must be between 10 and 100  !!");
                alert.showAndWait();
                return;
            }

            //  float price = Float.parseFloat(priceS);
            if (price < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Price can't be negative");
                alert.setContentText("Please fill the field with positive value !!");
                alert.showAndWait();
                return;
            }
            if (!priceS.matches("[0-9]+(\\.[0-9]+)?")) { // Check if the string contains only numbers and optional decimal point
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Price must contains only numbers ");
                alert.setContentText("Please fill the field with Numbers !!");
                alert.showAndWait();
                return;
            }

            //  int stock = Integer.parseInt(stockS);
            if (stock < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Stock can't be negative");
                alert.setContentText("Stock must be greater than zero!");
                alert.showAndWait();
                return;
            }
            if (!stockS.matches("\\d+")) { // Check if the string contains only digits
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stock must contain only whole numbers");
                alert.setContentText("Please fill the field with whole numbers only!");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }

        String req = "SELECT id FROM `article` WHERE titre=?";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, a.getTitre());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // The article name already exists
            System.out.println("This Article Already Exists ! ");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Exsiting Article ");
            alert.setContentText(a.getTitre() + " Article Already Exists !!");
            alert.showAndWait();
            return;
        } else {
            // try {    
            String req1 = "INSERT INTO `article` (`titre`, `image`,`prix`,`description`,`stock`,`category_id`) VALUES (?,?,?,?,?,?)";
            PreparedStatement st1 = ds.getCnx().prepareStatement(req1);
            st1.setString(1, a.getTitre());
            if (a.getImage() != null) {
                st1.setString(2, a.getImage());
            } else {
                st1.setNull(2, java.sql.Types.NULL);
            }
            st1.setFloat(3, a.getPrix());
            st1.setString(4, a.getDescription());
            st1.setInt(5, a.getStock());
            st1.setInt(6, categoryId);
            st1.executeUpdate();
            System.out.println("Article ajouté avec succès !");
            //  sa.ajouter2(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully Added !");
            alert.showAndWait();
            /*} catch (SQLException e) {
        // display an error message if there was a problem inserting the article into the database
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de l'ajout de l'article");
        alert.showAndWait();
    }*/
            articleList.refresh();
            tfTitle.setText("");
            taDescription.setText("");
            tfPrice.setText("");
            tfStock.setText("");
            cbCategory.setValue(null);
            imageView.setImage(null);
        }

    }

    @FXML
    private void updateArticle(ActionEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();

        String title = tfTitle.getText();
//        String image = tfImage.getText();
        String description = taDescription.getText();
        Float price = Float.valueOf(tfPrice.getText());
        // String type = cbType.getValue();
        Integer stock = Integer.valueOf(tfStock.getText());
        String newCategory = cbCategory.getValue();
        int categoryId = sa.getCategoryIDByName(newCategory);

        Article a = articleList.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select an Article to update !");
            alert.showAndWait();
            return;
        }

        sa.updateArticle(a.getId(), title, price, description, stock, newCategory);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Article Successfully updated !");
        alert.showAndWait();

        articleList.refresh();
        tfTitle.setText("");
        // tfImage.setText("");
        taDescription.setText("");
        tfPrice.setText("");
        // cbType.setValue("");
        tfStock.setText("");
        cbCategory.setValue(null);
    }

    @FXML
    private void deleteArticle(ActionEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();

        Article a = articleList.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select an Article to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Article");
        alert.setContentText("Are you sure you want to delete the selected article : " + a.getTitre() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            sa.deleteArticle(a);
//           articles.remove(a);
            //   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully Deleted !");
            alert.showAndWait();
            articleList.refresh();
            tfTitle.setText("");
            taDescription.setText("");
            tfPrice.setText("");
            tfStock.setText("");
        }

    }

    private MyConnection ds = MyConnection.getInstance();

    @FXML
    private void Import(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // User has selected a file, do something with it
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get("C:/Users/Nadine/Pidev/public/img"); // replace with the desired path to save the image
            try {
                Files.copy(sourcePath, targetPath.resolve(selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            } catch (IOException e) {
                // handle the exception
            }
        }

    }

    @FXML
    private void category(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Category.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

    @FXML
    private void article(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Article.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

    @FXML
    private void stats(ActionEvent event) {
        // Retrieve the article statistics data from the database
        ObservableList<PieChart.Data> pieChartData = getArticleStats();

        // Create the stats popup
        Stage statsStage = new Stage();
        statsStage.setTitle("Article Statistics");
        statsStage.setWidth(600);
        statsStage.setHeight(600);

        // Create the pie chart and add the data to it
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Article Stock Statistics");
        chart.setPrefWidth(800);
        chart.setPrefHeight(800);
        // Add the chart to the popup
        VBox statsBox = new VBox(chart);
        Scene statsScene = new Scene(statsBox);
        statsStage.setScene(statsScene);
        statsStage.show();
    }

    private ObservableList<PieChart.Data> getArticleStats() {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            stmt = ds.getCnx().createStatement();

            // Retrieve the article statistics data from the database
            rs = stmt.executeQuery("SELECT SUM(stock), titre FROM article GROUP BY titre");
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            int totalStock = 0;
            while (rs.next()) {
                int stock = rs.getInt(1);
                String name = rs.getString(2);
                totalStock += stock;
                pieChartData.add(new PieChart.Data(name, stock));
            }
            // Calculate and set the percentage for each data item
            for (PieChart.Data data : pieChartData) {
                double percentage = (data.getPieValue() / totalStock) * 100;
                data.setName(data.getName() + " (" + String.format("%.2f", percentage) + "%)");
            }
            return pieChartData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // Close the database resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void notif(ActionEvent event) {
        // Query the database to retrieve articles with a stock level below 5
        StringBuilder contentText = new StringBuilder();
        try (
                ResultSet rs = ds.getCnx().createStatement().executeQuery("SELECT * FROM article WHERE stock < 10")) {
            while (rs.next()) {
                String articleName = rs.getString("titre");
                int stockLevel = rs.getInt("stock");
                contentText.append(articleName).append(":   ").append(stockLevel).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a pop-up window with the low stock articles
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Low Stock");
        alert.setHeaderText("The following articles have a low stock level:");
        alert.setContentText(contentText.toString());

        // Customize the dialog pane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefSize(600, 500); // set preferred size to 600x400 pixels
        dialogPane.getStylesheets().add(getClass().getResource("/tn/leaguestorm/values/Notifstyle.css").toExternalForm());
        dialogPane.getStyleClass().add("low-stock");

        // Display the pop-up window
        alert.showAndWait();

        ///------------API CODE------------------  
        // Query the database to retrieve articles with a stock level below 5
        /*      StringBuilder contentText = new StringBuilder();
        try (
             ResultSet rs = ds.getCnx().createStatement().executeQuery("SELECT * FROM article WHERE stock < 10")) {
            while (rs.next()) {
                String articleName = rs.getString("titre");
                int stockLevel = rs.getInt("stock");
                contentText.append(articleName).append(": ").append(stockLevel).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a system tray notification with the low stock articles
        Notifications.create()
                .title("Low Stock")
                .text(contentText.toString());*/
    }

    @FXML
    private void sendSMS(ActionEvent event) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21626845683"),
                new com.twilio.type.PhoneNumber("+16315291904"),
                "\"Hello,\n"
                + "We wanted to inform you about our latest products! Check out our new arrivals ! We think you'll love them. Don't forget to visit our application for more information. Thank you for choosing us for your needs. Best regards, Leaguestorm.\""
        ).create();

        System.out.println(message.getSid());
    }

    // Your Twilio account SID and auth token
    public static final String ACCOUNT_SID = "ACace7472778dc6883d3fdaa091bedc3c4";
    public static final String AUTH_TOKEN = "c0369c26e0d598b9423e0b40ee2e37f7";

}
