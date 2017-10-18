package com.opencondo.pollservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * UserAccount is the entity class for representing simple information
 * about an user account. As this service is not responsible for managing
 * accounts, therefore, the info hold by this class is pretty basic and
 * for further info about this user, check the account service using the
 * username property. An UserAccount holds information about its username,
 * name and all poll that he created.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

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
    private Set<Poll> poll;


    private UserAccount() {
    }

    /**
     * <code>UserAccount</code> constructor, creates a new user account instance with
     * the mandatory parameters.
     *
     * @param username the <code>String</code> representing the username.
     * @param name     the <code>String</code> with the user's full name.
     */
    public UserAccount(String username, String name) {
        this.username = username;
        this.name = name;
        this.poll = new HashSet<>();
    }
}
