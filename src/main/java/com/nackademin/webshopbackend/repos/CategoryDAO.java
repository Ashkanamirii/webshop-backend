package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Category;
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

}

