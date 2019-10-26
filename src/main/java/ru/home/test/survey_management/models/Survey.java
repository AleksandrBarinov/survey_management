package ru.home.test.survey_management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

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
    @ManyToMany(cascade = CascadeType.ALL)
    List<Question> questions;
}
