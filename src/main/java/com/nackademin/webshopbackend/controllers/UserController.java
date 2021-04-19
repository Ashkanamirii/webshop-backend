package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Orders;
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

    @GetMapping("/get/id")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        User u = userService.addUser(user);

        System.out.println(u.getAddress().getCity());
        return u;
    }

    @PostMapping("/add/list")
    public void addUsers(@RequestBody List<User> users) {
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.addProductList(users);
    }

    @PostMapping(value = "/authentication/{email}/{password}")
    public User findUserByEmailAndPassword(@PathVariable String email,
                                           @PathVariable String password) {
        return userService.findUserByEmailAndPassword(email, password);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        String email = user.getEmail();
        String deleteMessage = email + " has been deleted";
        return deleteMessage;
    }

    @PostMapping("/delete/id")
    public void deleteUserById(@RequestParam Long id) {
        userService.removeUserById(id);
    }

}


    @PostMapping("delete/all")
    public void deleteUserList(){
        userService.removeUsers();
    }


}