package com.opencondo.pollservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    UserAccount author;

    @Column(nullable = false, length = 100000)
    String title;

    @Column(nullable = false, length = 100000)
    Boolean status;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    Set<Option> options = new HashSet<>();
    /**
     * Protected constructor, please use the one with parameters, that are required
     * for this object.
     */
    protected Poll() {
    }

    public Poll(UserAccount author, String title, Set<Option> options) {
        this.author = author;
        this.title = title;
        this.options = options;
    }

}
