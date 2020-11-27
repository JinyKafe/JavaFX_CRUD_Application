drop table if exists USERS;

create TABLE users
(
    id         varchar(36) not null
        constraint users_pkey primary key,
    dob        date,
    email      varchar(255),
    first_name varchar(255),
    gender     varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    role       varchar(255)
);