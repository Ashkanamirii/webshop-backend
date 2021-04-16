package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:05 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface UserDAO extends JpaRepository<User,Long> {
}
