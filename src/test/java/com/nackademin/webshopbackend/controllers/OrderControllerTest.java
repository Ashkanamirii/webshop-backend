package com.nackademin.webshopbackend.controllers;

import com.google.gson.Gson;
import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.utility.JWTTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderControllerTest {

    private String token;
    private String jsonOrder = "{\n" +
//            "\"id\": \"1\"" +
            "\"date\": \"2021-04-18\",\n" +
            "\"users\":{\n" +
            "\"id\": 1\n" +
            "} ,\n" +
            "\"status\": 0,\n" +
            "\"totalPrice\": 750\n" +
            "}";

    private String jsonUser = "{\n" +
            "\"id\":\"1\"" +
            "\"username\":\"Test\",\n" +
            "\n" +
            "\"firstName\": \"Pelle\",\n" +
            "\"lastName\": \"Karlsson\",\n" +
            "\"email\": \"test@test.com\",\n" +
            "\"password\": \"password\",\n" +
            "\"address\": {\n" +
            "\"street\": \"gatan 1\",\n" +
            "\"city\": \"Stockholm\",\n" +
            "\"zipcode\": \"12345\"\n" +
            "},\n" +
            "\n" +
            "\"number\": \"070-1234567\"\n" +
            "\n" +
            "}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    void init() {
        Users user = new Users(1L, "test@test.com", "Test", "password", "Pelle", "Karlsson", "070-1234567", "ROLE_SUPER_ADMIN", new String[]{"ROLE_SUPER_ADMIN"},
                new Address(10L, "gatan 1", "12345", "Stockholm", LocalDateTime.now(), LocalDateTime.now()),
                true, true, LocalDateTime.now(), LocalDateTime.now());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        token = jwtTokenProvider.generateJwtToken(userPrincipal);
    }

    @Test
    void addOrderShouldGiveStatus2xx() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/order/add")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonOrder)).andExpect(status()
                .is2xxSuccessful());
    }
}