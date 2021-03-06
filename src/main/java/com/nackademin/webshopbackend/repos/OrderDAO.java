package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:59 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface OrderDAO extends JpaRepository<Orders,Long> {
    List<Orders> findByUsersId(Long orderId);
}
