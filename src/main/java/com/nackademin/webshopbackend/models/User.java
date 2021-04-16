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
 * Time: 15:32 <br>
 * Project: webshop-back-end <br>
 */
@Entity(name="User")
@Table(name="user")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String number;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="addressId", referencedColumnName="id")
    @JsonBackReference
    private Address address;

    private int accountType;
    private boolean status;

    public User(){}

    public User(String email, String password, String firstname, String lastname, String number, Address address, int accountType, boolean status) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = number;
        this.address = address;
        this.accountType = accountType;
        this.status = status;
    }
}
