create table surveys (
  id int8 not null,
  date_end timestamp,
  date_start timestamp,
  is_active boolean not null,
  name varchar(255),
  primary key (id)
);
