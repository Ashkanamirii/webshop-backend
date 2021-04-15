package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:17
 * Project: webshop-backend
 * Copyright: MIT
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    AddressService addressService;


    @GetMapping("/get")
    public List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @PostMapping("/add")
    public void addAddress(@RequestBody Address Address){
        addressService.addAddress(Address);
    }

    @PostMapping("/add/list")
    public void addAddresses(@RequestBody List<Address> Addresses){
        addressService.addAddresses(Addresses);
    }

}
