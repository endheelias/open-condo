package com.opencondo.pollservice.service;


import com.opencondo.pollservice.domain.model.UserAccount;
import com.opencondo.pollservice.domain.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountQueryService {

    private UserAccountRepository repository;

    @Autowired
    public UserAccountQueryService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public UserAccount getUserAccountById(Long idAuthor) {
        return repository.findById(idAuthor).get();
    }
}
