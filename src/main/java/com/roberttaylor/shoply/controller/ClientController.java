package com.roberttaylor.shoply.controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.roberttaylor.shoply.dao.ClientDAO;
import com.roberttaylor.shoply.entities.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin
public class ClientController {

    @Autowired
    private ClientDAO clientDAO;

    @RequestMapping("/signup")
    public Client addClient(@RequestBody Client client) {
        Client newClient = clientDAO.save(client);
        return newClient;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody Client client) {
        return
            client.getUsername().equals("user") && client.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal client(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
            .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
            .decode(authToken)).split(":")[0];
    }
}