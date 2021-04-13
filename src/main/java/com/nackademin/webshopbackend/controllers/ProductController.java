package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-12 <br>
 * Time: 11:55 <br>
 * Project: webshop-back-end <br>
 */
@RestController
@RequestMapping(value = "/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    public List<Product> getAllProducts(){
        return productService.getProductDB();
    }


    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PostMapping("/add/list")
    public void addProductList(@RequestBody List<Product> products){
        productService.addProductList(products);
    }

    @DeleteMapping("/remove")
    public void deleteProductList(){
        productService.removeProducts();
    }

}
