package com.nackademin.webshopbackend.client.emailClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  13:43
 * Project: webshop-backend
 * Copyright: MIT
 */

@NoArgsConstructor
@Data
@Component
public class EmailContent {
	private String to;
	private String subject;
	private String body;


	@JsonCreator
	public EmailContent(@JsonProperty("to") String to, @JsonProperty("subject") String subject,
	                    @JsonProperty("body") String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
