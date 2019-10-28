package ru.home.test.survey_management.repository;

public class Filter {

    private String field;
    private Object value;

    public Filter(String field, Object value){
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
