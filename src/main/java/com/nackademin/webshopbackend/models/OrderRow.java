package com.nackademin.webshopbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;


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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="productId", referencedColumnName="id")
    @JsonBackReference
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

}
