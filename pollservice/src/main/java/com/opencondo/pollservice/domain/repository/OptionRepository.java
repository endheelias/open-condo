package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The <code>OptionRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional message related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on options.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
public interface OptionRepository extends JpaRepository<Option, Long>{

    /**
     * Searches a <code>Option</code> based on id.
     *
     * @param id the long representing the option id
     * @return a Option or null, when the id is invalid.
     */
    Optional<Option> findById(Long id);


    /**
     * Searches a <code>Set</code> of <code>Options</code> regarding a specific
     * <code>Poll</code>
     *
     * @param pollId the long representing the poll id
     * @return a list of messages
     */
    Set<Option> findByPollId(Long pollId);
}
