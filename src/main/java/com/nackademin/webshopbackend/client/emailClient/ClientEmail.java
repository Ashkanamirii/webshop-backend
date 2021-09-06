package com.nackademin.webshopbackend.client.emailClient;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  14:03
 * Project: webshop-backend
 * Copyright: MIT
 */
public class ClientEmail {
	private String message;

	public ClientEmail(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
