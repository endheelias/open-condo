package com.opencondo.surveyservice.domain.repository;

import com.opencondo.surveyservice.domain.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

    Optional<Survey> findById(Long id);
}
