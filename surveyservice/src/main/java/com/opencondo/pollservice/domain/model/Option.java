package com.opencondo.pollservice.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String option;

    @ManyToOne
    private Poll poll;

    public Option(String title) {
        this.counterVotes = new Integer(0);
        this.option = title;
    }

    private Option() {
    }
}
