package com.roberttaylor.shoply.controller;

import com.roberttaylor.shoply.dao.ClientDAO;
import com.roberttaylor.shoply.entities.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin
public class ClientController {

    @Autowired
    private ClientDAO clientDAO;
    
    @PostMapping("user")
    public Client login(@RequestParam("username") String username, @RequestParam("password") String pwd){

    }
}