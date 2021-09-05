package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.domain.HttpResponse;
import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.exception.domain.EmailExistException;
import com.nackademin.webshopbackend.exception.domain.NotAnImageFileException;
import com.nackademin.webshopbackend.exception.domain.UserNotFoundException;
import com.nackademin.webshopbackend.exception.domain.UsernameExistException;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.services.UserService;
import com.nackademin.webshopbackend.utility.JWTTokenProvider;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.nackademin.webshopbackend.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 * Controller for calls to user urls.
 * Logic is performed in UserService.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	public static final String EMAIL_SENT = "An email with a new password was sent to: ";
	public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	public UserController(AuthenticationManager authenticationManager,
	                      UserService userService, JWTTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
	}


	@PostMapping("/login")
	public ResponseEntity<Users> login(@RequestBody Users user) {
		authenticate(user.getUsername(), user.getPassword());
		Users loginUser = userService.findUserByUsername(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser, jwtHeader, OK);
	}
	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody Users user) throws UserNotFoundException, UsernameExistException,
			EmailExistException {
		Users newUser = userService.register(user);
		return new ResponseEntity<>(newUser, OK);
	}


	@GetMapping("/get")
	public List<Users> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/get/{id}")
	public Users getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}


	@PostMapping("/add")
	public ResponseEntity<Users> addNewUser(@RequestBody Users user)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {
		Users newUser = userService.addNewUser(user);
		return new ResponseEntity<>(newUser, OK);
	}

	@SneakyThrows
	@PostMapping("/update")
	public Users updateUser(@RequestBody Users users) throws UserNotFoundException,
			EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
		return userService.updateUser(users);
	}

	@DeleteMapping("/delete/{username}")
	@PreAuthorize("hasAnyAuthority('user:delete')")
	public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) throws IOException {
		userService.deleteUser(username);
		return response(OK, USER_DELETED_SUCCESSFULLY);
	}



//////////////******************************************************************************************//////
//////////////******************************************************************************************//////
//////////////******************************************************************************************//////

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(),
				message), httpStatus);
	}

	private HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
		return headers;
	}


	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}