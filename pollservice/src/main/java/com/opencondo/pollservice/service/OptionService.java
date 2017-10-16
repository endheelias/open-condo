package com.opencondo.pollservice.service;

import com.opencondo.pollservice.domain.model.Option;
import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.domain.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OptionService {

    OptionRepository repository;
    PollQueryService pollQueryService;


    @Autowired
    public OptionService(OptionRepository repository, PollQueryService pollQueryService) {
        this.repository = repository;
        this.pollQueryService = pollQueryService;
    }

    Option createOption(String title) {
        Option option = new Option(title);
        repository.save(option);
        return option;
    }

    public void setVote(Long idOption) throws Exception {
        try {
            Option option = repository.findById(idOption).get();

            int counter = option.getCounterVotes();
            counter++;

            option.setCounterVotes(counter);

            repository.save(option);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public Set<Option> getOptionsFromPoll(Long pollId) {
        return repository.findByPollId(pollId);
    }

    public Option createOptionOnPoll(Long pollId, String optionTitle) {
        Poll poll = pollQueryService.findByPollId(pollId);
        Option option = new Option(optionTitle);

        Set<Option> options = poll.getOptions();
        options.add(option);
        pollQueryService.updatePoll(poll);
        return option;
    }
}
