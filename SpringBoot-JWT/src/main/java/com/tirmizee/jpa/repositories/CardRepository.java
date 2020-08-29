package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirmizee.jpa.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
