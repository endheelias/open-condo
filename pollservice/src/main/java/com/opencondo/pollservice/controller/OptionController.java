package com.opencondo.pollservice.controller;

import com.opencondo.pollservice.controller.dto.OptionDTO;
import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


/**
 * Rest endpoint for title resource, including CRUD and query operations.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("api/option")
public class OptionController {

    OptionService optionService;

    /**
     * Class constructor with AutoWired dependencies injection.
     */
    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }


    /**
     * Set a vote for an title.
     *
     * @param optionId the Long title id.
     * @return 200 (html code) if the title exists and 400 (html code) if it doesn't.
     */
    @RequestMapping(path = "/{optionId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setVote(@PathVariable("optionId") Long optionId) {
        try {
            optionService.setVote(optionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    /**
     * Get a title set from a poll.
     *
     * @param pollId the poll id.
     * @return a title set.
     */
    @RequestMapping(path = "/{pollId}", method = RequestMethod.GET)
    public Set<OptionDTO> getOptionsFromPoll(@PathVariable("pollId") Long pollId) {

        Set<Option> options = optionService.getOptionsFromPoll(pollId);
        Set<OptionDTO> dtos = new HashSet<>();

        options.forEach(option -> {
            OptionDTO dto = new OptionDTO();
            dto.buildFromEntity(option);
            dtos.add(dto);
        });

        return dtos;
    }

    /**
     * Create a title on poll, just the poll author can create an title.
     *
     * @param pollId      the poll id.
     * @param optionTitle the optionTitle title
     * @return 200 (html code) if the the author can create a title and 400 (html code) if he can't.
     */
    @RequestMapping(path = "/{pollId}/{authorId}", method = RequestMethod.POST)
    public ResponseEntity<?> createOptionOnPoll(@PathVariable(value = "pollId") Long pollId,
                                                @RequestBody String optionTitle,
                                                @PathVariable(value = "authorId") Long authorId) {
        Option option = this.optionService.createOptionOnPoll(pollId, optionTitle, authorId);

        if (option != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
