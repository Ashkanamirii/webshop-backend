package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.client.emailClient.EmailContent;
import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.enumeration.Role;
import com.nackademin.webshopbackend.exception.domain.EmailExistException;
import com.nackademin.webshopbackend.exception.domain.EmailNotFoundException;
import com.nackademin.webshopbackend.exception.domain.UserNotFoundException;
import com.nackademin.webshopbackend.exception.domain.UsernameExistException;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

import static com.nackademin.webshopbackend.constant.EmailConstant.REGISTRATION;
import static com.nackademin.webshopbackend.constant.UserImplConstant.*;
import static com.nackademin.webshopbackend.enumeration.Role.ROLE_USER;
import static org.apache.commons.lang3.StringUtils.EMPTY;

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
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserDAO userDAO;
	private OrderDAO orderDAO;
	private LoginAttemptService loginAttemptService;
	private BCryptPasswordEncoder passwordEncoder;
	private EmailClient emailClient;

	public UserServiceImpl(UserDAO userDAO, OrderDAO orderDAO, LoginAttemptService loginAttemptService,
	                       BCryptPasswordEncoder passwordEncoder,EmailClient emailClient) {
		this.userDAO = userDAO;
		this.orderDAO = orderDAO;
		this.loginAttemptService = loginAttemptService;
		this.passwordEncoder = passwordEncoder;
		this.emailClient = emailClient;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userDAO.findByUsername(username);
		if (user == null) {
			log.error("User not found by username: " + username);
			throw new UsernameNotFoundException("User not found by username: " + username);
		} else {
			validateLoginAttempt(user);
			userDAO.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			log.info(FOUND_USER_BY_USERNAME + username);
			return userPrincipal;
		}

	}


	public Users getUserById(Long id) {
		return userDAO.findById(id).orElse(null); // Makes it possible to return User instead of Optional
	}

	@Override
	public Users register(Users user) throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, user.getUsername(), user.getEmail());
		Users u = new Users();
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(encodePassword(user.getPassword()));
		u.setRole(ROLE_USER.name());
		u.setAuthorities(ROLE_USER.getAuthorities());
		u.setNumber(user.getNumber());
		u.setActive(true);
		u.setNotLocked(true);
		Address address = new Address();
		address.setCity(user.getAddress().getCity());
		address.setStreet(user.getAddress().getStreet());
		address.setZipcode(user.getAddress().getZipcode());
		u.setAddress(address);
		userDAO.save(u);

		log.info("New  password: " + u.getPassword());
		emailClient.sendEmail(new EmailContent(user.getEmail(),"Register User",REGISTRATION));
		return u;

	}

	@Override
	public List<Users> getUsers() {
		return userDAO.findAll();
	}

	@Override
	public Users findUserByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public Users findUserByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public Users addNewUser(Users user) throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, user.getUsername(), user.getEmail());
		Users u = new Users();
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(encodePassword(user.getPassword()));
		u.setRole(getRoleEnumName(user.getRole()).name());
		u.setAuthorities(getRoleEnumName(user.getRole()).getAuthorities());
		u.setNumber(user.getNumber());
		u.setActive(true);
		u.setNotLocked(true);
		Address address = new Address();
		address.setCity(user.getAddress().getCity());
		address.setStreet(user.getAddress().getStreet());
		address.setZipcode(user.getAddress().getZipcode());
		userDAO.save(u);

		log.info("New  password: " + u.getPassword());
		// email to user

		return u;
	}


	public Users updateUser(Users user) throws UserNotFoundException, EmailExistException, UsernameExistException {
		Users currentUser = validateNewUsernameAndEmail(user.getUsername(), user.getUsername(), user.getEmail());
		log.info(currentUser.toString());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setNumber(user.getNumber());
		log.info(currentUser.getAddress().toString());
		currentUser.getAddress().setId(user.getAddress().getId());
		currentUser.getAddress().setCity(user.getAddress().getCity());
		currentUser.getAddress().setStreet(user.getAddress().getStreet());
		currentUser.getAddress().setZipcode(user.getAddress().getZipcode());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		currentUser.setRole(getRoleEnumName(user.getRole()).name());
		currentUser.setAuthorities(getRoleEnumName(user.getRole()).getAuthorities());
		currentUser.setActive(user.isActive());
		currentUser.setNotLocked(user.isNotLocked());

		return userDAO.save(currentUser);
	}

	@Override
	public void deleteUser(String username) throws IOException {
		Users user = userDAO.findByUsername(username);
		List<Orders> o = orderDAO.findByUsersId(user.getId());
		if (!o.isEmpty()) {
			for (Orders order : o) {
				order.setUsers(null);
				orderDAO.save(order);
			}
		}

		userDAO.deleteById(user.getId());
	}

	@Override
	public void resetPassword(String email, String newPassword) throws EmailNotFoundException {
		Users user = userDAO.findByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
		}

		user.setPassword(encodePassword(newPassword));
		userDAO.save(user);
		log.info("New user password: " + newPassword);
		//SEND EMAIL
	}

	/*
	 * ************************************UTILS*****************************************************
	 */

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	private void validateLoginAttempt(Users user) {
		if (user.isNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}

	private Role getRoleEnumName(String role) {
		return Role.valueOf(role.toUpperCase());
	}

	private Users validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		Users userByNewUsername = findUserByUsername(newUsername);
		Users userByNewEmail = findUserByEmail(newEmail);
		if (StringUtils.isNotBlank(currentUsername)) {
			Users currentUser = findUserByUsername(currentUsername);
			if (currentUser == null) {
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}
			if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
				throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
			}
			if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} else {
			if (userByNewUsername != null) {
				throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
			}
			if (userByNewEmail != null) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

}
