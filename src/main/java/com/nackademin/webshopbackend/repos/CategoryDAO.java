package com.nackademin.webshopbackend.repos;

import com.example.webshopbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:56 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface CategoryDAO extends JpaRepository<Category , Integer> {

/*    public List<Category> getAllCategories(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(1,"Skafferi"));
        list.add(new Category(2,"Snacks-godis"));
        list.add(new Category(3,"Hem-städ"));
        return list;
    }*/

}

