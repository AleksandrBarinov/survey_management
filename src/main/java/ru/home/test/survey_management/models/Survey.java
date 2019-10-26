package ru.home.test.survey_management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Survey extends BaseEntity{
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private boolean isActive;
}
