package com.nackademin.webshopbackend.services;

import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.repos.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-12
 * Time:  17:28
 * Project: webshop-back-end
 * Copyright: MIT
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public Category getCategoryById(Long id) { ;
        return categoryDAO.findById(id).orElse(null); // Makes it possible to return Category instead of Optional
    }

    public Category addCategory(Category category) {
       return categoryDAO.save(category);
    }

    public List<Category> addCategoryList(List<Category> categories) {
        return categoryDAO.saveAll(categories);
    }

    public String removeAllCategories() {
        categoryDAO.deleteAllInBatch();
        return "Deleted all categories.";
    }

    public String removeCategoryById(Long id) {
        categoryDAO.deleteById(id);
        return "Deleted category with id "+ id;
    }


}