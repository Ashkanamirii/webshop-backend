package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:15 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface AddressDAO extends JpaRepository<Address,Long> {

}
