package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The <code>PollRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional message related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on polls.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
public interface PollRepository extends JpaRepository<Poll, Long>{

    /**
     * Searches a <code>Poll</code> based on id.
     *
     * @param id the long representing the option id
     * @return a Option or null, when the id is invalid.
     */
    Optional<Poll> findById(Long id);
}
