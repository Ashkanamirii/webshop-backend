package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.utility.JWTTokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_SUPER_ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-13
 * Time: 13:49
 * Project: webshop-back-end
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AddressControllerTest {

    private final String baseUrl = "";
    private String token;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    public void init() {
        String[] array = {"ROLE_SUPER_ADMIN"};

        Users user = new Users(1L, "test@test.com", "Test", "password", "Pelle", "Karlsson", "070-1234567", "ROLE_SUPER_ADMIN", array,
                new Address(10L, "gatan 1", "12345", "Stockholm", LocalDateTime.now(), LocalDateTime.now()), true, true, LocalDateTime.now(), LocalDateTime.now());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        token = jwtTokenProvider.generateJwtToken(userPrincipal);
    }


    @Test
    void getAllAddressesGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/get")).andExpect(status().is(403));
    }

    @Test
    void getAllAddressesGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/get").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAddressById() {
    }

    @Test
    void addAddress() {
    }

    @Test
    void addAddresses() {
    }

    @Test
    void deleteAllAddresses() {
    }

    @Test
    void deleteAddressById() {
    }
}