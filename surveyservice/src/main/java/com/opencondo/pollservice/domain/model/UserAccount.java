package com.opencondo.pollservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Poll> poll = new HashSet<>();



    protected UserAccount() {
    }

    /**
     * <code>UserAccount</code> constructor, creates a new user account instance with
     * the mandatory parameters.
     *
     * @param username the <code>String</code> representing the username.
     * @param name the <code>String</code> with the user's full name.
     */
    public UserAccount(String username, String name) {
        this.username = username;
        this.name = name;
    }
}
