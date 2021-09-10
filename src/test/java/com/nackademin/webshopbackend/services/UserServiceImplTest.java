package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    List<Users> users = new ArrayList<>();

    @Mock
    private UserDAO repository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Users u = new Users();
        u.setId(1L);
        u.setEmail("ashkan@gmail.com");
        u.setUsername("ashkan@gmail.com");
        u.setPassword("Ashkan1885");
        u.setFirstName("ashkan");
        u.setActive(true);
        u.setNotLocked(true);

        users.add(u);

    }

    @Test
    @DisplayName("Should LoadUserByUsername Returns CorrectUser")
    void ShouldLoadUserByUsernameReturnsCorrectUsers() {
        Mockito.when(repository.findByUsername(Mockito.any())).thenReturn(users.get(0));

        UserServiceImpl spy = Mockito.spy(userService);

        Mockito.doNothing().when(spy).validateLoginAttempt(Mockito.any());

        UserPrincipal userPrincipal = new UserPrincipal(users.get(0));
        UserDetails userDetails = spy.loadUserByUsername(users.get(0).getUsername());


        assertEquals(userPrincipal.getUsername(), userDetails.getUsername());

    }
}