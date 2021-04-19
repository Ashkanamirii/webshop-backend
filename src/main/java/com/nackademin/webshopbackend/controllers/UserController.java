package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Users;
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
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/id")
    public Users getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public Users addUser(@RequestBody Users users) {
       return  userService.addUser(users);
    }

    @PostMapping("/add/list")
    public List<Users> addUsers(@RequestBody List<Users> users) {
        return userService.addUsers(users);
    }

    @PostMapping(value = "/authentication/{email}/{password}")
    public Users findUserByEmailAndPassword(@PathVariable String email,
                                            @PathVariable String password) {
        return userService.findUserByEmailAndPassword(email, password);
    }

    @PostMapping("/update")
    public Users updateUser(@RequestBody Users users) {
        return userService.updateUser(users);
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody Users users) {
        userService.deleteUser(users);
        String email = users.getEmail();
        String deleteMessage = email + " has been deleted";
        return deleteMessage;
    }

    @PostMapping("/delete/id")
    public String deleteUserById(@RequestParam Long id) {
        return userService.removeUserById(id);
    }

    @PostMapping("delete/all")
    public String deleteUserList(){
        return userService.removeUsers();
    }

}