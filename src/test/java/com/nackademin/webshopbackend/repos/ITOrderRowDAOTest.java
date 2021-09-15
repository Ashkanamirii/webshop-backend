package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_SUPER_ADMIN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-09-15 <br>
 * Time: 13:02 <br>
 * Project: webshop-backend <br>
 */
@DataJpaTest
class ITOrderRowDAOTest {

    @Autowired
    OrderRowDAO orderRowDAO;

    @Autowired
    OrderDAO orderDAO;

    @BeforeEach
    void init(){
        Orders order = new Orders(null,null,"2021-05-22",null,1000,null,null);
        order = orderDAO.save(order);
        List<OrderRow> orderRows = Arrays.asList(
                new OrderRow(null,null,order,10,100,null,null,null),
                new OrderRow(null,null,null,20,200,null,null,null),
                new OrderRow(null,null,null,30,300,null,null,null)
        );
        orderRowDAO.saveAll(orderRows);
    }

    @AfterEach
    void clearDb() {
        orderRowDAO.deleteAll();
        orderDAO.deleteAll();
    }

    @Test
    void findAllInDB(){
        List<OrderRow> list = orderRowDAO.findAll();
        System.out.println("Listan: "+list);
        assertEquals(3,list.size());
        assertEquals(600,list.stream().flatMapToDouble(l -> DoubleStream.of(l.getProductPriceWhenOrdering())).sum());
    }

    @Test
    void deleteByOrderId() {
        List<OrderRow> orderRowsWith3 = orderRowDAO.findAll();
        assertEquals(3,orderRowsWith3.size());
        assertNotEquals(2,orderRowsWith3.size());

        List<Orders> orders = orderDAO.findAll();
        Long id = orders.get(0).getId();

        orderRowDAO.deleteByOrderId(id);

        List<OrderRow> orderRowsWith2 = orderRowDAO.findAll();
        assertEquals(2,orderRowsWith2.size());
        assertNotEquals(3,orderRowsWith2.size());
    }

    @Test
    void findByOrderId() {
        List<Orders> orders = orderDAO.findAll();
        Long id = orders.get(0).getId();
        List<OrderRow> orderRowList = orderRowDAO.findByOrderId(id);
        assertEquals(1,orderRowList.size());
        assertNotEquals(0,orderRowList.size());

    }
}