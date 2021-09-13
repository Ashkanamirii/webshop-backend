package com.nackademin.webshopbackend.controllers;


import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.services.OrderRowService;
import com.nackademin.webshopbackend.services.OrderService;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-15
 * Time:  19:18
 * Project: webshop-backend
 * Copyright: MIT
 * Controller for calls to orderRow urls.
 * Logic is performed in OrderRowService.
 */
@RestController
@RequestMapping(value = "/orderRow")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderRowController {

	@Autowired
	OrderRowService orderRowService;

	@Autowired
	OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping("/get")
	public List<OrderRow> getAllOrderRow() {
		return orderRowService.getAllOrderRow();
	}

	@GetMapping("/get/id")
	public OrderRow getOrderRowById(Long id) {
		return orderRowService.getOrderRowById(id);
	}

	@PostMapping("/add")
	public OrderRow addOrderRow(@RequestBody OrderRow orderRow) {
		return orderRowService.addOrderRow(orderRow);
	}

	@PostMapping("/add/list")
	public ResponseEntity<Object> addOrderRowList(@RequestBody List<OrderRow> orderRows) {
		try {
			return ResponseEntity.ok(orderRowService.addOrderRowList(orderRows));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PostMapping("/delete/id")
	public void deleteOrderRowById(Long id) {
		orderRowService.removeOrderRowsById(id);
	}

	@PostMapping("/delete/orderid")
	public void deleteOrderRowsByOrderId(Long id) {
		orderRowService.removeOrderRowsByOrderId(id);
	}

	@PostMapping("/delete/all")
	public void deleteOrderRowList() {
		orderRowService.removeOrderRows();
	}

	@GetMapping("/get/byOrderID/{orderId}")
	public List<OrderRow> getByOrderId(@PathVariable Long orderId) {
		return orderRowService.getByOrderId(orderId);
	}
}
