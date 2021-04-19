package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.repos.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:09 <br>
 * Project: webshop-back-end <br>
 */
@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;


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

    public String removeProductById(Long id){
        productDAO.deleteById(id);
        return "Produc with id " + id + " deleted.";
    }

}
