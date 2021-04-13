package com.nackademin.webshopbackend.repos;

import com.example.webshopbackend.models.Address;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:15 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public class AddressDAO {

    public List<Address> getAllAddresses() {
        List<Address> list = new ArrayList<>();
        list.add(new Address(1,"Stjärnvägen 20","11123","Göteborg"));
        list.add(new Address(2,"Torggatan 33","14667","Botkyrka"));
        list.add(new Address(3,"Stigen 34","13987","Botkyrka"));
        list.add(new Address(4,"Vasastan 53","55371","Stockholm"));
        list.add(new Address(5,"Torget 35","14555","Botkyrka"));
        list.add(new Address(6,"Torget 36","14555","Botkyrka"));
        list.add(new Address(7,"Admingatan 66","14555","Täby"));
        return list;
    }
}
