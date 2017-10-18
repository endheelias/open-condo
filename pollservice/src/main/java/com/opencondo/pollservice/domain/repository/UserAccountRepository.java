package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The <code>UserAccountRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional account related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on user accounts.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
public interface UserAccountRepository  extends JpaRepository<UserAccount, Long> {

    /**
     * Searches a <code>Poll</code> based on id.
     *
     * @param id the long representing the option id
     * @return a UserAccount or null, when the id is invalid.
     */
    Optional<UserAccount> findById(Long id);

    /**
     * Searches a <code>Poll</code> based on username.
     *
     * @param username the long representing the option id
     * @return a Option or null, when the id is invalid.
     */
    Optional<UserAccount> findByUsername(String username);

}
