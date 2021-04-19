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

    public void addProduct(Product product) {
        productDAO.save(product);
    }

    public void addProductList(List<Product> products) {
        productDAO.saveAll(products);
    }

    public void removeProducts() {
        productDAO.deleteAllInBatch();

    }

    public void removeProductById(Long id){
        productDAO.deleteById(id);
    }



}
