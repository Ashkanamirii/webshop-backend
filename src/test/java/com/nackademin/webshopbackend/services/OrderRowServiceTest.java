package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.enumeration.OrderStatus;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.repos.OrderRowDAO;
import com.nackademin.webshopbackend.repos.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-13
 * Time:  10:31
 * Project: webshop-backend
 * Copyright: MIT
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class OrderRowServiceTest {


	@InjectMocks
	OrderRowService orderRowService;

	private OrderRow orderRow1;
	private OrderRow orderRow2;
	private Product product1;
	private Product product2;
	private List<OrderRow> orderRows;
	private List<OrderRow> orderRowsWithOne;
	private Orders order;

	@Mock
	private OrderService orderService;

	@Mock
	private ProductService productService;
	@Mock
	private OrderRowDAO orderRowDAO;
	@Mock
	private ProductDAO productDao;

	@BeforeEach
	public void init() {
		order = new Orders(5L, null, "2021-8-10", OrderStatus.PAID, 1000, null, null);
		product1 = new Product(1L, "title", "descrption", 99.99, "20 g", "Ica",
				"Image", 10, true, null, null, null);
		product2 = new Product(2L, "title2", "descrption2", 129.99, "30 g", "Ica2",
				"Image2", 100, true, null, null, null);

		orderRow1 = new OrderRow(3L, product1, order, 5,
				99.99, OrderStatus.PAID, null, null);
		orderRow2 = new OrderRow(4L, product2, order, 20,
				129.99, OrderStatus.PAID, null, null);
		orderRows = List.of(orderRow1, orderRow2);
		orderRowsWithOne = List.of(orderRow1);
	}


	@Test
	void addOrderRowListShouldAddSuccessfully() throws Exception {
		when(productService.checkQuantityAndPrice(any())).thenReturn(orderRows);
		when(orderRowDAO.saveAll(any())).thenReturn(orderRows);

		List<OrderRow> orderRowList = new ArrayList<>();
		List<OrderRow> expected = orderRowService.addOrderRowList(orderRowList);
		List<OrderRow> actual = orderRows;
		assertEquals(expected.get(0).getProduct().getId(), actual.get(0).getProduct().getId());
	}

	@Test
	void addOrderRowListShouldNotAddSuccessfully() throws Exception {
		List<OrderRow> orderRowList = new ArrayList<>();
		when(productService.checkQuantityAndPrice(any())).thenReturn(orderRowList);
		assertThrows(Exception.class, () -> orderRowService.addOrderRowList(orderRows));
	}

	@Test
	void addOrderRowListShouldAddOneProduct() throws Exception {
		when(productService.checkQuantityAndPrice(any())).thenReturn(orderRowsWithOne);

		// Nu ska vi mocka setTotalPriceOfOne
		when(orderService.getOrderById(any())).thenReturn(order);
		when(orderService.addOrder(order)).thenReturn(order);

		when(orderRowDAO.saveAll(any())).thenReturn(orderRowsWithOne);
		List<OrderRow> expected = orderRowService.addOrderRowList(orderRows);
		List<OrderRow> actual = orderRowsWithOne;
		assertEquals(expected.get(0).getProduct().getId(), actual.get(0).getProduct().getId());
	}
}