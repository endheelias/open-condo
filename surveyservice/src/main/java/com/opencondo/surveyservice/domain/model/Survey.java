package com.opencondo.surveyservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Survey {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100000)
    Long idUserAuthor;

    @Column(nullable = false, length = 100000)
    String title;

    @Column(nullable = false, length = 100000)
    Set<String> options;

    @Column(nullable = false, length = 100000)
    Boolean status;

    @Column
    Map<String, Integer> result;

    /**
     * Protected constructor, please use the one with parameters, that are required
     * for this object.
     */
    protected Survey() {
    }

    public Survey(Long idUserAuthor, String title) {
        this.idUserAuthor = idUserAuthor;
        this.title = title;
        this.options = new HashSet<>();
        this.result = new HashMap<>();
    }

}
