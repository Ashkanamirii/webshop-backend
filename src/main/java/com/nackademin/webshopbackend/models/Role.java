package com.nackademin.webshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-02
 * Time:  23:50
 * Project: webshop-backend
 * Copyright: MIT
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
}
