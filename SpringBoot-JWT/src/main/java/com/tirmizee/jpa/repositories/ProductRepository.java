package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirmizee.jpa.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
