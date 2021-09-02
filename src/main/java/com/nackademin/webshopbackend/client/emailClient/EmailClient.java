package com.nackademin.webshopbackend.client.emailClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private RestTemplate restTemplate;



	public String sendEmail(EmailContent emailContent) {
		restTemplate = new RestTemplate();
		System.out.println(emailContent.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("to", emailContent.getTo());
		map.put("subject", emailContent.getSubject());
		map.put("body", emailContent.getBody());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		final ResponseEntity<ClientEmailDTO> response = restTemplate.
				postForEntity("http://localhost:8085/sendemail", entity, ClientEmailDTO.class);


		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getClientEmail().getMessage();
		}

		throw new RuntimeException("Could not send email");
	}

//	public static void main(String[] args) {
//		EmailClient e = new EmailClient();
//		String s = e.sendEmail(new EmailContent("amiri.ashkan.d@gmail.com",
//				"test from code 2", "bbb"));
//		System.out.println(s);
//	}
}
