package com.nackademin.webshopbackend.client.emailClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  13:43
 * Project: webshop-backend
 * Copyright: MIT
 */

@NoArgsConstructor
@Data
public class EmailContent {
	private String to;
	private String subject;
	private String body;

	@JsonCreator
	public EmailContent(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
