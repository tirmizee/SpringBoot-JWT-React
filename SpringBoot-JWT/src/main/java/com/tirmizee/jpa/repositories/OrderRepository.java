package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirmizee.jpa.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
