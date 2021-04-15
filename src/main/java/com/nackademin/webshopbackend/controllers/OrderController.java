package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.OrderRowDAO;
import com.nackademin.webshopbackend.services.OrderService;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Orders order){
        orderService.addOrder(order);
    }

    @PostMapping("/add/list")
    public void addOrderList(@RequestBody List<Orders> orders){
        orderService.addOrderList(orders);
    }
}
