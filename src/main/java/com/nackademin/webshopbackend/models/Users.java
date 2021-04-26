package com.nackademin.webshopbackend.models;

import com.sun.istack.NotNull;
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
 * Time: 15:32 <br>
 * Project: webshop-back-end <br>
 */
@Entity(name="Users")
@Table(name="users")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "email", unique = true)
    @NotNull

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String number;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    private int accountType;
    private boolean status;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime  modifyDate;

    public Users(){}

}
