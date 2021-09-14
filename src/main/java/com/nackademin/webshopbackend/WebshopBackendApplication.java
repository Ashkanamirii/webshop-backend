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

//    @Bean
//    public PaymentClient paymentClient() {
//        return new PaymentClient(getRestTemplate());
//    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null,"ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_MANAGER"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(null,"Ashkan Amiri",
//                    "Ashkan@gmail.com","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"test Amiri",
//                    "test@gmail.com","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"Jim Amiri",
//                    "Jim@gmail.com","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"Arnold Amiri",
//                    "Arnold@gmail.com","1234",new ArrayList<>()));
//
//            userService.addRoleToUser("Ashkan@gmail.com","ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("Ashkan@gmail.com","ROLE_ADMIN");
//            userService.addRoleToUser("Ashkan@gmail.com","ROLE_USER");
//            userService.addRoleToUser("Jim@gmail.com","ROLE_USER");
//            userService.addRoleToUser("Arnold@gmail.com","ROLE_USER");
//            userService.addRoleToUser("Arnold@gmail.com","ROLE_ADMIN");
//            userService.addRoleToUser("test@gmail.com","ROLE_ADMIN");
//            userService.addRoleToUser("test@gmail.com","ROLE_MANAGER");
//        };
//    }
}

