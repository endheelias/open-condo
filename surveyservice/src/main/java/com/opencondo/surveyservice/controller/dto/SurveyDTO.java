package com.opencondo.surveyservice.controller.dto;

import com.opencondo.surveyservice.domain.model.Survey;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class SurveyDTO implements DTOMapper<Survey> {


    private Long id;
    private Long idUserAuthor;
    private String title;
    private Set<String> options;
    private Boolean status;
    private Map<String, Integer> result;


    @Override
    public void buildFromEntity(Survey survey) {
        this.id = survey.getId();
        this.idUserAuthor = survey.getIdUserAuthor();
        this.title = survey.getTitle();
        this.options = survey.getOptions();
        this.result = survey.getResult();
    }
}
