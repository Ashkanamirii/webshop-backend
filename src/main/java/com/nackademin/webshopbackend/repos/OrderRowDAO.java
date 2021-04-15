package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 17:20 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface OrderRowDAO extends JpaRepository<OrderRow,Long> {

}
