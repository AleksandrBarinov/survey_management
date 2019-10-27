package ru.home.test.survey_management.controller;

import ru.home.test.survey_management.models.Survey;
import org.springframework.web.bind.annotation.*;
import ru.home.test.survey_management.services.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public List<Survey> getAllSurveys(@RequestParam String sort_by) {
        return surveyService.getAllSurveys(sort_by);
    }

    @GetMapping("{id}")
    public Survey getSurvey(@PathVariable("id") Long id) {
        return surveyService.getById(id);
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.saveSurvey(survey);
    }

    @PutMapping
    public Survey editSurvey(@RequestBody Survey survey) {
        return surveyService.editSurvey(survey);
    }

    @DeleteMapping("{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }
}
