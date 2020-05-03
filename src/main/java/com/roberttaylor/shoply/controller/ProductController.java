package com.roberttaylor.shoply.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.roberttaylor.shoply.dao.ProductDAO;
import com.roberttaylor.shoply.entities.Product;
import com.roberttaylor.shoply.entities.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("products")
@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping
    public List<Product> products(){
        return productDAO.findAll();
    }
    
    @GetMapping(path = "{id}")
    public Product getProductById(@PathVariable("id") UUID id){
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()){
            return product.get();
        } else {
            return null;
        }
    }

    @GetMapping(value = "/{id}/reviews")
    public List<Review> product_reviews(@PathVariable UUID id){
        Product product = productDAO.getOne(id);

        return product.getReviews();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Product savedProduct = productDAO.save(product);
        return savedProduct;
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") UUID id){
        productDAO.deleteById(id);
    }
    
}