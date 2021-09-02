package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Role;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.RoleDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import com.nackademin.webshopbackend.utils.Encrypt;
import com.nackademin.webshopbackend.utils.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:10 <br>
 * Project: webshop-back-end <br>
 * Class that performs logic on User objects.
 */
@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private RoleDAO roleDAO;

    public List<Users> getAllUsers() {
        return userDAO.findAll();
    }

    public Users getUserById(Long id) {
        return userDAO.findById(id).orElse(null); // Makes it possible to return User instead of Optional
    }

    /**
     * Method that adds user to User repository, using the findByEmail method in User repository .
     * @param users Accepts a user object
     * @return a Users object
     * @throws UserException if user already exists an exception is thrown
     */
    public Users addUser(Users users) throws UserException {
        Users u = userDAO.findByEmail(users.getEmail());
        if (u != null) {
            throw new UserException("This E-mail already exist");
        } else {
            return userDAO.save(users);
        }
    }

    /**
     * Method that checks if user inputs correct email and password.
     * @param email Email String from frontend request
     * @param password password String from frontend request
     * @return a User object that matches correct email and password.
     * @throws UserException if either email or password is incorrect
     */
    public Users findUserByEmailAndPassword(String email, String password) throws UserException {
        Users u = userDAO.findByEmailAndPassword(email, password);
        if (u == null) {
            throw new UserException("Incorrect USER or PASSWORD");
        } else {
            return u;
        }
    }

    /**
     * Method that updates a Users preferences when password is validated using Encrypt class
     * @param users Accepts a Users object
     * @return an updates Users object
     */
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

    /**
     * Method that removes a user from database.
     * First checks if specified user has an active order. If so, the order will be deleted.
     * @param users Acccepts a Users object from frontend
     * @return A Users object
     */
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

    /**
     * OBS! Method that might be used in later front-end versions.
     * @param id of a user.
     * @return A String with the id of the deleted User.
     */
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


    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleDAO.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        Users u = userDAO.findByEmail(email);
        Role r = roleDAO.findByName(roleName);
        u.getRoles().add(r);
    }
}
