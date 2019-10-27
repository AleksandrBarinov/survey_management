package ru.home.test.survey_management.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.home.test.survey_management.models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.test.survey_management.repository.SurveyRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Survey> getAllSurveys(String sortBy, int page_num, String filter, String filterValue) throws ParseException {

        Pageable pageable = PageRequest.of(page_num - 1,4, Sort.by(sortBy));

        List<Survey> surveysPage = surveyRepository.findAll(pageable).toList();

        if (filter != null && filterValue != null) {
            switch (filter) {
                case "name":
                    surveysPage = surveysPage.stream()
                            .filter(survey -> survey.getName().equals(filterValue))
                            .collect(Collectors.toList());
                    break;

                case "dateStart":
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    surveysPage = surveysPage.stream().filter(survey ->
                            dateFormat.format(survey.getDateStart()).equals(filterValue))
                            .collect(Collectors.toList());
                    break;

                case "isActive":
                    surveysPage = surveysPage.stream()
                            .filter(survey -> survey.isActive() == Boolean.valueOf(filterValue))
                            .collect(Collectors.toList());
                    break;
            }
        }
        return surveysPage;
    }
}
