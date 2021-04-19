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

    public Orders getOrderById(Long id) {
        return orderDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
    }

    public Orders addOrder(Orders order) {
        return orderDAO.save(order);
    }

    public List<Orders> addOrderList(List<Orders> orders) {
        return orderDAO.saveAll(orders);
    }

    public void removeOrderById(Long id) {
        orderDAO.deleteById(id);
    }

    public void removeAllOrders(){
        orderDAO.deleteAllInBatch();
    }

}
