package com.nackademin.webshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:44 <br>
 * Project: webshop-back-end <br>
 */
@Entity(name="OrderRow")
@Table(name="orderRow")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(targetEntity = Orders.class)
    @JoinColumn(name="order_id")
    private Orders order;

    private int quantity;

    private Status status;

    private enum Status{
        CONFIRMED,
        CANCELED,
        PAYING,
    };

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime  modifyDate;

    public OrderRow(){}


}
