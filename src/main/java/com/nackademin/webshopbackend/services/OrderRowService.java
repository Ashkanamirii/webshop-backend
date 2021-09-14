package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.client.emailClient.EmailContent;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.OrderRowDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nackademin.webshopbackend.constant.EmailConstant.CONFIRMATION;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:19
 * Project: webshop-backend
 * Copyright: MIT
 * Class that performs logic on OrderRow objects.
 */
@Service
@Slf4j
public class OrderRowService {

	@Autowired
	OrderRowDAO orderRowDAO;

	@Autowired
	OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private EmailClient emailClient;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private OrderDAO orderDao;

	public List<OrderRow> getAllOrderRow() {
		return orderRowDAO.findAll();
	}

	public OrderRow getOrderRowById(Long id) {
		return orderRowDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
	}

	public OrderRow addOrderRow(OrderRow orderRow) {
		return orderRowDAO.save(orderRow);
	}

	public List<OrderRow> addOrderRowList(List<OrderRow> orderRows) throws Exception {
		Orders o = orderDao.findById(orderRows.get(0).getOrder().getId()).orElseThrow();
		List<OrderRow> correctInStock = productService.checkQuantityAndPrice(orderRows);
		if (correctInStock.isEmpty()) { // Om ingenting fanns i lager
			orderService.removeOrderById(orderRows.get(0).getOrder().getId());
			throw new Exception("Lagersaldona var mindre i lager än i beställningen");
		} else if (orderRows.size() == correctInStock.size()) { // Om allt går bra
			try {
				emailClient.sendEmail(new EmailContent(o.getUsers().getEmail(),
						"Order confirmation", CONFIRMATION + orderRows.get(0).getOrder().getId()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				log.error("Error to send email");
			}
			return orderRowDAO.saveAll(correctInStock);
		} else { // Om bara några saker finns i lager
			// Uppdatera totalpriset
			setTotalPriceOfOneOrder(correctInStock, orderRows);
		}
		try {
			emailClient.sendEmail(new EmailContent(o.getUsers().getEmail(),
					"Order confirmation", CONFIRMATION + orderRows.get(0).getOrder().getId()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("Error to send email");
		}

		return orderRowDAO.saveAll(correctInStock);
	}

	public void removeOrderRows() {
		orderRowDAO.deleteAllInBatch();
	}

	public void removeOrderRowsById(Long id) {
		orderRowDAO.deleteById(id);
	}

	public void removeOrderRowsByOrderId(Long id) {
		orderRowDAO.deleteByOrderId(id);
	}

	public List<OrderRow> getByOrderId(Long orderId) {
		return orderRowDAO.findByOrderId(orderId);
	}

	public void setTotalPriceOfOneOrder(List<OrderRow> correctInStock, List<OrderRow> orderRows) {
		double totalPrice = 0;
		for (OrderRow or : correctInStock) {
			double p = or.getQuantity() * or.getProductPriceWhenOrdering();
			totalPrice += p;
		}
		Orders order = orderService.getOrderById(orderRows.get(0).getOrder().getId());
		if (totalPrice < 250) {
			totalPrice = totalPrice + 49;
		}
		order.setTotalPrice(totalPrice);
		try {
			orderService.addOrder(order);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
