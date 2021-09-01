package com.nackademin.webshopbackend.client.emailClient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-01
 * Time:  14:29
 * Project: webshop-backend
 * Copyright: MIT
 */
class EmailClientTest {

	@Test
	void sendEmail() {
		EmailClient e = new EmailClient();
		String s = e.sendEmail(new EmailContent("amiri.ashkan.d@gmail.com", "test from code", "aaaaaa"));
		System.out.println(s);
	}
}