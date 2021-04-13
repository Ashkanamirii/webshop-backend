package com.nackademin.webshopbackend.models;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:45 <br>
 * Project: webshop-back-end <br>
 */
public class InCart {

    private int id;
    private Product productId;
    private User customerId;

    public InCart(){}

    public InCart(int id, int quantity, Product productId, User customerId) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public User getCustomerId() {
        return customerId;
    }

    public void setCustomerId(User customerId) {
        this.customerId = customerId;
    }
}
