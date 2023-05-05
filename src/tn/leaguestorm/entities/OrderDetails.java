 
package tn.leaguestorm.entities;



/**
 *
 * @author khair
 */
public class OrderDetails {
 int id,quantity,idO;

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", quantity=" + quantity + ", idO=" + idO + ", total=" + total + ", product=" + product + '}';
    }

    public OrderDetails(int produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public int getIdO() {
	return idO;
}

public void setIdO(int idO) {
	this.idO = idO;
}

double total;
 Article product;

    public OrderDetails(int quantity, double total, Article product) {
	super();
	this.quantity = quantity;
	this.total = total;
	this.product = product;
}

	public OrderDetails() {
    }

    public OrderDetails(int quantity, String productName, double price, double total) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
      public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }

    }
    
 
     public  double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
 
     public Article getProduct() {
        return product;
    }

    public void setProduct(Article product) {
        this.product = product;
    }
 
}


