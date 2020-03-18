package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<Client, UUID> {
    
}