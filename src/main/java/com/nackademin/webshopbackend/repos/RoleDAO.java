package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-03
 * Time:  00:44
 * Project: webshop-backend
 * Copyright: MIT
 */
@Repository
public interface RoleDAO extends JpaRepository<Role,Long> {
	Role findByName(String name);
}
