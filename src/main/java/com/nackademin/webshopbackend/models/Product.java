package com.nackademin.webshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:40 <br>
 * Project: webshop-back-end <br>
 */

@Entity(name="Product")
@Table(name="product")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;
    private String unit;
    private String brand;
    private String image;
    private int quantity;
    private boolean isFeatured;

    @ManyToMany(targetEntity= Category.class)
    @JoinTable(name="product_category",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<Category> category;

    public Product(){}



}




