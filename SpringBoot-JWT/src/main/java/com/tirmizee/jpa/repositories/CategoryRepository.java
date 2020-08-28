package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirmizee.jpa.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category getByCode(String code);

}
