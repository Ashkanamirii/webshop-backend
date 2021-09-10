package com.nackademin.webshopbackend.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nackademin.webshopbackend.enumeration.OrderStatus;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.services.OrderService;
import javassist.NotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.nackademin.webshopbackend.enumeration.OrderStatus.PAID;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-07
 * Time:  13:01
 * Project: webshop-backend
 * Copyright: MIT
 */

@Component
public class RabbitConsumer {

	@Autowired
	private OrderService orderService;

	@RabbitListener(queues = "payments")
	public void receiveMessage(String message) throws IOException {
		ObjectMapper o = new ObjectMapper();
		PaymentDTO payment = o.readValue(message, PaymentDTO.class);
		//System.out.println("Rabbit answer with status-> " + payment.getStatus() + " and ref-> " + payment.getReference());
		if (payment.getStatus().equals("PAID")) {
			try	{
				Orders orders = orderService.getOrderById(Long.parseLong(payment.getReference()));
				orderService.setOrderStatusToPaid(orders.getId());
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (NotFoundException notFoundException) {
				notFoundException.printStackTrace();
			}
		} else
			System.out.println("sending to server payments created");

	}
}
