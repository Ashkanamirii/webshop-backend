package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_SUPER_ADMIN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-09-15 <br>
 * Time: 13:44 <br>
 * Project: webshop-backend <br>
 */
@DataJpaTest
class OrderDAOTest {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    UserDAO userDAO;

    @BeforeEach
    void init(){
        Users user1 = new Users(null, "test1@test.com", "test1@test.com", "password1", "Anders", "Andersson",
                "070-1122333", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                new Address(null, "gatan 1", "12345", "Arboga", null, null),
                true, true, null, null);
        Users user2 = new Users(null, "test2@test.com", "test2@test.com", "password2", "Bengt", "Bengtsson",
                "070-4455666", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                new Address(null, "gatan 2", "12345", "Bengtsfors", null, null),
                true, true, null, null);

        user1 = userDAO.save(user1);
        user2 = userDAO.save(user2);

        List<Orders> orders = Arrays.asList(
                new Orders(null,user1,"2021-08-08",null,100,null,null),
                new Orders(null,user1,"2021-09-08",null,200,null,null),
                new Orders(null,user2,"2021-10-08",null,300,null,null)
        );

        orderDAO.saveAll(orders);
    }

    @AfterEach
    void clearDb() {
        orderDAO.deleteAll();
        userDAO.deleteAll();
    }

    @Test
    void findAllInDB(){
        List<Orders> orders = orderDAO.findAll();
        assertEquals(3,orders.size());
        assertNotEquals(2,orders.size());
    }

    @Test
    void findByUsersId() {
        Users user1 = userDAO.findByUsername("test1@test.com");
        Users user2 = userDAO.findByUsername("test2@test.com");

        List<Orders> orders1 = orderDAO.findByUsersId(user1.getId());
        List<Orders> orders2 = orderDAO.findByUsersId(user2.getId());

        assertEquals(2,orders1.size());
        assertNotEquals(1,orders1.size());

        assertEquals(1,orders2.size());
        assertNotEquals(2,orders2.size());
    }
}