package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.exception.domain.EmailExistException;
import com.nackademin.webshopbackend.exception.domain.NotAnImageFileException;
import com.nackademin.webshopbackend.exception.domain.UserNotFoundException;
import com.nackademin.webshopbackend.exception.domain.UsernameExistException;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.services.AddressService;
import com.nackademin.webshopbackend.services.UserService;
import com.nackademin.webshopbackend.utility.JWTTokenProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
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
    private Users user;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    public void init() {
        user = new Users(1L, "test@test.com", "Test", "password", "Pelle", "Karlsson",
                "070-1234567", "ROLE_SUPER_ADMIN", new String[]{"ROLE_SUPER_ADMIN"},
                new Address(10L, "gatan 1", "12345", "Stockholm", LocalDateTime.now(), LocalDateTime.now()),
                true, true, LocalDateTime.now(), LocalDateTime.now());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        token = jwtTokenProvider.generateJwtToken(userPrincipal);
    }

    @Nested()
    class GiveStatus2xx {
        String json = "" +
                "{\n" +
                "\"id\": \"1\",\n" +
                "\"street\": \"Torget 333\",\n" +
                "\"city\": \"Botkyrka\",\n" +
                "\"zipcode\": \"14555\"\n" +
                "}";

        @Test
        @DisplayName("Adding a list of addresses that should give status code 2xx")
        void addAddressesGiveStatus200() throws Exception {
            String jsonList = "[" + json + "]";
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/add/list")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonList))
                    .andExpect(status().is2xxSuccessful());

        }

        @Test
        @DisplayName("Adding one address that should give status code 2xx")
        void addAddressGiveStatus200() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/add")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        @DisplayName("Deleting one address that should give status code 2xx")
        void deleteAddressByIdGiveStatus200() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/address/add")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/delete/1")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        @DisplayName("Get all addresses that should give status code 2xx")
        void getAllAddressesGiveStatus200() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/address/get")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        @DisplayName("Get one address by ID should give status code 2xx")
        void getAddressByIdGiveStatus200() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/address/get/10")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        @DisplayName("Deleting all addresses should give status code 2xx")
        void deleteAllAddressesGiveStatus200() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/delete/all")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Nested
    class GiveStatus4xx {
        @Test
        @DisplayName("Get all addresses should give status 4xx")
        void getAllAddressesGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/address/get"))
                    .andExpect(status().is(403));
        }

        @Test
        @DisplayName("Get one address by ID should give status 4xx")
        void getAddressByIdGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/address/get/10"))
                    .andExpect(status().is(403));
        }

        @Test
        @DisplayName("Add one address should give status 4xx")
        void addAddressGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/add"))
                    .andExpect(status().is(403));
        }

        @Test
        @DisplayName("Add a list of addresses should give status 4xx")
        void addAddressesGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/add/list"))
                    .andExpect(status().is(403));
        }

        @Test
        @DisplayName("Delete all addresses should give status 4xx")
        void deleteAllAddressesGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/delete/all"))
                    .andExpect(status().is(403));
        }

        @Test
        @DisplayName("Delete one address by ID should give status 403")
        void deleteAddressByIdGiveStatus403() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/address/delete/10"))
                    .andExpect(status().is(403));
        }
    }





}