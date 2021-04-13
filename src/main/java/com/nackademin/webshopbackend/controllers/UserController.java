package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.User;
import com.nackademin.webshopbackend.repos.UserDAO;
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
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @GetMapping("/getusers")
    public List<User> getAllUsers(){
        UserDAO customerDB = new UserDAO();
        return customerDB.getAllUsers();
    }
}
