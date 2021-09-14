package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-13
 * Time:  19:02
 * Project: webshop-backend
 * Copyright: MIT
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class UserControllerTest {

	@Autowired
	private WebTestClient client;

	@Test
	void shouldBeAbleToRegisterAndLoginAndGetTokenBackAndCallGetUserByToken() {
		String signupBody = "{\n" +
				"    \"username\": \"amiri.ashkan.d@gmail.com\",\n" +
				"    \"firstName\": \"Ashkan\",\n" +
				"    \"lastName\": \"Amiri\",\n" +
				"    \"email\": \"amiri.ashkan.d@gmail.com\",\n" +
				"    \"password\": \"123456ashkan\",\n" +
				"    \"address\": {\n" +
				"        \"street\": \"Sollentuna\",\n" +
				"        \"city\": \"Sollentuna\",\n" +
				"        \"zipcode\": \"167 45\"\n" +
				"    },\n" +
				"    \"number\": \"+46732000880\"\n" +
				"}";
		try {
			// Register new user should send email
			client.post()
					.uri("/user/register")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(signupBody)
					.exchange()
					.expectStatus()
					.is2xxSuccessful();

			String loginBody = "{\n" +
					"    \"username\": \"amiri.ashkan.d@gmail.com\",\n" +
					"    \"password\":\"123456ashkan\"\n" +
					"}";
			// Login should get back JWT token
			List<String> strings = client.post()
					.uri("/user/login")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(loginBody)
					.exchange()
					.expectStatus()
					.is2xxSuccessful()
					.expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
					.expectBody().returnResult().getResponseHeaders().get("Jwt-Token");
			String token = strings.get(0);
			System.out.println(token + " +++++++++TOKEN++++++++++++");

			//Should get user back by JWT token
			Users firstUser = client.get().uri("/user/get/1").header("Authorization",
							"Bearer " + token)
					.exchange()
					.expectStatus()
					.is2xxSuccessful()
					.returnResult(Users.class)
					.getResponseBody().blockFirst();

			System.out.println(firstUser.getEmail() + "++++++++++++++EMAIL++++++++++++++++");

			assertEquals("amiri.ashkan.d@gmail.com", firstUser.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void register() {
	}

	@Test
	void addNewUser() {
	}

	@Test
	void updateUser() {
	}
}