package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, UUID> {
    
}