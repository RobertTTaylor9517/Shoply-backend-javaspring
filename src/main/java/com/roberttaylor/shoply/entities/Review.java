package com.roberttaylor.shoply.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Review implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID",
     strategy = GenerationType.AUTO)
    @GenericGenerator(
        name = "id",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    
    private String comment;
    private int rating;

    @ManyToOne
    // @MapsId("client_id")
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    // @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;
}