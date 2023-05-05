/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author khair
 */
public class Order {
	
    int idO,status;
    Date createdAt;
    String orderStatus,delivery,stripe_session;
    float price ;

	int userid;
    public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Order(int idO, int userid,Date createdAt,int status,  String orderStatus,  
			 String stripe_session,String delivery,float price) {
		super();
		this.idO = idO;
		this.status = status;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.delivery = delivery;
		this.stripe_session = stripe_session;
		this.userid = userid;
		this.price = price;
	}

	
    public Order(int idO,Date createdAt, int status,  String orderStatus, String delivery, String stripe_session,
			float price) {
		super();
		this.idO = idO;
		this.status = status;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.delivery = delivery;
		this.stripe_session = stripe_session;
		this.price = price;
	}
	public String getStripe_session() {
		return stripe_session;
	}
	public void setStripe_session(String stripe_session) {
		this.stripe_session = stripe_session;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
    public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	private Map <String, OrderDetails> orderDetails;
private User user;
    private static Order INSTANCE; 

    public User getUser() {
        return user;
    }
    public static Order getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Order();
        }
        return INSTANCE;
    }
    public Order() {
              this.orderDetails = new HashMap<>();

    }

    public Order(Date createdAt, String orderStatus, float price,String delivery) {
		super();
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.price = price;
		this.delivery=delivery;
	}
	public Order(int idO,int status, Date createdAt, String orderStatus) {
        this.idO = idO;
        this.status = status;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }

    public Order(int idO, int status, Date createdAt, String orderStatus, int userid,
		  User user) {
		super();
		this.idO = idO;
		this.status = status;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.userid = userid;
		 
		this.user = user;
	}
	public Order(int orderId, String status, Date created_at, String delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

     public Map<String, OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<String, OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
    public int getIdO() {
        return idO;
    }

    public void setIdO(int idO ){
        this.idO = idO;
    }

    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

     

    public Order(int idO, int status, Date createdAt, String orderStatus, String delivery, int userid, float price,
			Map<String, OrderDetails> orderDetails, User user) {
		super();
		this.idO = idO;
		this.status = status;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.delivery = delivery;
		this.userid = userid;
		this.price = price;
		this.orderDetails = orderDetails;
		this.user = user;
	}
	@Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.idO != other.idO) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.orderStatus, other.orderStatus)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return true;
    }
	@Override
	public String toString() {
		return "Order [idO=" + idO + ", status=" + status + ", createdAt=" + createdAt + ", orderStatus=" + orderStatus
				+ ", delivery=" + delivery + ", stripe_session=" + stripe_session + ", price=" + price + ", userid="
				+ userid + ", orderDetails=" + orderDetails + ", user=" + user + "]";
	}
    
    
    
}
