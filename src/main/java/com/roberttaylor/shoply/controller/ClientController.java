package com.roberttaylor.shoply.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.roberttaylor.shoply.dao.ClientDAO;
import com.roberttaylor.shoply.entities.Client;
import com.roberttaylor.shoply.entities.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
// @CrossOrigin
public class ClientController {

    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public HashMap<String, Object> addClient(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("wallet") int wallet) {

        HashMap<String, Object> map = new HashMap<>();

        Client client = new Client();

        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setWallet(wallet);

        Client newClient = clientDAO.save(client);
        String token = getJWTToken(newClient.getUsername());

        map.put("token", token);
        map.put("user_id", newClient.getId());


        // newClient.setToken(token);
        return map;
    }
    
    @PostMapping("login")
    public HashMap<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String pwd){

        HashMap<String, Object> map = new HashMap<>();

        Client client = clientDAO.findByUsername(username);

        if(client == null){
            map.put("Error", "Username or Password not valid...");
            return map;
        }else{
            if(passwordEncoder.matches(pwd, client.getPassword())){
                String token = getJWTToken(client.getUsername());
                map.put("token", token);
                map.put("user_id", client.getId());
                return map;
            }else{
                map.put("Error", "Username or Password not valid...");
                return map;
            }
        }
        
    }

    @GetMapping("user")
    public List<Client> clients(){
       return clientDAO.findAll();
    }

    @GetMapping(value = "/{id}/reviews")
    public List<Review> user_reviews(@PathVariable UUID id){
        Client client = clientDAO.getOne(id);

        return client.getReviews();
    }

    private String getJWTToken(String username){
        String secretKey = "shopsecret";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
            .builder()
            .setId("robJWT")
            .setSubject(username)
            .claim("authorities",
                grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}