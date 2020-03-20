package com.roberttaylor.shoply.entities;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "product")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Product implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID",
     strategy = GenerationType.AUTO)
    @GenericGenerator(
        name = "id",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String name;
    private int price;
    private String category;
    private String description;
    private String img;
    private int rating;
    
    @OneToMany(mappedBy = "product")
    Set<Review> review;
}