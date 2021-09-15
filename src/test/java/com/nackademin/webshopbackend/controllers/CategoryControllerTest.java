package com.nackademin.webshopbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nackademin.webshopbackend.domain.UserPrincipal;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.utility.JWTTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static com.nackademin.webshopbackend.enumeration.Role.ROLE_SUPER_ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoryControllerTest {

    private String token;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    void init() {
        Users user = new Users(1L, "test@test.com", "Test", "password", "Pelle", "Karlsson",
                "070-1234567", ROLE_SUPER_ADMIN.name(), ROLE_SUPER_ADMIN.getAuthorities(),
                new Address(10L, "gatan 1", "12345", "Stockholm", LocalDateTime.now(), LocalDateTime.now()),
                true, true, LocalDateTime.now(), LocalDateTime.now());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        token = jwtTokenProvider.generateJwtToken(userPrincipal);
    }

    @Nested
    class GiveStatus4xx {

        @Test
        void getCategoryByIdGiveStatus4xx() throws Exception {
            mockMvc.perform((MockMvcRequestBuilders
                    .get("/category/get/id")))
                    .andExpect(status().is(500));
        }

        @Test
        void addCategoryGiveStatus4xx() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/category/add"))
                     .andExpect(status().is4xxClientError());
        }

        @Test
        void addCategoryListGiveStatus4xx() throws Exception {
            mockMvc.perform((MockMvcRequestBuilders
                    .post("/category/add/list")))
                    .andExpect(status().is(403));
        }

        @Test
        void deleteAllCategoriesGiveStatus4xx() throws Exception {
            mockMvc.perform((MockMvcRequestBuilders
                    .post("/category/delete/all")))
                    .andExpect(status().is(403));
        }

        @Test
        void deleteCategoryByIdGiveStatus4xx() throws Exception {
            mockMvc.perform((MockMvcRequestBuilders
                    .post("/category/delete/1")))
                    .andExpect(status().is(403));
        }
    }

    @Nested
    class GiveStatus2xx {
        String json = "{\n" + "\"id\": \"1\",\n" + "\"name\": \"Gröt\"\n" + "}";

        @Test
        void getAllCategoriesGiveStatus2xx() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/category/get")
                    .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        void getCategoryByIdGiveStatus2xx() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                    .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));

            mockMvc.perform(MockMvcRequestBuilders.get("/category/get/id")
                    .header("Authorization", "Bearer " + token)
                    .param("id","1"))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        void addCategoryGiveStatus2xx() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                            .andExpect(status().is2xxSuccessful());
        }

        @Test
        void addCategoryListGiveStatus2xx() throws Exception {
            System.out.println(token + " **************************************************************************");
            String jsonList = "[" + json + "]";
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/category/add/list")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonList))
                            .andExpect(status().is2xxSuccessful());
        }

        @Test
        void deleteAllCategoriesGiveStatus2xx() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));

            mockMvc.perform(MockMvcRequestBuilders.post("/category/delete/all")
                    .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        void deleteCategoryByIdGiveStatus2xx() throws Exception{
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)).andExpect(status().is2xxSuccessful()).andReturn();

//            String object = mvcResult.getResponse().getContentAsString();

//            System.out.println("object = " + object);
            Category cat = new ObjectMapper().readValue(json,Category.class);

            System.out.println(cat.getId() + "********************* VÅRT ID ******************");

            mockMvc.perform(MockMvcRequestBuilders.post("/category/delete/"+cat.getId())
                    .header("Authorization", "Bearer " + token))
                    .andExpect(status().is2xxSuccessful());
        }
    }


}