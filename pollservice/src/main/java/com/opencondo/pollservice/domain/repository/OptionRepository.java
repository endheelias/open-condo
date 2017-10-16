package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OptionRepository extends JpaRepository<Option, Long>{

    Optional<Option> findById(Long id);


    Set<Option> findByPollId(Long pollId);
}
