package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import com.nackademin.webshopbackend.utils.Encrypt;
import com.nackademin.webshopbackend.utils.UserException;
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
    private OrderDAO orderDAO;

    public List<Users> getAllUsers() {
        return userDAO.findAll();
    }

    public Users getUserById(Long id) {
        return userDAO.findById(id).orElse(null); // Makes it possible to return User instead of Optional
    }

//    public Users addUser(Users users) throws Exception {
//        return userDAO.save(users);
//    }

    public Users addUser(Users users) throws UserException {
        Users u = userDAO.findByEmail(users.getEmail());
        if (u != null) {
            throw new UserException("This E-mail already exist");
        } else {
            return userDAO.save(users);
        }
    }


    public Users findUserByEmailAndPassword(String email, String password) throws UserException {
        Users u = userDAO.findByEmailAndPassword(email, password);
        if (u == null) {
            throw new UserException("Incorrect USER or PASSWORD");
        } else {
            return u;
        }
    }

    public Users updateUser(Users users) {
        Users u = userDAO.getOne(users.getId());
        if(!users.getPassword().equalsIgnoreCase(u.getPassword())){
            u.setPassword(Encrypt.getMd5(users.getPassword()));
        }
        u.setFirstname(users.getFirstname());
        u.setLastname(users.getLastname());
        u.setNumber(users.getNumber());
        u.getAddress().setCity(users.getAddress().getCity());
        u.getAddress().setStreet(users.getAddress().getStreet());
        u.getAddress().setZipcode(users.getAddress().getZipcode());

        return userDAO.save(u);
    }

    public Users deleteUser(Users users) {
        List<Orders> o = orderDAO.findByUsersId(users.getId());
        if(!o.isEmpty()){
            for (Orders order : o){
                order.setUsers(null);
                orderDAO.save(order);
            }
        }
        userDAO.delete(users);
        return users;
    }

    public String removeUserById(Long id) {
        // Det ska kollas om det finns order med det här user eller inte
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
