package com.nackademin.webshopbackend.models;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:45 <br>
 * Project: webshop-back-end <br>
 */
public class InCart {

    private Long id;
    private Product productId;
    private User customerId;

    public InCart(){}

    public InCart(Long id, Product productId, User customerId) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
