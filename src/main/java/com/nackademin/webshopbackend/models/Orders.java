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

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    private int orderNumber;
    private String date;
    private Status status;

    private enum Status{
        PENDING,
        PRINTED,
        DELIVERED,
    };

    public Orders(){}

}

