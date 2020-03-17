package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, UUID> {
    
}