package com.nackademin.webshopbackend.controllers;

import com.example.webshopbackend.models.Order;
import com.example.webshopbackend.models.OrderRow;
import com.example.webshopbackend.repos.OrderDAO;
import com.example.webshopbackend.repos.OrderRowDAO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 */
@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderController {

    @GetMapping("/getorders")
    public List<Order> getAllOrders(){
        OrderDAO orderDB = new OrderDAO();
        return orderDB.getAllOrders();
    }

    @GetMapping("/getorderrows")
    public List<OrderRow> getAllOrderRows(){
        OrderRowDAO orderRowDB = new OrderRowDAO();
        return orderRowDB.getAllOrderRows();
    }
}
