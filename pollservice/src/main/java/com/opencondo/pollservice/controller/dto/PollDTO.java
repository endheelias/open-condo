package com.opencondo.pollservice.controller.dto;

import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.domain.model.Poll;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class PollDTO implements DTOMapper<Poll> {


    private Long id;
    private String title;
    private Boolean status;
    private Set<OptionDTO> options;
    private UserDTO author;


    @Override
    public void buildFromEntity(Poll poll) {
        this.id = poll.getId();
        this.title = poll.getTitle();

        this.author = new UserDTO();
        this.author.buildFromEntity(poll.getAuthor());

        options = new HashSet<>();

        for (Option option : poll.getOptions()) {
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.buildFromEntity(option);
            options.add(optionDTO);
        }

        this.status = poll.getStatus();
    }
}
