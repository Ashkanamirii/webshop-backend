package com.nackademin.webshopbackend.repos;

import com.example.webshopbackend.models.Order;
import com.example.webshopbackend.models.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:59 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public class OrderDAO {

    List<User> customers;

    public OrderDAO(){
        UserDAO customersDB = new UserDAO();
        customers = customersDB.getAllUsers();
    }

    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        list.add(new Order(1, customers.get(0),1,LocalDate.parse("2014-03-12"),"Beställd"));
        list.add(new Order(2, customers.get(1),2,LocalDate.parse("2018-01-01"), "Påbörjad"));
        list.add(new Order(3, customers.get(2),3,LocalDate.parse("2020-05-30"), "Påbörjad"));
        list.add(new Order(4, customers.get(3),4,LocalDate.parse("2020-02-20"), "Levererad"));
        return list;
    }
}
