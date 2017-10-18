package com.opencondo.pollservice.controller.dto;

import com.opencondo.pollservice.domain.model.Option;
import lombok.Getter;


/**
 * Data Transfer Object for the Option Entity
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

@Getter
public class OptionDTO implements DTOMapper<Option> {


    private Long id;

    private Integer counterVotes;

    private String title;


    @Override
    public String toString() {
        return String.format("OptionDTO[id=%d, counterVotes='%d', title='%s']", id, counterVotes, title);
    }


    /**
     * Fills the DTO with information from the <code>Message</code> entity.
     *
     * @param entity the <code>Message</code> entity
     */
    @Override
    public void buildFromEntity(Option entity) {
        this.id = entity.getId();
        this.counterVotes = entity.getCounterVotes();
        this.title = entity.getTitle();
    }

}
