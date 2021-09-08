package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.exception.domain.*;
import com.nackademin.webshopbackend.models.Users;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-05
 * Time:  19:52
 * Project: webshop-backend
 * Copyright: MIT
 */
public interface UserService {
	Users register(Users user)
			throws UserNotFoundException, UsernameExistException, EmailExistException;

	List<Users> getUsers();

	Users findUserByUsername(String username);

	Users findUserByEmail(String email);

	Users addNewUser(Users user)
			throws UserNotFoundException, UsernameExistException,
			EmailExistException, IOException, NotAnImageFileException;

	Users updateUser(Users user)
			throws UserNotFoundException, UsernameExistException,
			EmailExistException;

	void deleteUser(String username) throws IOException;

	void resetPassword(String email, String newPassword) throws  EmailNotFoundException;

	Users getUserById(Long id);
}
