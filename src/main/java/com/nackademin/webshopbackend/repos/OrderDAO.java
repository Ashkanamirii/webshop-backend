package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Order;
import com.nackademin.webshopbackend.models.User;
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
        list.add(new Order(1L, customers.get(0),1,LocalDate.parse("2014-03-12"),"Beställd"));
        list.add(new Order(2L, customers.get(1),2,LocalDate.parse("2018-01-01"), "Påbörjad"));
        list.add(new Order(3L, customers.get(2),3,LocalDate.parse("2020-05-30"), "Påbörjad"));
        list.add(new Order(4L, customers.get(3),4,LocalDate.parse("2020-02-20"), "Levererad"));
        return list;
    }
}
