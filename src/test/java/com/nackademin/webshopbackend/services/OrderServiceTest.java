package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.exception.domain.UserNotFoundException;
import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private Optional<Users> customer;
    private Orders order;
    private long customerId;

    @Mock
    private OrderDAO orderDAO;
    @Mock
    private UserDAO userDAO;
    @Mock
    private EmailClient emailClient;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void init() {
        this.customer = Optional.of(new Users(1L,"email@email.se","username","pass","firstName","lastName","number","USER",new String[1],new Address(),true,true, LocalDateTime.now(),LocalDateTime.now()));
        this.order = new Orders(1L, customer.get(),LocalDate.now().toString(), Orders.Status.PENDING,0.0,LocalDateTime.now(),LocalDateTime.now());
        this.customerId = customer.get().getId();
    }

    @Nested
    class AddOrderTest {
        @Test
        @DisplayName("Add order successfully")
        void addOrderSuccessfully() {


            when(userDAO.findById(customerId)).thenReturn(Optional.of(customer).get());
            when(orderDAO.save(order)).thenReturn(order);
            try {
                Orders actual = orderService.addOrder(order);
                assertEquals(order,actual);

            } catch (UserNotFoundException userNotFoundException){
                userNotFoundException.printStackTrace();
            }



            assertNotEquals(null,customer);

            verify(userDAO, times(1)).findById(customerId);
            verify(orderDAO, times(1)).save(order);
            verify(emailClient, times(1)).sendEmail(any());
        }

        @Test
        @DisplayName("Add order unsuccessfully, user null")
        void addOrderUnsuccessfullyUserNull() {

            UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,() -> {
                orderService.addOrder(order);
            });

            assertEquals("Customer not found.",userNotFoundException.getMessage());

            verify(userDAO, times(1)).findById(any());
            verify(orderDAO, times(0)).save(any());
            verify(emailClient, times(0)).sendEmail(any());


        }

        @Test
        @DisplayName("Add order unsuccessfully, input wrong")
        void addOrderUnsuccessfullyInputWrong() {
            /*
            input:
            user -> successful
            order -> unsuccessful

            output:
            ???

            något som bör testas eller oviktigt?
             */
        }

        /*
        emailClient?

        Är det något som ska testas i addOrder() eller antar vi att allt går korrekt till. Sedan så
        testar vi den med integration test senare?
         */
    }






}