package com.nackademin.webshopbackend.models;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:53 <br>
 * Project: webshop-back-end <br>
 */
public class Address {

    private int id;
    private String street;
    private String zipcode;
    private String city;

    public Address(){}

    public Address(int id, String street, String zipcode, String city) {
        this.id = id;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
