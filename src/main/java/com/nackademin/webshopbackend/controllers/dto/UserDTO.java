package com.nackademin.webshopbackend.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-02
 * Time:  23:35
 * Project: webshop-backend
 * Copyright: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private String username;
	private String password;

	public UserDTO() {

	}

	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}
