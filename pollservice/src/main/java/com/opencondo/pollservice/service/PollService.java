package com.opencondo.pollservice.service;

import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.domain.model.UserAccount;
import com.opencondo.pollservice.domain.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The <code>PollService</code> class is responsible for CRUD operations regarding
 * option. For queries methods look for <code>MessageQueryService</code>. As a service
 * this class uses only its related repository, in this case <code>MessageRepository</code>,
 * operations on others entities are performed using the related services.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
@Service
public class PollService {

    PollRepository repository;
    OptionService optionService;
    UserAccountQueryService userAccountQueryService;
    PollQueryService pollQueryService;

    /**
     * Class constructor with AutoWired dependency injection.
     */
    @Autowired
    public PollService(PollRepository repository,
                       OptionService optionService,
                       UserAccountQueryService userAccountQueryService,
                       PollQueryService pollQueryService) {
        this.repository = repository;
        this.optionService = optionService;
        this.userAccountQueryService = userAccountQueryService;
        this.pollQueryService = pollQueryService;
    }

    /**
     * Create a <code>Poll</code> based on author's id, title and title of options.
     *
     * @param authorId the <code>Long</code> id of the author.
     * @param title    the <code>String</code> with pagination page.
     * @param optionsTitle    <code>Set</code><<code>String</code>>, a set with all title of options.
     * @return a Poll.
     */
    public Poll createPoll(Long authorId, String title, Set<String> optionsTitle) {
        UserAccount author = userAccountQueryService.getUserAccountById(authorId);

        Set<Option> options = new HashSet<>();


        optionsTitle.forEach(optionTitle -> {
            Option op = optionService.createOption(optionTitle);
            options.add(op);

        });

        Poll poll = new Poll(author, title, options);

        repository.save(poll);
        return poll;
    }

    /**
     * Get a <code>Poll</code> from database, based on poll's id.
     *
     * @param id the <code>Long</code> id of the poll.
     * @return a Poll.
     */
    public Poll retrievePoll(Long id) {
        Optional<Poll> optionalPoll = repository.findById(id);
        //TODO: throw new exception for not found and remove this null
        return optionalPoll.orElse(null);
    }

    /**
     * Close a election in a <code>Poll</code>, just the poll's author can open it.
     *
     * @param pollId the <code>Long</code> id of the poll.
     * @param authorId the <code>Long</code> id of the author.
     */
    public void closePoll(Long pollId, Long authorId) throws NoPermissionException {
        if (this.pollQueryService.isAuthor(pollId, authorId)) {
            Optional<Poll> optionalSurvey = repository.findById(pollId);
            Poll poll = optionalSurvey.get();

            poll.setStatus(Boolean.FALSE);
            repository.save(poll);
        } else {
            throw new NoPermissionException();
        }
    }

    /**
     * Open a election in a <code>Poll</code>, just the poll's author can open it.
     *
     * @param pollId the <code>Long</code> id of the poll.
     * @param authorId the <code>Long</code> id of the author.
     */
    public void openPoll(Long pollId, Long authorId) throws NoPermissionException {
        Optional<Poll> optionalSurvey = repository.findById(pollId);

        if (this.pollQueryService.isAuthor(pollId, authorId)) {
            Poll poll = optionalSurvey.get();

            poll.setStatus(Boolean.TRUE);
            repository.save(poll);
        } else {
            throw new NoPermissionException();
        }
    }


}