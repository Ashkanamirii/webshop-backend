package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.User;
import com.nackademin.webshopbackend.repos.AddressDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.hibernate.NonUniqueResultException;
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
    @Autowired
    private AddressDAO addressDAO;

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id).orElse(null); // Makes it possible to return User instead of Optional
    }

    public User addUser(User user) {
       return userDAO.save(user);
    }



    public User findUserByEmailAndPassword(String email, String password) {
        User u = new User();
        try {
            u = userDAO.findByEmailAndPassword(email, password);
        }catch(NonUniqueResultException e){
            throw new RuntimeException("hello");
        }
        return u;
    }

    public User updateUser(User user) {
        User u = userDAO.getOne(user.getId());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPassword(user.getPassword());
        u.getAddress().setCity(user.getAddress().getCity());
        u.getAddress().setStreet(user.getAddress().getStreet());
        u.getAddress().setZipcode(user.getAddress().getZipcode());

        return userDAO.save(u);
    }

    public User deleteUser(User user) {
        userDAO.delete(user);
        return user;
    }

    public String removeUserById(Long id) {
        userDAO.deleteById(id);
        return "User with id " + id + " remved.";
    }

    public String removeUsers(){
        userDAO.deleteAllInBatch();
        return "All users deleted.";
    }


    public List<User> addUsers(List<User> users) {
        return userDAO.saveAll(users);
    }
}
