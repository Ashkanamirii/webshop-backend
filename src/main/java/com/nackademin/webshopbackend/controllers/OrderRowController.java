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
@RestController
@RequestMapping(value = "/orderRow")
public class OrderRowController {
    @Autowired
    OrderRowService orderRowService;


    @GetMapping("/get")
    public List<OrderRow> getAllOrderRow(){
        return orderRowService.getAllOrderRow();
    }


    @PostMapping("/add")
    public OrderRow addOrderRow(@RequestBody OrderRow orderRow){
        return orderRowService.addOrderRow(orderRow);
    }

    @PostMapping("/add/list")
    public List<OrderRow> addOrderRowList(@RequestBody List<OrderRow> orderRows){
        return orderRowService.addOrderRowList(orderRows);
    }
}
