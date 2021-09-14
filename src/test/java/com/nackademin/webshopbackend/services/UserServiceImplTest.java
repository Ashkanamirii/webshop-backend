package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.exception.domain.EmailExistException;
import com.nackademin.webshopbackend.exception.domain.UserNotFoundException;
import com.nackademin.webshopbackend.exception.domain.UsernameExistException;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

	List<Users> users = new ArrayList<>();

	@Mock
	EmailClient emailClient;

	@Mock
	BCryptPasswordEncoder passwordEncoder;


	@Mock
	private UserDAO repository;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		Users u = new Users();
		Address a = new Address(1L, "street", "123456",
				"city", null, null);
		u.setId(1L);
		u.setEmail("ashkan@gmail.com");
		u.setUsername("ashkan@gmail.com");
		u.setPassword("Ashkan1885");
		u.setFirstName("ashkan");
		u.setActive(true);
		u.setNotLocked(true);
		u.setAddress(a);
		u.setRole(ROLE_USER.name());
		u.setAuthorities(ROLE_USER.getAuthorities());
		u.setNumber("073997****");

		users.add(u);

	}

	@Test
	@DisplayName("Should LoadUserByUsername Returns CorrectUser")
	void ShouldLoadUserByUsernameReturnsCorrectUsers() {
		when(repository.findByUsername(any())).thenReturn(users.get(0));

		UserServiceImpl spy = Mockito.spy(userService);

		Mockito.doNothing().when(spy).validateLoginAttempt(any());

		UserPrincipal userPrincipal = new UserPrincipal(users.get(0));
		UserDetails userDetails = spy.loadUserByUsername(users.get(0).getUsername());


		assertEquals(userPrincipal.getUsername(), userDetails.getUsername());

	}

	@Test
	@DisplayName("Should LoadUserByUsername Throw UsernameNotFoundException")
	void ShouldLoadUserByUsernameThrowUsernameNotFoundException() {
		Users u = null;
		when(repository.findByUsername(any())).thenReturn(u);
		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("user"));
	}

	@Test
	void registerUserShouldAddUserToDB() throws UserNotFoundException, EmailExistException, UsernameExistException {
		when(userService.findUserByUsername(any())).thenReturn(null);
		when(userService.findUserByEmail(any())).thenReturn(null);
		when(userService.encodePassword(any())).thenReturn(users.get(0).getPassword());
		when(passwordEncoder.encode(any())).thenReturn("Ashkan1885");
		when(emailClient.sendEmail(any())).thenReturn("Ashkan1885");
		when(repository.save(any())).thenReturn(users.get(0));

		Users expected = userService.register(users.get(0));
		Users actual = users.get(0);

		assertEquals(expected.getAuthorities(), actual.getAuthorities());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getEmail(), actual.getEmail());

	}


	@Test
	void registerUserShouldThrowException() {
		when(userService.findUserByUsername(any())).thenReturn(users.get(0));
		when(userService.findUserByEmail(any())).thenReturn(null);
		when(userService.encodePassword(any())).thenReturn(users.get(0).getPassword());
		when(passwordEncoder.encode(any())).thenReturn("Ashkan1885");
		when(emailClient.sendEmail(any())).thenReturn("Ashkan1885");
		when(repository.save(any())).thenReturn(users.get(0));

		Exception exception = assertThrows(UsernameExistException.class,
				() -> userService.register(users.get(0)));

		String expectedMessage = "Username already exists";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
//
//	@Test
//	void updateUserShouldUpdateOneUser() throws UserNotFoundException, EmailExistException, UsernameExistException {
//		when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
//		when(userService.findUserByUsername(any())).thenReturn(users.get(0));
//		when(userService.findUserByEmail(any())).thenReturn(users.get(0));
//		when(userService.findUserByEmail(users.get(0).getEmail())).thenReturn(users.get(0));
//		System.out.println(users.get(0).getNumber());
//		users.get(0).setNumber("newNumber");
//		when(repository.save(any())).thenReturn(users.get(0));
//
//		Users expected = userService.updateUser(users.get(0));
//		Users actual = users.get(0);
//
//		assertEquals(expected.getNumber(), actual.getNumber());
//	}
}