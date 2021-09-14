package com.nackademin.webshopbackend.client.emailClient;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


class EmailClientTest {
    private static final int PORT = 9090;
    private WireMockServer mockServer;

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
    void sendEmailSuccessful() {
        mockServer.stubFor(post(urlEqualTo("/mail/send")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{\"message\":\"send email successfully\"}:")));

        String emailServiceUrl = mockServer.baseUrl() + "/mail/send";
        RestTemplate restTemplate = new RestTemplate();
        EmailContent emailContent = new EmailContent("to@to.se","subject","body");

        EmailClient emailClient = new EmailClient(restTemplate, emailServiceUrl);
        String response = emailClient.sendEmail(emailContent);

        assertEquals("send email successfully", response);

    }

    @Test
    void sendEmailUnsuccessfully() {
        mockServer.stubFor(post(urlEqualTo("/mail/send")).willReturn(aResponse()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withHeader("Content-Type", "text/plain")
                .withBody("BAD REQUEST")));

        String emailServiceUrl = mockServer.baseUrl() + "/mail/send";
        RestTemplate restTemplate = new RestTemplate();
        EmailContent emailContent = new EmailContent();

        EmailClient emailClient = new EmailClient(restTemplate, emailServiceUrl);
        assertThrows(HttpClientErrorException.class, () -> {
            emailClient.sendEmail(emailContent);
        });

    }
}