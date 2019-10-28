package ru.home.test.survey_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.home.test.survey_management.models.Survey;
import ru.home.test.survey_management.repository.Filter;
import ru.home.test.survey_management.repository.SurveyRepository;
import ru.home.test.survey_management.repository.SurveySpecification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<Survey> getAllSurveys(String sortBy, int page_num, String name, String dateStart, String isActive) {

        SurveySpecification specification = new SurveySpecification();

        if (name != null){
            specification.addFilter(
                    new Filter("name", name)
            );
        }

        if (dateStart != null){
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy").parse(dateStart);
            } catch (ParseException ignored){}
            specification.addFilter(
                    new Filter("dateStart", date)
            );
        }

        if (isActive != null){
            boolean active = Boolean.valueOf(isActive);
            specification.addFilter(
                    new Filter("isActive", active)
            );
        }

        Pageable pageable = PageRequest.of(page_num - 1,4, Sort.by("id"));

        List<Survey> surveysPage = surveyRepository.findAll(specification, pageable).toList();

        return surveysPage;
    }
}
