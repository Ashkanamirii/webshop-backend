package com.nackademin.webshopbackend;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.client.payment.PaymentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static com.nackademin.webshopbackend.constant.EmailConstant.BASEURL;

@SpringBootApplication
public class WebshopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopBackendApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public EmailClient getClientEmail() {
        return new EmailClient(getRestTemplate(),BASEURL);
    }
}

