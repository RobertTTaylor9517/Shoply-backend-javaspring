package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, UUID> {
    
}