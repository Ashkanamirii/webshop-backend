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

    private String token;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    public void init() {
        Users user = new Users(1L, "test@test.com", "Test", "password", "Pelle", "Karlsson",
                "070-1234567", "ROLE_SUPER_ADMIN", new String[]{"ROLE_SUPER_ADMIN"},
                new Address(10L, "gatan 1", "12345", "Stockholm", LocalDateTime.now(), LocalDateTime.now()),
                true, true, LocalDateTime.now(), LocalDateTime.now());
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
    void getAddressByIdGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/get/10")).andExpect(status().is(403));
    }

    @Test
    void getAddressByIdGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/get/10").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void addAddressGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/add")).andExpect(status().is(403));
    }

    @Test
    void addAddressGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/add").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void addAddressesGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/add/list")).andExpect(status().is(403));
    }

    @Test
    void addAddressesGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/add/list").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteAllAddressesGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/delete/all")).andExpect(status().is(403));
    }

    @Test
    void deleteAllAddressesGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/delete/all").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteAddressByIdGiveStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/delete/10")).andExpect(status().is(403));
    }

    @Test
    void deleteAddressByIdGiveStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/address/delete/10").header("Authorization", "Bearer " + token)).andExpect(status().is2xxSuccessful());
    }
}