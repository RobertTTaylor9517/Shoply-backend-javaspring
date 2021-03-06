package com.roberttaylor.shoply.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
// @Table(name = "product")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Product implements Serializable {

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
    
    // @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<Review> reviews;

    public void addReview(final Review review){
        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        reviews.add(review);

        review.setProduct(this);
    }

}