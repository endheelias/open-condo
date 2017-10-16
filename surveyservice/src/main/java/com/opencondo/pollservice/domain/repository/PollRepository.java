package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long>{

    Optional<Poll> findById(Long id);
}
