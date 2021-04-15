package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.repos.AddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:19
 * Project: webshop-backend
 * Copyright: MIT
 */
@Service
public class AddressService {

    @Autowired
    AddressDAO addressDAO;

    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }


    public void addAddress(Address address) {
        addressDAO.save(address);
    }

    public void addAddresses(List<Address> addresses) {
        addressDAO.saveAll(addresses);
    }
}
