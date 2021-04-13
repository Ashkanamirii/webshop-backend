package com.nackademin.webshopbackend.repos;

import com.example.webshopbackend.models.Order;
import com.example.webshopbackend.models.OrderRow;
import com.example.webshopbackend.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 17:20 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public class OrderRowDAO {

    List<Product> products;
    List<Order> orders;

    public OrderRowDAO(){
        //ProductDAO productDB = new ProductDAO();
        OrderDAO orderDB = new OrderDAO();
        //products = productDB.getAllProducts();
        orders = orderDB.getAllOrders();
    }

    public List<OrderRow> getAllOrderRows(){
        List<OrderRow> list = new ArrayList<>();
        list.add(new OrderRow(1,products.get(0),orders.get(0),2));
        list.add(new OrderRow(2,products.get(1),orders.get(0),1));
        list.add(new OrderRow(3,products.get(3),orders.get(1),2));
        list.add(new OrderRow(4,products.get(1),orders.get(1),1));
        list.add(new OrderRow(5,products.get(3),orders.get(2),3));
        list.add(new OrderRow(6,products.get(4),orders.get(2),1));
        list.add(new OrderRow(7,products.get(0),orders.get(3),2));
        list.add(new OrderRow(8,products.get(4),orders.get(3),1));
        return list;
    }
}
