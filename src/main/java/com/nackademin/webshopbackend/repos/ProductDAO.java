package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:30 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    //public Product findByTitle(String title);
}
