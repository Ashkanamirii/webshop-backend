package com.nackademin.webshopbackend.services;

import com.example.webshopbackend.models.Product;
import com.example.webshopbackend.repos.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 12:09 <br>
 * Project: webshop-back-end <br>
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDAO productDAO;


    public List<Product> getProductDB() {
        return productDAO.findAll();
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
}