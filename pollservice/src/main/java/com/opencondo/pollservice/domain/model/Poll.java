package com.opencondo.pollservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


/**
 * Poll is the entity class representing a poll in a election
 * board, that is, a poll. Generally, a poll is a container of
 * options with a title. A poll will hold information
 * about its title, author, if it is open and its options.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

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


    /**
     * <code>Poll</code> constructor, creates a new poll instance with
     * the mandatory parameters.
     *
     * @param author      the <code>{@link UserAccount}</code> representing the poll's author
     * @param title the <code>String</code> representing the poll's title
     * @param options the <code>Set</code><<code>{@link Option}</code>>, a set which contains the options.
     */
    public Poll(UserAccount author, String title, Set<Option> options) {
        this.author = author;
        this.title = title;
        this.options = options;
        this.status = true;
    }

    @Override
    public String toString() {
        return String.format("Poll[id=%d, author='%s', title='%s', status='%s',options='%s']", id, author, title, status, options);
    }
}
