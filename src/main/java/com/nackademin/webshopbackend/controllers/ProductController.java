package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 * Controller for calls to product urls.
 * Logic is performed in ProductService.
 */
@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/get")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/get/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/add/list")
	public List<Product> addProductList(@RequestBody List<Product> products) {
		return productService.addProductList(products);
	}

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@PreAuthorize("hasAnyAuthority('user:delete')")
	@PostMapping("/delete/all")
	public String deleteProductList() {
		return productService.removeProducts();
	}

	@PreAuthorize("hasAnyAuthority('user:delete')")
	@PostMapping("/delete/{id}")
	public String deleteProductById(@PathVariable Long id) {
		return productService.removeProductById(id);
	}

}
