package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:05 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface UserDAO extends JpaRepository<Users,Long> {
    Users findByEmailAndPassword(String email, String password);
    Users findByEmail(String email);

    Optional<Users> findById(Long id);
}
