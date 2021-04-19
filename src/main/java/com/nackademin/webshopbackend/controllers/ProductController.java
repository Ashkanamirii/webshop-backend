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
//
@RestController
@RequestMapping(value = "/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get/id")
    public Product getProductById(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PostMapping("/add/list")
    public void addProductList(@RequestBody List<Product> products){
        productService.addProductList(products);
    }

    @PostMapping("/delete/all")
    public void deleteProductList(){
        productService.removeProducts();
    }

    @PostMapping("/delete/id")
    public void deleteProductById(@RequestParam Long id) {
        productService.removeProductById(id);
    }

}
