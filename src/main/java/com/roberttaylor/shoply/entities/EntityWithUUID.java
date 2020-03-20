package com.roberttaylor.shoply.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityWithUUID {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    public EntityWithUUID(){
        this.id = UUID.randomUUID();
    }
}