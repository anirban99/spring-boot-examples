drop table if exists employees;

create table employees (
    id              bigserial,
    user_name       varchar(50) not null,
    first_name      varchar(50) not null,
    middle_name     varchar(50),
    last_name       varchar(50) not null,
    email_address   varchar(100) not null,
    day_of_birth    date not null,
    primary key (id),
    unique (user_name)
);