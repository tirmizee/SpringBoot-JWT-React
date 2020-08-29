package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirmizee.jpa.entities.OrderItemKey;
import com.tirmizee.jpa.entities.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderItemKey>{

}
