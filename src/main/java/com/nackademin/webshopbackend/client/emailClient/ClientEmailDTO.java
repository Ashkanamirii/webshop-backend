package com.nackademin.webshopbackend.client.emailClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  13:36
 * Project: webshop-backend
 * Copyright: MIT
 */



@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientEmailDTO {
	private final ClientEmail clientEmail;

	@JsonCreator
	public ClientEmailDTO(@JsonProperty("message") ClientEmail clientEmail) {
		this.clientEmail = clientEmail;
	}


	public ClientEmail getClientEmail() {
		return clientEmail;
	}
}
