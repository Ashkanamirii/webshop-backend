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
        return productService.getAllProducts();
    }


    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping("/add/list")
    public List<Product> addProductList(@RequestBody List<Product> products){
       return  productService.addProductList(products);
    }

    @DeleteMapping("/removeAll")
    public void deleteProductList(){
        productService.removeProducts();
    }


//    @PostMapping("/book/upsert")
//    public String upsertBook(@RequestBody Product b){
//        int indexToUpdate = -1;
//
//        for(int i =0; i< mybooks.size(); i++){
//            if(mybooks.get(i).getId() == b.getId()){
//                indexToUpdate = i;
//            }
//        }
//        if(indexToUpdate == -1){
//            mybooks.add(b);
//        }else {
//            mybooks.set(indexToUpdate,b);
//        }
//        return "book update";
//    }

}
