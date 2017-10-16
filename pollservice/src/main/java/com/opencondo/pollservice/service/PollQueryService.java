package com.opencondo.pollservice.service;

import com.opencondo.pollservice.domain.model.Poll;
import com.opencondo.pollservice.domain.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollQueryService {

    PollRepository repository;

    @Autowired
    public PollQueryService(PollRepository repository) {
        this.repository = repository;
    }

    public Poll findByPollId(Long pollId) {
        return repository.findById(pollId).get();
    }


    public Poll updatePoll(Poll poll) {
        repository.save(poll);
        return poll;
    }
}
