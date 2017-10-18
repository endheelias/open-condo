package com.opencondo.pollservice.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Option is the entity class representing a title in a poll.
 * Users can vote on title if he want that title wins the election.
 * A title contains the id, vote counter, a title and the Poll which belongs with.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

@Getter
@Setter
@Entity
public class Option {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100000)
    private Integer counterVotes;

    @Column(nullable = false, length = 100000)
    private String title;

    @ManyToOne
    private Poll poll;

    /**
     * <code>Option</code> constructor, creates a new title instance with the
     * mandatory parameters.
     *
     * @param title the <code>String</code> holding the option's title
     */
    public Option(String title) {
        this.counterVotes = new Integer(0);
        this.title = title;
    }

    /**
     * Protected constructor, please use the one with parameters, that are required
     * for this object.
     */
    private Option() {
    }


    @Override
    public String toString() {
        return String.format("Option[id=%d, counterVotes='%d', title='%s', poll='%s']", id, counterVotes, title, poll);
    }
}
