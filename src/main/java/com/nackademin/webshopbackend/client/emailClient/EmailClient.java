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

import static com.nackademin.webshopbackend.constant.EmailConstant.BASEURL;

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
	private String emailServiceUrl;




	public String sendEmail(EmailContent emailContent) {
		restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("to", emailContent.getTo());
		map.put("subject", emailContent.getSubject());
		map.put("body", emailContent.getBody());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		final ResponseEntity<ClientEmailDTO> response = restTemplate.
				postForEntity(emailServiceUrl, entity, ClientEmailDTO.class);


		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getClientEmail().getMessage();
		}

		throw new RuntimeException("Could not send email");
	}
}
