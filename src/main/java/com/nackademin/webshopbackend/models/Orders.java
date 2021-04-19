package com.nackademin.webshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @ManyToOne(targetEntity = Users.class) // fetch = FetchType.LAZY
    @JoinColumn(name="users_id")
    private Users users;
    private String date;
    private Status status;

    private enum Status{
        PENDING,
        PRINTED,
        DELIVERED,
    };

    public Orders(){}

}

