/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

/**
 *
 * @author khair
 */
public class CartItem {
     private Article product;
    private int quantity;
 private double discountedPrice;

    public CartItem(Article product, int quantity, double discountedPrice) {
        this.product = product;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
    }

    public CartItem(Article product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(int produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public  Article getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
