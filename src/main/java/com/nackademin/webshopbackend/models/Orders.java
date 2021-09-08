package com.nackademin.webshopbackend.models;

import com.nackademin.webshopbackend.enumeration.OrderStatus;
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

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name="users_id")
    private Users users;
    private String date;
    private String status;
    private double totalPrice;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime  modifyDate;

    public Orders(){}

}

