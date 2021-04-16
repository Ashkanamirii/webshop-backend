package com.nackademin.webshopbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:11 <br>
 * Project: webshop-back-end <br>
 */
@Entity(name="Orders")
@Table(name="orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName="id")
    @JsonBackReference
    private User userId;

    private int orderNumber;
    private String date;
    private Status status;

    private enum Status{
        PENDING,
        PRINTED,
        DELIVERED,
    };

    public Orders(){}

    public Orders(User userId, int orderNumber, String date, Status status) {
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.date = date;
        this.status = status;
    }
}

