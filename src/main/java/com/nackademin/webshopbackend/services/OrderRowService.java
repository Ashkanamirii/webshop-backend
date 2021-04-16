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
 */
@Service
public class OrderRowService {

    @Autowired
    OrderRowDAO orderRowDAO;

    public List<OrderRow> getAllOrderRow() {
        return orderRowDAO.findAll();
    }

    public void addOrderRow(OrderRow orderRow) {
        orderRowDAO.save(orderRow);
    }

    public void addOrderRowList(List<OrderRow> orderRows) {
        orderRowDAO.saveAll(orderRows);
    }
}