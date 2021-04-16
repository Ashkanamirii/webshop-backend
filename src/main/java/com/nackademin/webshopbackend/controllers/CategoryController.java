package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-12
 * Time:  17:27
 * Project: webshop-back-end
 * Copyright: MIT
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @PostMapping("/add/list")
    public void addCategoryList(@RequestBody List<Category> categories){
        categoryService.addCategoryList(categories);
    }

    @DeleteMapping("/removeAll")
    public void deleteAllCategories(){
        categoryService.removeCategory();
    }

//    @PostMapping("/delete/id")
//    public void deleteCategoryById(Long id) {
//        categoryService.removeCategoryById(id);
//    }
}
