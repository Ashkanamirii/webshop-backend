package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Orders;
import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.repos.OrderDAO;
import com.nackademin.webshopbackend.repos.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:09 <br>
 * Project: webshop-back-end <br>
 * Class that performs logic on Product objects.
 */
@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderDAO orderDAO;

	public List<Product> getAllProducts() {
		return productDAO.findAll();
	}

	public Product getProductById(Long id) {
		return productDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
	}

	public Product addProduct(Product product) {
		return productDAO.save(product);
	}

	public List<Product> addProductList(List<Product> products) {
		return productDAO.saveAll(products);
	}

	public String removeProducts() {
		productDAO.deleteAllInBatch();
		return "All products deleted.";
	}

	public String removeProductById(Long id) {
		productDAO.deleteById(id);
		return "Product with id " + id + " deleted.";
	}

	/**
	 * Method that might be used from the admin portal to update products.
	 * Checks if the ID exists and then updates every variable from the input.
	 *
	 * @param product Object containing all the information to be updated.
	 * @return The updated product.
	 */
	public Product updateProduct(Product product) {
		Product p = productDAO.getOne(product.getId());
		p.setTitle(product.getTitle());
		p.setBrand(product.getBrand());
		p.setDescription(product.getDescription());
		p.setImage(product.getImage());
		p.setPrice(product.getPrice());
		p.setQuantity(product.getQuantity());
		p.setUnit(product.getUnit());
		List<Category> categories = p.getCategory();
		List<Category> c = product.getCategory();
		for (int i = 0; i < c.size(); i++) {
			categories.get(i).setId(c.get(i).getId());
		}
		p.setFeatured(product.isFeatured());

		return productDAO.save(p);
	}

	public List<OrderRow> checkQuantityAndPrice(List<OrderRow> orderRows) throws Exception {
		List<OrderRow> list = new ArrayList<>();
		Orders o = orderDAO.getOne(orderRows.get(0).getOrder().getId());
		double price = 0;

		// manuellt för att ändra orderRows till att bara felaktiga är kvar samt att lagerstatus hämtas från databasen.
		for (int i = orderRows.size() - 1; i >= 0; i--) {
			long id = orderRows.get(i).getProduct().getId();
			Product product = productDAO.getOne(id);
			price += product.getPrice() * orderRows.get(i).getQuantity();
			// check price with DB
			if (orderRows.get(i).getProduct().getPrice() != product.getPrice()
			|| orderRows.get(i).getQuantity() <= 0 ){
				orderDAO.deleteById(orderRows.get(i).getOrder().getId());
				throw new Exception("Produkt data är korrumperad");
			}

			if (orderRows.get(i).getQuantity() <= product.getQuantity()) {
				int newQuantity = product.getQuantity() - orderRows.get(i).getQuantity();
				product.setQuantity(newQuantity);
				Product p = productDAO.save(product);
				orderRows.get(i).setProductPriceWhenOrdering(p.getPrice());
				list.add(orderRows.get(i));
			}
		}
		if(price <= 250) price += 49;
		if(price != o.getTotalPrice()){
			orderDAO.deleteById(orderRows.get(0).getOrder().getId());
			throw new Exception("Kvantitet måste vara heltal!");
		}
		return list;
	}
}
