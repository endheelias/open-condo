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

@Service
public class PollService {

    PollRepository repository;
    OptionService optionService;
    UserAccountQueryService userAccountQueryService;


    public void init() {
        Long idAuthor1 = 1l;
        Long idAuthor2 = 2l;

        String op1 = "autor 1 - opcao 1";
        String op2 = "autor 1 - opcao 2";

        Set<String> opsAuthor1 = new HashSet<>();
        opsAuthor1.add(op1);
        opsAuthor1.add(op2);


        String op3 = "author 2 - opcao 3";
        String op4 = "author 2 - opcao 4";

        Set<String> opsAuthor2 = new HashSet<>();
        opsAuthor1.add(op3);
        opsAuthor1.add(op4);

        this.createPoll(idAuthor1, "Enquete Autor 1",  opsAuthor1);
        this.createPoll(idAuthor2, "Enquete Autor 1",  opsAuthor2);
    }

    @Autowired
    public PollService(PollRepository repository, OptionService optionService, UserAccountQueryService userAccountQueryService) {
        this.repository = repository;
        this.optionService = optionService;
        this.userAccountQueryService = userAccountQueryService;
    }

    public Poll createPoll(Long idAuthor, String title, Set<String> optionsTitle) {
        UserAccount author = userAccountQueryService.getUserAccountById(idAuthor);

        Set<Option> options = new HashSet<>();


        optionsTitle.forEach(optionTitle -> {
            Option op = optionService.createOption(optionTitle);
            options.add(op);

        });

        Poll poll = new Poll(author, title, options);

        repository.save(poll);
        return poll;
    }

    public Poll retrievePoll(Long id) {
        Optional<Poll> optionalPoll = repository.findById(id);
        //TODO: throw new exception for not found and remove this null
        return optionalPoll.orElse(null);
    }


    public void closePoll(Long idPoll, Long idAuthor) throws NoPermissionException {
        if (isAuthor(idPoll, idAuthor)) {
            Optional<Poll> optionalSurvey = repository.findById(idPoll);
            Poll poll = optionalSurvey.get();

            poll.setStatus(Boolean.FALSE);
            repository.save(poll);
        } else {
            throw new NoPermissionException();
        }
    }

    public void openPoll(Long idSurvey, Long idAuthor) throws NoPermissionException {
        Optional<Poll> optionalSurvey = repository.findById(idSurvey);

        if (isAuthor(idSurvey, idAuthor)) {
            Poll poll = optionalSurvey.get();

            poll.setStatus(Boolean.TRUE);
            repository.save(poll);
        } else {
            throw new NoPermissionException();
        }
    }

    private boolean isAuthor(Long idPoll, Long idAuthor) {
        Optional<Poll> optionalSurvey = repository.findById(idPoll);
        Poll poll = optionalSurvey.get();

        UserAccount author = poll.getAuthor();

        if (author.getId() == idAuthor) {
            return true;
        } else {
            return false;
        }
    }

}