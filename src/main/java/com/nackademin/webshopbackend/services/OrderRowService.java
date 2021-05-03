package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.repos.OrderRowDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:19
 * Project: webshop-backend
 * Copyright: MIT
 * Class that performs logic on OrderRow objects.
 */
@Service
public class OrderRowService {

    @Autowired
    OrderRowDAO orderRowDAO;

    public List<OrderRow> getAllOrderRow() {
        return orderRowDAO.findAll();
    }

    public OrderRow getOrderRowById(Long id) {
        return orderRowDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
    }

    public OrderRow addOrderRow(OrderRow orderRow) {
        return orderRowDAO.save(orderRow);
    }

    public List<OrderRow> addOrderRowList(List<OrderRow> orderRows) {
        return orderRowDAO.saveAll(orderRows);
    }

    public void removeOrderRows() {
        orderRowDAO.deleteAllInBatch();
    }

    public void removeOrderRowsById(Long id) {
        orderRowDAO.deleteById(id);
    }

    public void removeOrderRowsByOrderId(Long id) {
        orderRowDAO.deleteByOrderId(id);
    }

    public List<OrderRow> getByOrderId(Long orderId) {
        return orderRowDAO.findByOrderId(orderId);
    }
}
