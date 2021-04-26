package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.services.UserService;
import com.nackademin.webshopbackend.utils.Encrypt;
import com.nackademin.webshopbackend.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Object> addUser(@RequestBody Users users) {
//        Users u = null;
//        try {
//            u = userService.addUser(users);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().body("This E-mail already exist " + e.getMessage() + e.getCause());
//        }
//        return ResponseEntity.ok(u);
//    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody Users users) {
        Users u = null;
        try {
            users.setPassword(Encrypt.getMd5(users.getPassword()));
            u = userService.addUser(users);
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(u);
    }

    @PostMapping("/add/list")
    public List<Users> addUsers(@RequestBody List<Users> users) {
        for (Users u : users){
            u.setPassword(Encrypt.getMd5(u.getPassword()));
        }
        return userService.addUsers(users);
    }

    @PostMapping(value = "/authentication")
    public ResponseEntity<Object> findUserByEmailAndPassword(@RequestBody Users user) {
        Users u = null;
        try {
            u= userService.findUserByEmailAndPassword(user.getEmail(), Encrypt.getMd5(user.getPassword()));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(u);
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

    @PostMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return userService.removeUserById(id);
    }

    @PostMapping("delete/all")
    public String deleteUserList() {
        return userService.removeUsers();
    }

}