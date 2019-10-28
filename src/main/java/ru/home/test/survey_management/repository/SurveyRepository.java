package ru.home.test.survey_management.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.home.test.survey_management.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {
}
