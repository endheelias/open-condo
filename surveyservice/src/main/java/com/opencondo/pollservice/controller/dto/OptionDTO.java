package com.opencondo.pollservice.controller.dto;

import com.opencondo.pollservice.domain.model.Option;
import lombok.Getter;

@Getter
public class OptionDTO implements DTOMapper<Option> {


    private Long id;

    private Integer counterVotes;

    private String title;



    @Override
    public void buildFromEntity(Option entity) {
        this.id = entity.getId();
        this.counterVotes = entity.getCounterVotes();
        this.title = entity.getOption();
    }

}
