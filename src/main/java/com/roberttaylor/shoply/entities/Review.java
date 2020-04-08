package com.roberttaylor.shoply.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
// @Table(name="review")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
// @JsonIdentityInfo(
//     generator = ObjectIdGenerators.StringIdGenerator.class,
//     property = "review_id"
// )
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

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @MapsId("client_id")
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    // Client client;
    private Client client;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @MapsId("product_id")
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    // Product product;
    private Product product;
}