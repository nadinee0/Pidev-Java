 
package tn.leaguestorm.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.OrderDetails;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author khair
 */
public class OrderService {

    private MyConnection ds = MyConnection.getInstance();
    PreparedStatement stmt = null;
    public void createOrder(Order order) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        String query = "INSERT INTO orders (user_id, created_at, status, order_status,stripe_session, delivery, price) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ds.getCnx().prepareStatement(query)) {
            stmt.setInt(1, order.getUserid());
            stmt.setTimestamp(2, timestamp);
            stmt.setInt(3, order.getStatus());
            stmt.setString(4, order.getOrderStatus());
            stmt.setString(5, order.getStripe_session());
            stmt.setString(6, order.getDelivery());
            stmt.setDouble(7, order.getPrice());
            stmt.executeUpdate();
            System.out.println("Order added successfully.");
            /*String subject = "New order";
           // String body = "Hello,\n\nYour order is being processed.\n\nBest regards,\nSupport team";
            sendEmail(order.getEmail(), subject, body);*/
        } catch (SQLException ex) {
            System.out.println("Error while adding order: " + ex.getMessage());
        }
    }

    public void UpdateStatus(Order o) throws SQLException {
        String req = "UPDATE `orders` SET `order_status` = '" + o.getOrderStatus() + "' WHERE `id` = " + o.getIdO();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

   
    public ObservableList<Order> afficherTous() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = ds.getCnx().prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                Date createdAt = resultSet.getDate("created_at");
                int status = resultSet.getInt("status");
                String orderStatus = resultSet.getString("order_status");
                String stripeSession = resultSet.getString("stripe_session");
                String delivery = resultSet.getString("delivery");
                float price = resultSet.getFloat("price");


                Order order = new Order(id, userId, createdAt, status, orderStatus, stripeSession,delivery,price);
                orders.add(order);
                System.out.println(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public ObservableList<Order> displayUserOrders(int userId) throws SQLException {
    	 ObservableList<Order> orders = FXCollections.observableArrayList();
    	 try {
    // Get the user's orders
    PreparedStatement ordersStmt = ds.getCnx().prepareStatement("SELECT * FROM orders WHERE user_id = ? ");
    ordersStmt.setInt(1, userId);
    ResultSet ordersResult = ordersStmt.executeQuery();

    while (ordersResult.next()) {
        int id = ordersResult.getInt("id");
         Date createdAt = ordersResult.getDate("created_at");
        int status = ordersResult.getInt("status");
        String orderStatus = ordersResult.getString("order_status");
        String stripeSession = ordersResult.getString("stripe_session");
        String delivery = ordersResult.getString("delivery");
        float price = ordersResult.getFloat("price");


        Order order = new Order(id,createdAt, status, orderStatus, stripeSession,delivery,price);
        orders.add(order);
        System.out.println(order.toString());
    }
} catch (SQLException e) {
    e.printStackTrace();
}
return orders;
}
 public Order getOrderItem(int idO) {
        Order o = new Order();
        try {
            String req = "SELECT * FROM Orders WHERE idO = ?";
            PreparedStatement ordersStmt = ds.getCnx().prepareStatement(req);
            ordersStmt .setInt(1,idO);
            ResultSet rs = ordersStmt .executeQuery();
            rs.next();
            o.setIdO(rs.getInt("id"));
             
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return o;
        
         
    }

public int getIdOrder() throws SQLException{
	   int lastId = 0;
	    String query = "SELECT id FROM orders ORDER BY id DESC LIMIT 1;";
	    try (PreparedStatement stmt = ds.getCnx().prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            lastId = rs.getInt("id");
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error while getting last order id: " + ex.getMessage());
	    }
	    return lastId;
	}

public void update(Order o)throws SQLException{ 
	 String query = "UPDATE orders SET status = ?, order_status = ?, stripe_session = ?, delivery = ?, price = ? WHERE id = ?";
	    try (PreparedStatement stmt = ds.getCnx().prepareStatement(query)) {
	        stmt.setInt(1, o.getStatus());
	        stmt.setString(2, o.getOrderStatus());
	        stmt.setString(3, o.getStripe_session());
	        stmt.setString(4, o.getDelivery());
	        stmt.setFloat(5, o.getPrice());
	        stmt.setInt(6, o.getIdO());

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Order updated successfully.");
	        } else {
	            System.out.println("No order found with id " + o.getIdO());
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error while updating order: " + ex.getMessage());
	    }
	}

    public void UpdatePayement(Order order) {
         String query = "UPDATE orders SET status =1 WHERE id = ?";
	    try (PreparedStatement stmt = ds.getCnx().prepareStatement(query)) {
	        stmt.setInt(1, order.getStatus());
	      

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Order updated successfully.");
	        } else {
	            System.out.println("No order found with id " + order.getIdO());
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error while updating order: " + ex.getMessage());
	    }
     }
 
}
