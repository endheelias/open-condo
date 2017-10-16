package com.opencondo.pollservice.controller;


import com.opencondo.pollservice.controller.dto.PollDTO;
import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import javax.swing.*;
import java.util.Set;

@RestController
@RequestMapping("api/poll")
public class PollController {

    PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
        //
    }

    private PollDTO parserPoll(Poll poll) {
        PollDTO dto = new PollDTO();
        dto.buildFromEntity(poll);
        return dto;
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public void init() {
//        System.out.println("AQUIIII");
//        this.pollService.init();
//    }

    @RequestMapping(path = "/{idAuthor}", method = RequestMethod.POST)
    public PollDTO createPoll(@PathVariable(value = "idAuthor") Long idAuthor,
                              @RequestBody String title,
                              @RequestBody Set<String> optionsTitle) {

        Poll poll = this.pollService.createPoll(idAuthor, title, optionsTitle);
        return this.parserPoll(poll);
    }

    @RequestMapping(path = "/{pollId}", method = RequestMethod.GET)
    public PollDTO retrievePoll(@PathVariable(value = "pollId") Long pollId) {
        Poll poll = this.pollService.retrievePoll(pollId);
        PollDTO dto = parserPoll(poll);
        return dto;
    }


    @RequestMapping(path = "/{idPoll}/{authorId}", method = RequestMethod.PUT)
    public ResponseEntity<?> openPoll(@PathVariable(value = "pollId") Long pollId,
                                      @PathVariable(value = "authorId") Long authorId) {
        try {
            this.pollService.openPoll(pollId, authorId);
            return ResponseEntity.ok().build();

        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(path = "/{idPoll}/{authorId}", method = RequestMethod.POST)
    public ResponseEntity<?> closePoll(@PathVariable(value = "pollId") Long pollId,
                                       @PathVariable(value = "authorId") Long authorId) {
        try {
            this.pollService.openPoll(pollId, authorId);
            return ResponseEntity.ok().build();

        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
