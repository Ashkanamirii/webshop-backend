package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.OrderRow;
import com.nackademin.webshopbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 17:20 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface OrderRowDAO extends JpaRepository<OrderRow,Long> {

}
