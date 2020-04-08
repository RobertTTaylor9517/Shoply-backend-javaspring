package com.roberttaylor.shoply.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.roberttaylor.shoply.dao.ClientDAO;
import com.roberttaylor.shoply.dao.ProductDAO;
import com.roberttaylor.shoply.dao.ReviewDAO;
import com.roberttaylor.shoply.entities.Client;
import com.roberttaylor.shoply.entities.Product;
import com.roberttaylor.shoply.entities.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("reviews")
@RestController
public class ReviewController {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired 
    private ClientDAO clientDAO;

    @Autowired
    private ProductDAO productDAO;


    @GetMapping
    public List<Review> review(){
        return reviewDAO.findAll();
    }
    @GetMapping(path = "{id}")
    public Review getReviewById(@PathVariable("id") UUID id){
        Optional<Review> review = reviewDAO.findById(id);
        if (review.isPresent()){
            return review.get();
        } else {
            return null;
        }
    }

    // @PostMapping
    // public Review addReview(@RequestBody Review review) {
    //     Review savedReview = reviewDAO.save(review);
    //     return savedReview;
    // }


    @PostMapping
    public Review addReview(@RequestParam("comment") String comment, @RequestParam("rate") String rate, @RequestParam("client_id") UUID client_id, @RequestParam("product_id") UUID product_id){
        Review review = new Review();
        review.setComment(comment);
        int ratingNum = Integer.parseInt(rate);
        review.setRating(ratingNum);

        Client client = clientDAO.getOne(client_id);
        review.setClient(client);


        Product product = productDAO.getOne(product_id);
        review.setProduct(product);

        Review newReview = reviewDAO.save(review);

        return newReview;
    }
    @DeleteMapping(path = "{id}")
    public void deleteReview(@PathVariable("id") UUID id) {
        reviewDAO.deleteById(id);
    }
    
}