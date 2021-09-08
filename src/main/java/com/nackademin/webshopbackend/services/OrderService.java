package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.client.emailClient.EmailClient;
import com.nackademin.webshopbackend.client.emailClient.EmailContent;
import com.nackademin.webshopbackend.client.payment.PaymentClient;
import com.nackademin.webshopbackend.client.payment.PaymentDto;
import com.nackademin.webshopbackend.enumeration.OrderStatus;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nackademin.webshopbackend.constant.EmailConstant.CONFIRMATION;
import static com.nackademin.webshopbackend.enumeration.OrderStatus.PAID;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:10 <br>
 * Project: webshop-back-end <br>
 * Class that performs logic on Order objects.
 */
@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailClient emailClient;

	@Autowired
	private PaymentClient paymentClient;

	public List<Orders> getAllOrders() {
		return orderDAO.findAll();
	}

	public Orders getOrderById(Long id) {
		return orderDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
	}

	/**
	 * Method that adds an Order object to the database.
	 * Check if the user ID exists otherwise it returns an UserException.
	 *
	 * @param order Contains information about the order.
	 * @return The order that was saved or Exception.
	 * @throws Exception
	 */
	public Orders addOrder(Orders order) throws Exception {
		Users user = userDAO.findById(order.getUsers().getId()).orElse(null);
		if (user == null) {
			throw new Exception("The customer does not exist");
		} else {
			Orders newOrder = orderDAO.save(order);
			try {
				paymentClient.sendPayment(new PaymentDto(newOrder.getId().toString(), newOrder.getTotalPrice()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			emailClient.sendEmail(new EmailContent(user.getEmail(),
					"Order confirmation", CONFIRMATION +
					newOrder.getId()));
			return newOrder;
		}
	}

	public List<Orders> addOrderList(List<Orders> orders) {
		return orderDAO.saveAll(orders);
	}

	public String removeOrderById(Long id) {
		orderDAO.deleteById(id);
		return "Deleted order with id " + id;
	}

	public String removeAllOrders() {
		orderDAO.deleteAllInBatch();
		return "Deleted all orders.";
	}

	public String setOrderStatusToPaid(Long id) {
		Orders orders = orderDAO.findById(id).orElse(null);
		if (orders != null) {
			orders.setStatus(PAID);
			orderDAO.save(orders);
			return "Order " + orders.getId() + " has been PAID";
		}
		return "Order not found";
	}


}
