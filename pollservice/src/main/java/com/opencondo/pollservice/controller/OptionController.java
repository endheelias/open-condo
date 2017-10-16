package com.opencondo.pollservice.controller;

import com.opencondo.pollservice.controller.dto.OptionDTO;
import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/option")
public class OptionController {

    OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> setVote(@PathVariable("optionId") Long optionId) {
        try {

            optionService.setVote(optionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<OptionDTO> getOptionsFromPoll(@PathVariable("pollid") Long pollId) {

        Set<Option> options = optionService.getOptionsFromPoll(pollId);
        Set<OptionDTO> dtos = new HashSet<>();

        options.forEach(option -> {
            OptionDTO dto = new OptionDTO();
            dto.buildFromEntity(option);
            dtos.add(dto);
        });

        return dtos;
    }

    @RequestMapping(path = "/{pollId}", method = RequestMethod.POST)
    public ResponseEntity<?> createOptionOnPoll(@PathVariable(value = "pollId") Long pollId,
                                                @RequestBody String option) {
        try {
            this.optionService.createOptionOnPoll(pollId, option);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

}
