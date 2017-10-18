package com.opencondo.pollservice.controller;


import com.opencondo.pollservice.controller.dto.PollDTO;
import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.Set;

/**
 * Rest endpoint for poll resource, including CRUD and query operations.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

@RestController
@RequestMapping("api/poll")
public class PollController {

    PollService pollService;

    /**
     * Class constructor with AutoWired dependencies injection.
     */
    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    /**
     * Parses an entity model Poll to a PollDTO.
     *
     * @param poll a <code>Poll</code> instance.
     * @return the poll data transfer object.
     */
    private PollDTO parserPoll(Poll poll) {
        PollDTO dto = new PollDTO();
        dto.buildFromEntity(poll);
        return dto;
    }


    /**
     * Create a poll.
     *
     * @param idAuthor the author id.
     * @param title the poll title.
     * @param optionsTitle the set of String which contains the options title.
     * @return the created poll.
     */
    @RequestMapping(path = "/{idAuthor}", method = RequestMethod.POST)
    public PollDTO createPoll(@PathVariable(value = "idAuthor") Long idAuthor,
                              @RequestBody String title,
                              @RequestBody Set<String> optionsTitle) {

        Poll poll = this.pollService.createPoll(idAuthor, title, optionsTitle);
        return this.parserPoll(poll);
    }

    /**
     * Retrieve a poll.
     *
     * @param pollId the poll id.
     * @return the retrieved poll.
     */
    @RequestMapping(path = "/{pollId}", method = RequestMethod.GET)
    public PollDTO retrievePoll(@PathVariable(value = "pollId") Long pollId) {
        Poll poll = this.pollService.retrievePoll(pollId);
        PollDTO dto = parserPoll(poll);
        return dto;
    }


    /**
     * Open a specific poll, just the poll author can open it.
     *
     * @param pollId the poll id.
     * @param authorId the author id.
     * @return 200 if successful or 400 if doesn't.
     */
    @RequestMapping(path = "open/{idPoll}/{authorId}", method = RequestMethod.PUT)
    public ResponseEntity<?> openPoll(@PathVariable(value = "pollId") Long pollId,
                                      @PathVariable(value = "authorId") Long authorId) {
        try {
            this.pollService.openPoll(pollId, authorId);
            return ResponseEntity.ok().build();

        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    /**
     * Close a specific poll, just the poll author can close it.
     *
     * @param pollId the poll id.
     * @param authorId the author id.
     * @return 200 if successful or 400 if doesn't.
     */
    @RequestMapping(path = "close/{idPoll}/{authorId}", method = RequestMethod.PUT)
    public ResponseEntity<?> closePoll(@PathVariable(value = "pollId") Long pollId,
                                       @PathVariable(value = "authorId") Long authorId) {
        try {
            this.pollService.closePoll(pollId, authorId);
            return ResponseEntity.ok().build();

        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
