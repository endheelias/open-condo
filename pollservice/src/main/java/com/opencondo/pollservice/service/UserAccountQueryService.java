package com.opencondo.pollservice.service;


import com.opencondo.pollservice.domain.model.UserAccount;
import com.opencondo.pollservice.domain.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The <code>UserAccountQueryService</code> class is responsible for query methods regarding
 * user accounts. For CRUD operations look for user account micro service.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

@Service
public class UserAccountQueryService {

    private UserAccountRepository repository;

    /**
     * Class constructor with AutoWired dependency injection.
     */
    @Autowired
    public UserAccountQueryService(UserAccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Searches a <code>UserAccount</code> based on his id.
     *
     * @param authorId the <code>Long</code> id of the user's account.
     * @return <code>{@link UserAccount}</code>.
     */
    public UserAccount getUserAccountById(Long authorId) {
        return repository.findById(authorId).get();
    }
}
