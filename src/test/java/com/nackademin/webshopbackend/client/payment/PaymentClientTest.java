package com.nackademin.webshopbackend.client.payment;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-06
 * Time: 16:08
 * Project: webshop-back-end
 */
class PaymentClientTest {

	private static final int PORT = 9090;
	private WireMockServer mockServer;
	private PaymentDto paymentDto;
	private PaymentClient paymentClient;

	@BeforeEach
	public void before() {
		mockServer = new WireMockServer(PORT);
		mockServer.start();
	}

	@AfterEach
	public void after() {
		mockServer.stop();
	}


	@Test
	void sendPaymentSuccessful() {
		mockServer.stubFor(post(urlEqualTo("/payment")).willReturn(aResponse()
				.withStatus(HttpStatus.NO_CONTENT.value())));


		String paymentServiceUrl = mockServer.baseUrl() + "/payment";
		RestTemplate restTemplate = new RestTemplate();
		paymentDto = new PaymentDto("test", 1500);
		paymentClient = new PaymentClient(restTemplate);
		paymentClient.setUrl(paymentServiceUrl);
		String response = paymentClient.sendPayment(paymentDto);

		assertEquals("204 NO_CONTENT", response);

	}

	@Test
	void sendPaymentUnsuccessfully() {
		mockServer.stubFor(post(urlEqualTo("/payment")).willReturn(aResponse()
				.withStatus(HttpStatus.FORBIDDEN.value())));

		String paymentServiceUrl = mockServer.baseUrl() + "/payment";
		RestTemplate restTemplate = new RestTemplate();
		paymentDto = new PaymentDto("test", 1500);
		paymentClient = new PaymentClient(restTemplate);
		paymentClient.setUrl(paymentServiceUrl);


		assertThrows(Exception.class, () -> {
			paymentClient.sendPayment(paymentDto);
		});
	}
}