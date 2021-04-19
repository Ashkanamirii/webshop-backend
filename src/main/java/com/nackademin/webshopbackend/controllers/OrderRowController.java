package com.nackademin.webshopbackend.controllers;


import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.services.OrderRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:18
 * Project: webshop-backend
 * Copyright: MIT
 */
//
@RestController
@RequestMapping(value = "/orderRow")
public class OrderRowController {
    @Autowired
    OrderRowService orderRowService;


    @GetMapping("/get")
    public List<OrderRow> getAllOrderRow(){
        return orderRowService.getAllOrderRow();
    }

    @GetMapping("/get/id")
    public OrderRow getOrderRowById(Long id){
        return orderRowService.getOrderRowById(id);
    }

    @PostMapping("/add")
    public OrderRow addOrderRow(@RequestBody OrderRow orderRow){
        return orderRowService.addOrderRow(orderRow);
    }

    @PostMapping("/add/list")
    public List<OrderRow> addOrderRowList(@RequestBody List<OrderRow> orderRows){
       return orderRowService.addOrderRowList(orderRows);
    }

    @PostMapping("/delete/id")
    public void deleteOrderRowById(Long id){
        orderRowService.removeOrderRowsById(id);
    }

    @PostMapping("/delete/orderid")
    public void deleteOrderRowsByOrderId(Long id){
        orderRowService.removeOrderRowsByOrderId(id);
    }

    @PostMapping("/delete/all")
    public void deleteOrderRowList(){
        orderRowService.removeOrderRows();
    }
}
