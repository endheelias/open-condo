package com.opencondo.surveyservice.service;

import com.opencondo.surveyservice.domain.model.Survey;
import com.opencondo.surveyservice.domain.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class SurveyService {
    SurveyRepository repository;

    public SurveyService(SurveyRepository repository) {
        this.repository = repository;
    }

    public Survey createSurvey(Long idUserAuthor, String title) {
        Survey survey = new Survey(idUserAuthor, title);

        repository.save(survey);
        return survey;
    }

    public Survey retrieveSurvey(Long id) {
        Optional<Survey> optionalSurvey = repository.findById(id);
        //TODO: throw new exception for not found and remove this null
        return optionalSurvey.orElse(null);
    }

    public void createOptionOnSurvey(Long idSurvey, String option) throws Exception {
        try {
            Optional<Survey> optionalSurvey = repository.findById(idSurvey);

            Survey survey = optionalSurvey.get();

            Set<String> options = survey.getOptions();
            options.add(option);

            survey.setOptions(options);

            Map<String, Integer> result = survey.getResult();
            result.put(option, new Integer(0));

            survey.setResult(result);

            repository.save(survey);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void setVote(Long idSurvey, String option) throws Exception {
        try {
            Optional<Survey> optionalSurvey = repository.findById(idSurvey);

            Survey survey = optionalSurvey.get();
            Map<String, Integer> result = survey.getResult();
            int counter = result.get(option);

            result.put(option, new Integer(++counter));
            survey.setResult(result);

            repository.save(survey);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public void closeSurvey(Long idSurvey, Long idAuthor) throws NoPermissionException {
        if (isAuthor(idSurvey, idAuthor)) {
            Optional<Survey> optionalSurvey = repository.findById(idSurvey);
            Survey survey = optionalSurvey.get();

            survey.setStatus(Boolean.FALSE);
            repository.save(survey);
        } else {
            throw new NoPermissionException();
        }
    }

    public void openSurvey(Long idSurvey, Long idAuthor) throws NoPermissionException {
        Optional<Survey> optionalSurvey = repository.findById(idSurvey);

        if (isAuthor(idSurvey, idAuthor)) {
            Survey survey = optionalSurvey.get();

            survey.setStatus(Boolean.TRUE);
            repository.save(survey);
        } else {
            throw new NoPermissionException();
        }
    }

    private boolean isAuthor(Long idSurvey, Long idAuthor) {
        Optional<Survey> optionalSurvey = repository.findById(idSurvey);
        Survey survey = optionalSurvey.get();

        if (idAuthor == survey.getIdUserAuthor()) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Integer> getResultFromSurvey(Long surveyId) {
        Optional<Survey> optionalSurvey = repository.findById(surveyId);
        Survey survey = optionalSurvey.get();

        if (survey != null) {
            return survey.getResult();
        }
        return null;

    }
}