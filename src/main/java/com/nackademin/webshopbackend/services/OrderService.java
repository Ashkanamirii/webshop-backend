package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.repos.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:10 <br>
 * Project: webshop-back-end <br>
 */
@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public List<Orders> getAllOrders() {
        return orderDAO.findAll();
    }

    public void addOrder(Orders order) {
        orderDAO.save(order);
    }

    public void addOrderList(List<Orders> orders) {
        orderDAO.saveAll(orders);
    }
}
