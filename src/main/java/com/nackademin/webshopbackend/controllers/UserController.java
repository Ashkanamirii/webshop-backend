package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.User;
import com.nackademin.webshopbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping("/add")
    public void addUser(@RequestBody User user) {

        userService.addProduct(user);
    }

    @PostMapping("/add/list")
    public void addUsers(@RequestBody List<User> users) {

        userService.addProductList(users);
    }

    @PostMapping("/delete/id")
    public void deleteUserById(@RequestParam Long id) {
        userService.removeUserById(id);
    }

    @PostMapping("delete/all")
    public void deleteUserList(){
        userService.removeUsers();
    }


}