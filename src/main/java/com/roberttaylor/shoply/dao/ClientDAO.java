package com.roberttaylor.shoply.dao;

import java.util.UUID;

import com.roberttaylor.shoply.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, UUID> {
    Client findByUsername(String username);
}