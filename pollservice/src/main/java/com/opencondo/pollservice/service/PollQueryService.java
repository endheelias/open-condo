package com.opencondo.pollservice.service;

import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.domain.model.UserAccount;
import com.opencondo.pollservice.domain.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



/**
 * The <code>PollQueryService</code> class is responsible for query methods regarding
 * polls. For CRUD operations look for <code>PollService</code>.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
@Service
public class PollQueryService {

    PollRepository repository;

    /**
     * Class constructor with AutoWired dependency injection.
     */
    @Autowired
    public PollQueryService(PollRepository repository) {
        this.repository = repository;
    }

    /**
     * Searches a <code>Poll</code> based on its id.
     *
     * @param pollId the <code>Long</code> id of the poll.
     * @return a list of messages, sorted by creation time.
     */
    public Poll findByPollId(Long pollId) {
        return repository.findById(pollId).get();
    }


    /**
     * Verify if the user is the poll's author.
     *
     * @param pollId the <code>Long</code> id of the poll.
     * @param userId the <code>Long</code> id of the user.
     * @return a list of messages, sorted by creation time.
     */
    boolean isAuthor(Long pollId, Long userId) {
        Optional<Poll> optionalSurvey = repository.findById(pollId);
        Poll poll = optionalSurvey.get();

        UserAccount author = poll.getAuthor();

        if (author.getId() == userId) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update a <code>Poll</code> on database.
     *
     * @param poll the <code>Poll</code> object.
     * @return a updated poll.
     */
    public Poll updatePoll(Poll poll) {
        repository.save(poll);
        return poll;
    }
}
