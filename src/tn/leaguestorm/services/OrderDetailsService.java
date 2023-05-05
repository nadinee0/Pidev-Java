 
package tn.leaguestorm.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.OrderDetails;
import tn.leaguestorm.utils.MyConnection;

/** 
 *
 * @author khair
 */
public class OrderDetailsService  {

    private MyConnection ds = MyConnection.getInstance();
    PreparedStatement stmt = null;
public int getOrderDetailsId(int orderId, int productId) throws SQLException {
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  int orderDetailsId = 0;
  
  try {
    
    String sql = "SELECT id FROM order_details WHERE order_ref_id = ? AND product_id = ?";
    stmt = ds.getCnx().prepareStatement(sql);
    stmt.setInt(1, orderId);
    stmt.setInt(2, productId);
    rs = stmt.executeQuery();
    
    if (rs.next()) {
      orderDetailsId = rs.getInt("id");
                           System.out.println("id:"+orderDetailsId);
                           return orderDetailsId;

    }
  }  
  catch(Exception ex){
      System.out.println("err");
  }
  return 0;
}

    
               public void updateqt(OrderDetails order,int idProduct) throws SQLException {
                    PreparedStatement selectStmt = null;
  PreparedStatement updateStmt = null;
  ResultSet rs = null;
  
                 String selectSql = "SELECT quantity FROM order_details WHERE id = ?";
    selectStmt = ds.getCnx().prepareStatement(selectSql);
    selectStmt.setInt(1, order.getId());
    rs = selectStmt.executeQuery();
    
    int oldQuantity = 0;
    if (rs.next()) {
      oldQuantity = rs.getInt("quantity");
    }
    
  String updateSql = "UPDATE order_details SET quantity = ?, total = ? WHERE order_ref_id = ? AND product_id = ? AND id = ?";
updateStmt = ds.getCnx().prepareStatement(updateSql);
updateStmt.setInt(1, order.getQuantity()+oldQuantity);
updateStmt.setDouble(2, order.getProduct().getPrix()*(order.getQuantity()+oldQuantity));
updateStmt.setInt(3, order.getIdO());
updateStmt.setInt(4, idProduct);
updateStmt.setInt(5, order.getId());
updateStmt.executeUpdate();

    
    System.out.println("La quantité de produit " + idProduct + " est passée de " + oldQuantity + " à " + order.getQuantity() + ".");
               }

public float  calc(int id)throws SQLException {
    String sql = "SELECT SUM(total) AS total FROM order_details WHERE order_ref_id = ?";
PreparedStatement stmt = ds.getCnx().prepareStatement(sql);
stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
    float total = rs.getFloat("total");
    return total;
} else {
    return 0;
}

}
    public void addItem(int productId, int quantity, int userId) throws SQLException {
        // Get the user ID from the session
        // int userId = (int) session.getAttribute("user_id");

        // Check if the product is already in the cart
        PreparedStatement checkCartStmt = ds.getCnx().prepareStatement("SELECT * FROM orders WHERE user_id = ? AND order_status = 'cart'");
        checkCartStmt.setInt(1, userId); // replace 1 with actual user ID
        ResultSet checkCartResult = checkCartStmt.executeQuery();
        int orderId;
        if (checkCartResult.next()) {
            // If the cart already exists, get the order ID
            orderId = checkCartResult.getInt("id");
        } else {
            // If the cart is empty, create a new order and get the order ID
            PreparedStatement insertOrderStmt = ds.getCnx().prepareStatement("INSERT INTO orders (user_id, created_at, status, order_status,delivery) VALUES (?, NOW(), 0, 'cart','notYet')", Statement.RETURN_GENERATED_KEYS);
            insertOrderStmt.setInt(1, userId); // replace 1 with actual user ID
            insertOrderStmt.executeUpdate();
            ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
            generatedKeys.next();
            orderId = generatedKeys.getInt(1);
        }

        // Check if the product is already in the cart
        PreparedStatement checkStmt = ds.getCnx().prepareStatement("SELECT * FROM order_details WHERE order_ref_id = ? AND product_id = ?");
        checkStmt.setInt(1, orderId);
        checkStmt.setInt(2, productId);
        ResultSet checkResult = checkStmt.executeQuery();
        if (checkResult.next()) {
            // If it is, just update the quantity
            int cartItemId = checkResult.getInt("id");
            int currentQuantity = checkResult.getInt("quantity");
            int newQuantity = currentQuantity + quantity;
            PreparedStatement updateStmt = ds.getCnx().prepareStatement("UPDATE order_details SET quantity = ? WHERE id = ?");
            updateStmt.setInt(1, newQuantity);
            updateStmt.setInt(2, cartItemId);
            updateStmt.executeUpdate();
        } else {
            // If not, add the new item to the cart
            PreparedStatement insertItemStmt = ds.getCnx().prepareStatement("INSERT INTO order_details (order_ref_id, product_id, quantity, total) VALUES (?, ?, ?, (SELECT prix FROM article WHERE id = ?) * ?)");
            insertItemStmt.setInt(1, orderId);
            insertItemStmt.setInt(2, productId);
            insertItemStmt.setInt(3, quantity);
            insertItemStmt.setInt(4, productId);
            insertItemStmt.setInt(5, quantity);
            insertItemStmt.executeUpdate();
        }
    }

    
    public void removeItem(int orderId, int productId) throws SQLException {
        // Check if the product is in the cart
        PreparedStatement checkStmt = ds.getCnx().prepareStatement("SELECT * FROM order_details WHERE order_ref_id = ? AND product_id = ?");
        checkStmt.setInt(1, orderId);
        checkStmt.setInt(2, productId);
        ResultSet checkResult = checkStmt.executeQuery();

        if (checkResult.next()) {
            // If it is, delete the cart item
            int cartItemId = checkResult.getInt("id");
            PreparedStatement deleteStmt = ds.getCnx().prepareStatement("DELETE FROM order_details WHERE id = ?");
            deleteStmt.setInt(1, cartItemId);
            deleteStmt.executeUpdate();
        }
    }

    public void clearCart(int userId) throws SQLException {
        // Get the user ID from the session
        // int userId = (int) session.getAttribute("user_id");

        // Find the current cart order for the user
        PreparedStatement getCartStmt = ds.getCnx().prepareStatement("SELECT id FROM orders WHERE user_id = ? AND order_status = 'cart'");
        getCartStmt.setInt(1, userId); // replace 1 with actual user ID
        ResultSet cartResult = getCartStmt.executeQuery();
        if (cartResult.next()) {
            int orderId = cartResult.getInt("id");

            // Delete all items from the order_details table associated with the current cart order
            PreparedStatement clearCartStmt = ds.getCnx().prepareStatement("DELETE FROM order_details WHERE order_ref_id = ?");
            clearCartStmt.setInt(1, orderId);
            clearCartStmt.executeUpdate();
        }
    }

    public void displayCart(int orderId) throws SQLException {
        // Get the cart items for the user
        PreparedStatement cartStmt = ds.getCnx().prepareStatement("SELECT od.quantity, a.titre, a.prix, (od.quantity * a.prix) as total FROM order_details od JOIN article a ON od.product_id = a.id WHERE od.order_ref_id = ?");
        cartStmt.setInt(1, orderId);
        ResultSet cartResult = cartStmt.executeQuery();

        // Display the cart items
        System.out.println("Cart items for order " + orderId + ":");
        while (cartResult.next()) {
            int quantity = cartResult.getInt("quantity");
            String productName = cartResult.getString("titre");
            double price = cartResult.getDouble("prix");
            double total = cartResult.getDouble("total");

            System.out.println(quantity + " x " + productName + " (" + price + " each) = " + total);
        }
    }
    /*public void displayCart() throws SQLException {
    // Get the user ID from the session
    int userId = (int) session.getAttribute("user_id");

    // Get the cart items for the user and order
    PreparedStatement cartStmt = ds.getCnx().prepareStatement("SELECT od.quantity, a.titre, a.prix, (od.quantity * a.prix) as total FROM order_details od JOIN article a ON od.product_id = a.id JOIN orders o ON od.order_ref_id = o.id WHERE o.user_id = ? AND o.order_status = 'cart' AND od.order_ref_id = ?");
    cartStmt.setInt(1, userId);
    cartStmt.setInt(2, (int) session.getAttribute("order_id"));
    ResultSet cartResult = cartStmt.executeQuery();

    // Display the cart items
    System.out.println("Cart items:");
    while (cartResult.next()) {
        int quantity = cartResult.getInt("quantity");
        String productName = cartResult.getString("titre");
        double price = cartResult.getDouble("prix");
        double total = cartResult.getDouble("total");

        System.out.println(quantity + " x " + productName + " (" + price + " each) = " + total);
    }
}
     */
    OrderDetails od;

    public void increaseQuantity() throws SQLException {

        od.increaseQuantity();
    }

    public void decreaseQuantity() throws SQLException {
        od.decreaseQuantity();
    }
    public int createfirst(int id1) throws SQLException{
    	int orderId = 0;
		  LocalDateTime now = LocalDateTime.now();
	        Timestamp timestamp = Timestamp.valueOf(now);
		    String query = "INSERT INTO orders (created_at,user_id) VALUES (?,?)";
		    try (PreparedStatement stmt = ds.getCnx().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		    	  
		            stmt.setTimestamp(1, timestamp);
		            stmt.setInt(2, id1);
		    	int rowsAffected = stmt.executeUpdate();
		        if (rowsAffected == 1) {
		            ResultSet rs = stmt.getGeneratedKeys();
		            if (rs.next()) {
		                orderId = rs.getInt(1);
		                System.out.println("New order created with id: " + orderId);
		            }
		        }
		    } catch (SQLException ex) {
		        System.out.println("Error while creating new order: " + ex.getMessage());
		    }
		    return  orderId;
    }

	public void insert(OrderDetails order) throws SQLException{
		  
		String query1 = "INSERT INTO order_details (product_id,order_ref_id,quantity,total) VALUES (?,?, ?, ?)";
        try (PreparedStatement stmt = ds.getCnx().prepareStatement(query1)) {
            stmt.setInt(1, order.getProduct().getId());
            stmt.setInt(2, order.getIdO() );
            stmt.setInt(3, order.getQuantity());
            stmt.setDouble(4, order.getTotal());
            stmt.executeUpdate();
                        System.out.println(order.getTotal());

            System.out.println("Order added successfully.");
         
        } catch (SQLException ex) {
            System.out.println("Error while adding order: " + ex.getMessage());
        }		
	}

}
