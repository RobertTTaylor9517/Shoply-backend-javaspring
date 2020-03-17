package com.roberttaylor.shoply.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

public class Review extends EntityWithUUID {
    private String comment;
    private int rating;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product-id")
    Product product;
}