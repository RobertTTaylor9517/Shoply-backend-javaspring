package com.roberttaylor.shoply.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.roberttaylor.shoply.dao.ClientDAO;
import com.roberttaylor.shoply.entities.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String addClient(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("wallet") int wallet) {
        Client client = new Client();

        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setWallet(wallet);

        Client newClient = clientDAO.save(client);
        String token = getJWTToken(newClient.getUsername());


        // newClient.setToken(token);
        return token;
    }
    
    @PostMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd){

        Client client = clientDAO.findByUsername(username);
        if(passwordEncoder.matches(pwd, client.getPassword())){
            String token = getJWTToken(client.getUsername());
            return token;
        }else{
            return "Username or Password not valid...";
        }
        
    }

    @GetMapping("user")
    public List<Client> clients(){
       return clientDAO.findAll();
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