package ru.home.test.survey_management.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.home.test.survey_management.models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.test.survey_management.repository.SurveyRepository;

import java.util.*;

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
    public List<Survey> getAllSurveys(String sortBy, int page_num) {
        Pageable pageable = PageRequest.of(page_num + 1,4);

        List<Survey> surveysPage = new ArrayList<>(
                surveyRepository.findAll(pageable).toList()
        );
        switch (sortBy){
            case "name":
                surveysPage.sort(Comparator.comparing(Survey::getName));
                break;
            case "date_start":
                surveysPage.sort(Comparator.comparing(Survey::getDateStart));
                break;
        }
        return surveysPage;
    }
}
