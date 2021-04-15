package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Order;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.OrderRowDAO;
import com.nackademin.webshopbackend.services.OrderService;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

//    @GetMapping("/getorderrows")
//    public List<OrderRow> getAllOrderRows(){
//        OrderRowDAO orderRowDB = new OrderRowDAO();
//        return orderRowDB.getAllOrderRows();
//    }
}
