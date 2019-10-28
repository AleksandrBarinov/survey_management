create table surveys_questions (
  survey_id int8 not null,
  questions_id int8 not null
);

alter table surveys_questions
  add constraint FK1itrpmeqsbdosgk1yd5pluixo
  foreign key (questions_id) references questions;

alter table surveys_questions
  add constraint FK18lpnbibouvtocgjuqb9vt35w
  foreign key (survey_id) references surveys;
