package com.nackademin.webshopbackend.controllers;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-12
 * Time:  17:27
 * Project: webshop-back-end
 * Copyright: MIT
 * Controller for calls to category urls.
 * Logic is performed in CategoryService.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/get/id")
    public Category getCategoryById(@RequestParam Long id) {
        return categoryService.getCategoryById(id);
    }

    @PreAuthorize("hasAnyAuthority('user:create')")
    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PreAuthorize("hasAnyAuthority('user:create')")
    @PostMapping("/add/list")
    public List<Category> addCategoryList(@RequestBody List<Category> categories){
       return categoryService.addCategoryList(categories);
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @PostMapping("/delete/all")
    public String deleteAllCategories(){
        return categoryService.removeAllCategories();
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @PostMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.removeCategoryById(id);
    }
}
