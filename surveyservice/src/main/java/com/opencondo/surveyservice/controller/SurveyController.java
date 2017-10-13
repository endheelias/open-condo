package com.opencondo.surveyservice.controller;


import com.opencondo.surveyservice.controller.dto.SurveyDTO;
import com.opencondo.surveyservice.domain.model.Survey;
import com.opencondo.surveyservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import java.util.Map;

@RestController
@RequestMapping("api/survey")
public class SurveyController {
    SurveyService surveyService;


    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    private SurveyDTO parserSurvey(Survey survey) {
        SurveyDTO dto = new SurveyDTO();
        dto.buildFromEntity(survey);
        return dto;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Map<String, Integer> getResultFromSurvey(Long id) {
            return this.surveyService.getResultFromSurvey(id);
    }

    @RequestMapping(path = "/{idUserAuthor}, /{title}", method = RequestMethod.POST)
    public SurveyDTO createSurvey(Long idUserAuthor, String title) {
        Survey survey = this.surveyService.createSurvey(idUserAuthor, title);
        return this.parserSurvey(survey);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SurveyDTO retrieveSurvey(Long id) {
        Survey survey = this.surveyService.retrieveSurvey(id);
        SurveyDTO dto = parserSurvey(survey);
        return dto;
    }

    @RequestMapping(path = "/{idSurvey}, /{option}", method = RequestMethod.POST)
    public ResponseEntity<?> createOptionOnSurvey(Long idSurvey, String option) {
        try {
            this.surveyService.createOptionOnSurvey(idSurvey, option);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(path = "/{idSurvey}, /{option}", method = RequestMethod.PUT)
    public ResponseEntity<?> setVote(Long idSurvey, String option) {
        try {
            this.surveyService.setVote(idSurvey, option);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(path = "/{idSurvey}, /{idAuthor}", method = RequestMethod.PUT)
    public ResponseEntity<?> closeSurvey(Long idSurvey, Long idAuthor) {
        try {
            this.surveyService.closeSurvey(idSurvey, idAuthor);
            return ResponseEntity.ok().build();

        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(path = "/{idSurvey}, /{idAuthor}", method = RequestMethod.PUT)
    public ResponseEntity<?> openSurvey(Long idSurvey, Long idAuthor) {
        try {
            this.surveyService.openSurvey(idSurvey, idAuthor);
            return ResponseEntity.ok().build();
        } catch (NoPermissionException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
