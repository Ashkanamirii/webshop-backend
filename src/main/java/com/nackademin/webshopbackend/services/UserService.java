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

    public User addUser(User user) {
       return  userDAO.save(user);
    }

    public List<User> addProductList(List<User> users) {
        return userDAO.saveAll(users);
    }

    public User findUserByEmailAndPassword(String email, String password) {
        User u = new User();
        try {
            u = userDAO.findByEmailAndPassword(email, password);
        }catch(NonUniqueResultException e){
            throw new RuntimeException("heloo");
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

    public void deleteUser(User user) {
       // Address address =user.getAddress();
       // addressDAO.delete(address);
        userDAO.delete(user);
    }
}
