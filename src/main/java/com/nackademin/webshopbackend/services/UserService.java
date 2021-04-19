package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Users;
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

    public List<Users> getAllUsers() {
        return userDAO.findAll();
    }

    public Users getUserById(Long id) {
        return userDAO.findById(id).orElse(null); // Makes it possible to return User instead of Optional
    }

    public Users addUser(Users users) {
       return userDAO.save(users);
    }



    public Users findUserByEmailAndPassword(String email, String password) {
        Users u = new Users();
        try {
            u = userDAO.findByEmailAndPassword(email, password);
        }catch(NonUniqueResultException e){
            throw new RuntimeException("hello");
        }
        return u;
    }

    public Users updateUser(Users users) {
        Users u = userDAO.getOne(users.getId());
        u.setFirstname(users.getFirstname());
        u.setLastname(users.getLastname());
        u.setPassword(users.getPassword());
        u.getAddress().setCity(users.getAddress().getCity());
        u.getAddress().setStreet(users.getAddress().getStreet());
        u.getAddress().setZipcode(users.getAddress().getZipcode());

        return userDAO.save(u);
    }

    public Users deleteUser(Users users) {
        userDAO.delete(users);
        return users;
    }

    public String removeUserById(Long id) {
        userDAO.deleteById(id);
        return "User with id " + id + " remved.";
    }

    public String removeUsers(){
        userDAO.deleteAllInBatch();
        return "All users deleted.";
    }


    public List<Users> addUsers(List<Users> users) {
        return userDAO.saveAll(users);
    }
}
