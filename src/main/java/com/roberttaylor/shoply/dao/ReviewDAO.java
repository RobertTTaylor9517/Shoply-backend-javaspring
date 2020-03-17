package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Review;

import org.springframework.data.repository.CrudRepository;

public interface ReviewDAO extends CrudRepository<Review, UUID> {
    
}