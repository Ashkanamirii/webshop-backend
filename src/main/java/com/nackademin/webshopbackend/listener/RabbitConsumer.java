package com.nackademin.webshopbackend.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-07
 * Time:  13:01
 * Project: webshop-backend
 * Copyright: MIT
 */

@Component
public class RabbitConsumer {


	@RabbitListener(queues = "payments")
	public void receiveMessage(String message) throws IOException {
		ObjectMapper o = new ObjectMapper();
		PaymentDTO payment = o.readValue(message, PaymentDTO.class);
		System.out.println("Rabbit answer with status-> " + payment.getStatus() + " and ref-> " + payment.getReference());
		if (payment.getStatus().equals("PAID")) {
			//TODO: send mail or change status order
		} else
			System.out.println("sending to server payments created");

	}
}
