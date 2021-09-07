package com.nackademin.webshopbackend.controllers;


import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.services.OrderRowService;
import com.nackademin.webshopbackend.services.OrderService;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:18
 * Project: webshop-backend
 * Copyright: MIT
 * Controller for calls to orderRow urls.
 * Logic is performed in OrderRowService.
 */
@RestController
@RequestMapping(value = "/orderRow")
@CrossOrigin("*")
public class OrderRowController {

    @Autowired
    OrderRowService orderRowService;

    @Autowired
    OrderService orderService;

    @Autowired
    private ProductService productService;

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
    public ResponseEntity<Object> addOrderRowList(@RequestBody List<OrderRow> orderRows){

        List<OrderRow> correctInStock = productService.checkQuantity(orderRows);

        if(correctInStock.isEmpty()){ // Om ingenting fanns i lager
            orderService.removeOrderById(orderRows.get(0).getOrder().getId());
            return ResponseEntity.badRequest().body("Lagersaldona var mindre i lager än i beställningen");
        }
        else if(orderRows.isEmpty()){ // Om allt går bra
            List<OrderRow> or = orderRowService.addOrderRowList(correctInStock);
            return ResponseEntity.ok(or);
        }
        else{ // Om bara några saker finns i lager
            // Uppdatera totalpriset
            double totalPrice = 0;
            for(OrderRow or : correctInStock){
                double p = or.getQuantity() * or.getProductPriceWhenOrdering();
                totalPrice += p;
            }
            Orders order = orderService.getOrderById(orderRows.get(0).getOrder().getId());
            if (totalPrice < 250){
                totalPrice = totalPrice + 49;
            }
            order.setTotalPrice(totalPrice);
            try{
                orderService.addOrder(order);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            orderRowService.addOrderRowList(correctInStock);

            return ResponseEntity.ok(orderRows);
        }
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

    @GetMapping("/get/byOrderID/{orderId}")
    public List<OrderRow> getByOrderId(@PathVariable Long orderId){
        return orderRowService.getByOrderId(orderId);
    }
}
