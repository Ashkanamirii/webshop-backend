package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.User;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:10 <br>
 * Project: webshop-back-end <br>
 */
@Service
public class UserService {


    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
    }

    public void addProduct(User user) {
        userDAO.save(user);
    }

    public void addProductList(List<User> users) {
        userDAO.saveAll(users);
    }

    public void removeUserById(Long id) {
        userDAO.deleteById(id);
    }

    public void removeUsers(){
        userDAO.deleteAllInBatch();
    }


}
