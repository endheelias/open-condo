package com.opencondo.pollservice.service;

import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.domain.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * The <code>OptionService</code> class is responsible for CRUD operations regarding
 * option. For queries methods look for <code>PollQueryService</code>. As a service
 * this class uses only its related repository, in this case <code>PollRepository</code>,
 * operations on others entities are performed using the related services.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
@Service
public class OptionService {

    OptionRepository repository;
    PollQueryService pollQueryService;

    /**
     * Class constructor with AutoWired dependencies injection.
     */
    @Autowired
    public OptionService(OptionRepository repository, PollQueryService pollQueryService) {
        this.repository = repository;
        this.pollQueryService = pollQueryService;
    }

    /**
     * Creates a new <code>Option</code> based on the title.
     *
     * @param title the <code>String</code> with option's title.
     * @return a new, persisted, option
     */
    protected Option createOption(String title) {
        Option option = new Option(title);
        repository.save(option);
        return option;
    }


    /**
     * Set a vote for a specific option.
     *
     * @param optionId the <code>Long</code> with option's id.
     */
    public void setVote(Long optionId) throws Exception {
        try {
            Option option = repository.findById(optionId).get();

            int counter = option.getCounterVotes();
            counter++;

            option.setCounterVotes(counter);

            repository.save(option);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * Get all options from a specific poll.
     *
     * @param pollId the <code>Long</code> with poll's id.
     */
    public Set<Option> getOptionsFromPoll(Long pollId) {
        return repository.findByPollId(pollId);
    }

    /**
     * Creates a new <code>Option</code> based on the poll, topic and author.
     *
     * @param pollId      the <code>Long</code> with poll's id.
     * @param optionTitle the <code>String</code>option's title.
     * @return a new, persisted, message
     */
    public Option createOptionOnPoll(Long pollId, String optionTitle, Long authorId) {
        if (this.pollQueryService.isAuthor(pollId, authorId)) {
            Poll poll = pollQueryService.findByPollId(pollId);
            Option option = new Option(optionTitle);

            Set<Option> options = poll.getOptions();
            options.add(option);
            pollQueryService.updatePoll(poll);
            return option;
        }
        return null;
    }
}
