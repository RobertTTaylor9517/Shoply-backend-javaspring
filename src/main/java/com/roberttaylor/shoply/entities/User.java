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
public class User extends EntityWithUUID {
    private String username;
    private String password;
    private int wallet;

    @OneToMany(mappedBy = "user")
    Set<Review> review;

}