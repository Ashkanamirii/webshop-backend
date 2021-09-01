package com.nackademin.webshopbackend.client.emailClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  13:33
 * Project: webshop-backend
 * Copyright: MIT
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailClient {

	private RestTemplate restTemplate = new RestTemplate();

	private String baseUrl = "http://localhost:8081/sendemail";


	public String sendEmail(EmailContent emailContent) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		Map<EmailContent, Object> map = new HashMap<>();
		map.put(emailContent, headers);

		HttpEntity<Map<EmailContent, Object>> entity = new HttpEntity<>(map, headers);

		final ResponseEntity<ClientEmailDTO> response = restTemplate.
				postForEntity("http://localhost:8081/sendemail", entity, ClientEmailDTO.class);


		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getClientEmail().getMessage();
		}

		throw new RuntimeException("Could not send email");
	}

	public static void main(String[] args) {
		EmailClient e = new EmailClient();
		String s = e.sendEmail(new EmailContent("amiri.ashkan.d@gmail.com", "test from code", "aaaaaa"));
		System.out.println(s);
	}
}

/*
RestTemplate restTemplate = new RestTemplate();

// create headers
HttpHeaders headers = new HttpHeaders();
// set `content-type` header
headers.setContentType(MediaType.APPLICATION_JSON);
// set `accept` header
headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// request body parameters
Map<String, Object> map = new HashMap<>();
map.put("userId", 1);
map.put("title", "Spring Boot 101");
map.put("body", "A powerful tool for building web apps.");

// build the request
HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
 */
