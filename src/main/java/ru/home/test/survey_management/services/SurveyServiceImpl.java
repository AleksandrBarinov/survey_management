package ru.home.test.survey_management.services;

import ru.home.test.survey_management.models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.test.survey_management.repository.SurveyRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Override
    public Survey getById(Long id) {
        return surveyRepository.getOne(id);
    }

    @Override
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public Survey editSurvey(Survey survey) {
        Survey surveyDB = surveyRepository.getOne(survey.getId());

        surveyDB.setName(survey.getName());
        surveyDB.setDateStart(survey.getDateStart());
        surveyDB.setDateEnd(survey.getDateEnd());
        surveyDB.setActive(survey.isActive());

        return saveSurvey(surveyDB);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public List<Survey> getAllSurveys(String sortBy) {
        List<Survey> surveys = surveyRepository.findAll();
        switch (sortBy){
            case "name":
                surveys.sort(Comparator.comparing(Survey::getName));
            case "date_start":
                surveys.sort(Comparator.comparing(Survey::getDateStart));
        }
        return surveys;
    }
}
