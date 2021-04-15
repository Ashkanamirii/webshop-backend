package com.nackademin.webshopbackend.models;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:44 <br>
 * Project: webshop-back-end <br>
 */
public class OrderRow {

    private Long id;
    private Product productId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="orderId", referencedColumnName="id")
    @JsonBackReference
    private Orders orderId;

    private int quantity;

    private Status status;

    private enum Status{
        CONFIRMED,
        CANCELED,
        PAYING,
    };

    public OrderRow(){}

    public OrderRow(Long id, Product productId, Order orderId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
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

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
