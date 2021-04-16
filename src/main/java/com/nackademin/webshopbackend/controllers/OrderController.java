package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 */
//
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/get/id")
    public Orders getOrdersById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Orders order){
        orderService.addOrder(order);
    }

    @PostMapping("/add/list")
    public void addOrderList(@RequestBody List<Orders> orders){
        orderService.addOrderList(orders);
    }

    @PostMapping("/delete/id")
    public void deleteOrderById(@RequestParam Long id) {
        orderService.removeOrderById(id);
    }

    @PostMapping("/delete/all")
    public void deleteAllOrders(){
        orderService.removeAllOrders();
    }
}
