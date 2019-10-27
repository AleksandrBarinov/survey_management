package ru.home.test.survey_management.services;

import ru.home.test.survey_management.models.Survey;

import java.util.List;

public interface SurveyService {

    Survey getById(Long id);

    Survey saveSurvey(Survey survey);

    Survey editSurvey(Survey survey);

    void deleteSurvey(Long id);

    List<Survey> getAllSurveys(String sortBy);
}
