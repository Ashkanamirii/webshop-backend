package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_SUPER_ADMIN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-09-15 <br>
 * Time: 11:15 <br>
 * Project: webshop-backend <br>
 */
@DataJpaTest
class ITUserDAOTest {

    @Autowired
    UserDAO userDAO;

    @BeforeEach
    void init(){
        List<Users> users = Arrays.asList(
                new Users(null, "test1@test.com", "test1@test.com", "password1", "Anders", "Andersson",
                        "070-1122333", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                        new Address(null, "gatan 1", "12345", "Arboga", null, null),
                        true, true, null, null),
                new Users(null, "test2@test.com", "test2@test.com", "password2", "Bengt", "Bengtsson",
                        "070-4455666", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                        new Address(null, "gatan 2", "12345", "Bengtsfors", null, null),
                        true, true, null, null),
                new Users(null, "test3@test.com", "test3@test.com", "password3", "Clas", "Classon",
                        "070-7788999", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                        new Address(null, "gatan 3", "12345", "Carlshamn", null, null),
                        true, true, null, null)
        );
        userDAO.saveAll(users);
    }

    @AfterEach
    void clearDb() {
        userDAO.deleteAll();
    }

    @Test
    void findAllDataInDB(){
        List<Users> list = userDAO.findAll();
        assertEquals(3,list.size());
        assertNotEquals(2,list.size());
    }

    @Test
    void findByEmailAndPassword() {
        Users user = userDAO.findByEmailAndPassword("test1@test.com","password1");
        assertEquals("Andersson",user.getLastName());
        assertEquals("Anders",user.getFirstName());
        assertNotEquals("Bengt",user.getFirstName());
        assertNotEquals("Clas",user.getFirstName());
    }

    @Test
    void findByEmail() {
        Users user = userDAO.findByEmail("test2@test.com");
        assertEquals("Bengtsson",user.getLastName());
        assertEquals("Bengt",user.getFirstName());
        assertNotEquals("Andersson",user.getLastName());
        assertNotEquals("Classon",user.getLastName());
    }

    @Test
    void findByUsername() {
        Users user = userDAO.findByUsername("test3@test.com");
        assertEquals("Classon",user.getLastName());
        assertEquals("Clas",user.getFirstName());
        assertNotEquals("Andersson",user.getLastName());
        assertNotEquals("Bengtsson",user.getLastName());
    }

    @Test
    void findById() {
        Users user1 = userDAO.findByEmailAndPassword("test1@test.com","password1");
        Users user2 = userDAO.findById(user1.getId()).orElse(null);
        assertEquals(user1.getId(),user2.getId());
        assertEquals(user1.getLastName(),user2.getLastName());
        assertNotEquals(user1.getId(),user2.getId()+1);
    }
}