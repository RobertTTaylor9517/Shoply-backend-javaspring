package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, UUID> {
    
}