
package tn.leaguestorm.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.leaguestorm.entities.Address;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.CartItem;
import tn.leaguestorm.entities.MyListener;
import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.OrderDetails;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static tn.leaguestorm.gui.LeagueStorm.CURRENCY;

import tn.leaguestorm.services.AddressService;
import tn.leaguestorm.services.OrderDetailsService;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ShopController implements Initializable {

    private VBox chosenFruitCard;

    private Label fruitNameLable;

    private Label fruitPriceLabel;

    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Article> articles = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private ImageView Img;
    Article a = new Article();
    private MyConnection ds = MyConnection.getInstance();
    ServiceArticle sa = new ServiceArticle();
    @FXML
    private VBox chosenCard;

private AnchorPane parentAnchorPane;
    @FXML
    private Button profile;
    @FXML
    private Button addToCart;
    @FXML
    private Button cart;
    
    
   /* public void populateGridPane() throws SQLException, FileNotFoundException {

        String query = "SELECT * FROM article";

// Execute the query and retrieve the result set
        Statement statement = ds.getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // Define the column and row indexes for the GridPane
        int colIndex = 0;
        int rowIndex = 0;

// Iterate through the result set and add each product to the GridPane
        while (resultSet.next()) {
            // Create a new Label for the product name
            /*  Label productName = new Label(resultSet.getString("titre"));
            grid.add(productName, colIndex, rowIndex);
             /
            String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + resultSet.getString("image"); // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels
            grid.add(imageView, colIndex, rowIndex + 1);

            Tooltip tooltip = new Tooltip(resultSet.getString("titre") + " - " + resultSet.getString("prix"));
            Tooltip.install(imageView, tooltip);

// Add the ImageView to the GridPane
            grid.add(imageView, colIndex, rowIndex + 1);

            /*  Label price = new Label(resultSet.getString("prix"));
            grid.add(price, colIndex, rowIndex + 1); /
            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 2;
            }
        }
    }
*/
public void populateGridPane() throws SQLException, FileNotFoundException {
    String query = "SELECT * FROM article";
    
    // Execute the query and retrieve the result set
    Statement statement = ds.getCnx().createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    
    // Define the column and row indexes for the GridPane
    int colIndex = 0;
    int rowIndex = 0;
    
    // Iterate through the result set and add each product to the GridPane
    while (resultSet.next()) {
    	
        // Create a new Label for the product name
        Label productName = new Label(resultSet.getString("titre"));
        grid.add(productName, colIndex, rowIndex + 1);
        
        // Create a new Label for the product price
        Label price = new Label(resultSet.getString("prix"));
        grid.add(price, colIndex, rowIndex + 2);

        // Create a new ImageView for the product image
        String imagePath = "D:/malek/upload/" + resultSet.getString("image"); // Replace this with your own file path and column name
      
        Image image = new Image(new File(imagePath).toURI().toString());

        ImageView imageView = new ImageView(image);
        imageView.setBlendMode(BlendMode.MULTIPLY);

        imageView.setFitWidth(200); // Set the width to 200 pixels
        imageView.setFitHeight(200); // Set the height to 200 pixels
        
        // Add a mouse click event handler to the ImageView
        int productId = resultSet.getInt("id");
        float prix=resultSet.getFloat("prix");
        int qt=resultSet.getInt("stock");
       // Get the product ID from the result set
        imageView.setOnMouseClicked(e -> {
        	 
     			selectedProduct=new Article(productId,productName.getText(),imagePath,prix,qt);
     		 
            // Pass the product ID to the method that will handle the click event
            handleProductClick(productId, productName.getText(), price.getText(), imagePath);
         
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

private int selectedProductId;
public Article selectedProduct;
private void handleProductClick(int productId, String productName, String price, String imagePath) {
    // Update the UI with the selected product details
    NameLabel.setText(productName);
    PriceLabel.setText(price);
    Img.setImage(new Image(new File(imagePath).toURI().toString()));
    //Img.setBlendMode(BlendMode.MULTIPLY);
    Img.setStyle("-fx-background-color: transparent");

    // Store the selected product ID
    selectedProductId = productId;
  
}


private void setChosenArticle(Article article) {
    NameLabel.setText(article.getTitre());
    PriceLabel.setText(CURRENCY + article.getPrix());
    Image image = new Image(getClass().getResourceAsStream(article.getImage()));
    ImageView imageView = new ImageView(image);
    imageView.setBlendMode(BlendMode.MULTIPLY);

    imageView.setFitWidth(200);
    imageView.setFitHeight(200);
    chosenCard.setStyle("-fx-background-color: #" + article.getColor() + ";\n"
            + "    -fx-background-radius: 30;");
    chosenCard.getChildren().clear();
    chosenCard.getChildren().addAll(imageView, NameLabel, PriceLabel);
}


  @Override
public void initialize(URL location, ResourceBundle resources) {
	  
    try {
        populateGridPane();
        if (articles.size() > 0) {
            setChosenArticle(articles.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article article) {
                    setChosenArticle(article);
                }
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("tn/leaguestorm/gui/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(articles.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (SQLException ex) {
        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 
  

    @FXML
    private void changeToProfile(ActionEvent event) throws IOException {
         FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Home.fxml", "Home");
    }  
	List<Article> cartProducts = new ArrayList<>();
	
		
	int k= create();
	public int create() {
		OrderDetailsService os1 = new OrderDetailsService();
		System.out.println("ok");
    	try {
 
    		return  os1.createfirst(CurrentUser.getUser().getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     return 0;
	}
    @FXML
    private void addToCart(ActionEvent event) {

     
    	if (selectedProduct != null) {
    	    
    	    // Create GridPane to hold product details
    	    GridPane productGrid = new GridPane();
    	    productGrid.setAlignment(Pos.CENTER);
    	    productGrid.setHgap(10);
    	    productGrid.setVgap(10);
    	    productGrid.setPadding(new Insets(20));

    	    // Add product image
    	    ImageView productImage = new ImageView(new Image(new File(selectedProduct.getImage()).toURI().toString()));
    	    productImage.setFitHeight(150);
    	    productImage.setFitWidth(150);
    	    productGrid.add(productImage, 0, 0);

    	    // Add product name
    	    Label productNameLabel = new Label(selectedProduct.getTitre());
    	    productNameLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
    	    productGrid.add(productNameLabel, 1, 0);
    	    OrderDetails od=new OrderDetails(1,selectedProduct.getPrix(),selectedProduct) ;
    	    // Add product price
    	    Label productPriceLabel = new Label("Total:"+selectedProduct.getPrix() + " USD");
    	    productPriceLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
    	    productPriceLabel.setTextFill(Color.GREEN); // Set text color to green
    	    productGrid.add(productPriceLabel, 1, 1);

    	    // Add product quantity
    	    Label productQuantityLabel = new Label("Quantity: " + od.getQuantity());
    	    productQuantityLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
    	    productGrid.add(productQuantityLabel, 1, 2);

    	    // Add plus and minus buttons
    	    Button plusButton = new Button("+");
    	    plusButton.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Set button color to green
    	    productGrid.add(plusButton, 2, 1);

    	    Button minusButton = new Button("-");
    	    minusButton.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Set button color to red
    	    productGrid.add(minusButton, 3, 1);
    	    plusButton.setOnAction(e -> {
    	        int quantity = od.getQuantity() + 1;
    	        od.setQuantity(quantity);
    	        productQuantityLabel.setText("Quantity: "+Integer.toString(quantity));
    	        float totalPrice = selectedProduct.getPrix() * quantity;
    	        productPriceLabel.setText("Total:"+totalPrice + " USD");
    	        od.setTotal(totalPrice);
    	    });
    	    minusButton.setOnAction(e -> {
    	        int quantity = od.getQuantity()- 1;
    	        if (quantity >= 0) {
    	            od.setQuantity(quantity);
    	            productQuantityLabel.setText("Quantity: "+Integer.toString(quantity));
    	            float totalPrice = selectedProduct.getPrix() * quantity;
    	            productPriceLabel.setText("Total:"+totalPrice + " USD");
    	            od.setTotal(totalPrice);
    	        }
    	    });
            

    	 
    	    // Create and show popup window
    	    Scene scene = new Scene(productGrid);
    	    Stage popupWindow = new Stage();
    	    popupWindow.initModality(Modality.APPLICATION_MODAL);
    	    popupWindow.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
    	    popupWindow.setScene(scene);
    	    popupWindow.showAndWait();
 boolean isProductFound = false;
     	            od.setIdO(k);

  
  // Parcourir les produits déjà ajoutés
  for (Article product : cartProducts) {
    if (product.getId() == selectedProduct.getId()) { // Vérifier si le produit sélectionné existe déjà
        	OrderDetailsService os = new OrderDetailsService();
    	         try{
                     int id=os.getOrderDetailsId(od.getIdO(), selectedProduct.getId());
                     System.out.println("id:"+id);
                     od.setId(id);
                     System.out.println("chof 9bal mnbdl "+od.toString());

    	            os.updateqt(od,selectedProduct.getId());
                   Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
    	            alert1.setTitle("Success");
    	            alert1.setHeaderText(null);
    	            alert1.setContentText("Order details added successfully.");
    	            alert1.showAndWait();
                 }catch(SQLException ex){
                     System.out.println("errr"+ex.getMessage());
                 }
       isProductFound = true;
      break;
    }
  }
  
  // Si le produit sélectionné n'existe pas, l'ajouter à la liste
  if (!isProductFound) {
      Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to add this order details?", ButtonType.YES, ButtonType.NO);
    	    
    	    Optional<ButtonType> result = alert.showAndWait();
    	    if (result.isPresent() && result.get() == ButtonType.YES) {
    	    	
    	        try {    	    
    	            System.out.println("ok");
    	        	OrderDetailsService os = new OrderDetailsService();
    	         
    	            os.insert(od);
                System.out.println(od.getTotal());

    	            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
    	            alert1.setTitle("Success");
    	            alert1.setHeaderText(null);
    	            alert1.setContentText("Order details added successfully.");
    	            alert1.showAndWait();
    	        } catch (Exception ex) {
    	            System.out.println("Error while adding order details: " + ex.getMessage());
    	        }
    	    } else {
    	        // cancel operation
    	    }

     cartProducts.add(selectedProduct);
  }    	        	}}
    	
    
        private List<CartItem> cartItems = new ArrayList<>();
/*
   private void addProductToCart(int produit) {
        // Verifier si le produit est deja  dans le panier
        cartItems.forEach((cartItem) -> {
            if (cartItem.getProduct().getId()==produit) {
                // Le produit est deja  dans le panier, incrementer la quantite
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                
                
            }
            else {
                cartItems.add(new CartItem(produit));
                
                // mettre Ã  jour le label du montant total
            }       });
   } 
   */
   @FXML
   private void gotoo(ActionEvent event) throws IOException {
	   
	   try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderUser.fxml"));
		    Parent root = loader.load();
		    
		    
		    
		    Scene scene = new Scene(root);
		    Stage stage = new Stage();
		    stage.setScene(scene);
		    stage.show();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
   }
   
  public float totalPrice1 = 0;
   @FXML
   private void cart(ActionEvent event) throws IOException {
	// Create a ListView to display the list of cart products
	   ListView<Article> cartListView = new ListView<>();
	   cartListView.getItems().addAll(cartProducts);
   	ObservableList<Article> produits = cartListView.getItems();

	   // Create a GridPane to hold product details
	   GridPane productGrid = new GridPane();
	   productGrid.setAlignment(Pos.CENTER);
	   productGrid.setHgap(10);
	   productGrid.setVgap(10);
	   productGrid.setPadding(new Insets(20));

	   // Method to update the total price label with new data
	   Label totalLabel = new Label();
	   updateTotalPrice(totalLabel, cartProducts);
	   totalLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
	   totalLabel.setTextFill(Color.GREEN);
	   productGrid.add(totalLabel, 0, cartProducts.size(), 3, 1);
 	  //  Article produit = cartListView.getSelectionModel().getSelectedItem();
	   cartListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	   // Add product image, name, price and remove button
	   for (int i = 0; i < cartProducts.size(); i++) {
	      Article article = cartProducts.get(i);
	      int rowIndex = i;

	      ImageView productImage = new ImageView(new Image(new File(article.getImage()).toURI().toString()));
	      productImage.setFitHeight(100);
	      productImage.setFitWidth(100);
	      productGrid.add(productImage, 0, rowIndex);
 	      Label productNameLabel = new Label(article.getTitre());
	      productNameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
	      productGrid.add(productNameLabel, 1, rowIndex);

	      Label productPriceLabel = new Label("Prix: " + article.getPrix() + " USD");
	      productPriceLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
	      productGrid.add(productPriceLabel, 2, rowIndex);

	      Button removeButton = new Button("-");
	      removeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
	      removeButton.setOnAction(e -> {
	    	  Article produit = cartListView.getSelectionModel().getSelectedItem();

	    	    if (produit != null) {
	    	        // Remove the selected product from the cartProducts list
	    	        cartProducts.remove(produit);
	    	        System.out.println(produit.toString());
	    	        // Remove the selected product from the cartListView
	    	        cartListView.getItems().remove(produit);

	    	        // Update the cartListView and totalLabel
	    	        updateCartListView(cartListView, cartProducts);
	    	        updateTotalPrice(totalLabel, cartProducts);
	    	    }
	    	    else {	    	        System.out.println("is null");

	    	    }
	    	    
	    	});


	      productGrid.add(removeButton, 3, rowIndex);
	   }
	   
       cartListView.refresh();
	   // Add checkout button
	   Button checkoutButton = new Button("Passer commande");
	   checkoutButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
	   productGrid.add(checkoutButton, 0, cartProducts.size() + 1, 3, 1);
	   checkoutButton.setOnAction(e -> {
		    AddressService sc=new AddressService();
		    ObservableList<Address> userAddresses = sc.getAll().stream()
	                .filter(address -> address.getUserId() == CurrentUser.getUser().getId())
	                .collect(Collectors.toCollection(FXCollections::observableArrayList));
		// Create a new dialog to show the address list
		   Dialog<Address> dialog = new Dialog<>();
		   dialog.setTitle("Select an address");

		   // Set the dialog buttons
		   ButtonType selectButton = new ButtonType("Select", ButtonData.OK_DONE);
		   dialog.getDialogPane().getButtonTypes().addAll(selectButton, ButtonType.CANCEL);

		   // Create the address combo box
		   ComboBox<Address> addressComboBox = new ComboBox<>();
		   addressComboBox.setItems(userAddresses);
		   addressComboBox.setConverter(new StringConverter<Address>() {
			    @Override
			    public String toString(Address address) {
			        if (address == null) {
			            return "";
			        }
			        return address.getAddress();
			    }

			    @Override
			    public Address fromString(String string) {
			        // Recherche de l'adresse correspondant à la chaîne de texte saisie
			        for (Address address : addressComboBox.getItems()) {
			            if (address.getAddress().equals(string)) {
			                return address;
			            }
			        }
			        return null;
			    }
			});

	 

		   // Add the address combo box to the dialog
		   dialog.getDialogPane().setContent(addressComboBox);

		   // Set the result converter to return the selected address
		   dialog.setResultConverter(dialogButton -> {
		       if (dialogButton == selectButton) {
		           return addressComboBox.getSelectionModel().getSelectedItem();
		       }
		       return null;
		   });

		   // Show the dialog and wait for the user to select an address
		   Optional<Address> result = dialog.showAndWait();
		   if (result.isPresent()) {
			   Calendar calendar = Calendar.getInstance();  // get instance of Calendar
			   Date now = calendar.getTime();  // get current date and time
		       // Create a new order with the selected address
		       Order commande = new Order(now,"Commande Passe", totalPrice1, result.get().getAddress());
		   System.out.println(commande.toString());
		    
		   try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
			    Parent root = loader.load();
			    
			    // Get the controller of the Cart.fxml file
			    CartController cartController = loader.getController();
			    
			    // Pass the commande object to the CartController
			    cartController.setCommande(commande);
			    
			    Scene scene = new Scene(root);
			    Stage stage = new Stage();
			    stage.setScene(scene);
			    stage.show();
			} catch (IOException ex) {
			    ex.printStackTrace();
			}
		   }

	   });

	   // Create a VBox to hold the GridPane and a close button
	   VBox cartBox = new VBox();
	   cartBox.setSpacing(10);
	   cartBox.setPadding(new Insets(10));
	   cartBox.getChildren().addAll(productGrid);

	   // Create a new scene and set it on a new stage
	   Scene cartScene = new Scene(cartBox);
	   Stage cartStage = new Stage();
	   cartStage.setScene(cartScene);
	   cartStage.show();
	

   }

   public void updateCartListView(ListView<Article> cartListView, List<Article> cartProducts) {
       cartListView.getItems().clear();
       cartListView.getItems().addAll(cartProducts);
   }

   public void updateTotalPrice(Label label, List<Article> data) {
       try{
       OrderDetailsService os=new OrderDetailsService();
	    float totalPrice = os.calc(k);
	    
	    label.setText("Prix total: " + totalPrice + " USD");
	   this.totalPrice1=totalPrice;
	   }
       catch(SQLException ex){
           System.err.println(ex.toString());
       }
   }

}
