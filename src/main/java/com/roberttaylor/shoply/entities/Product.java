package com.roberttaylor.shoply.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Product extends EntityWithUUID {
    private String name;
    private int price;
    private String category;
    private String description;
    private String img;
    private int rating;
    
    @OneToMany(mappedBy = "product")
    Set<Review> review;
}