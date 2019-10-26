package ru.home.test.survey_management.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
public class Question extends BaseEntity{
    @OneToOne
    private Survey survey;
    private String text;
    private int indexNumber;
}
