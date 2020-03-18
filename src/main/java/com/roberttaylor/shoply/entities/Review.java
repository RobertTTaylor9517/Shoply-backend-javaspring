package com.roberttaylor.shoply.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Review extends EntityWithUUID {
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