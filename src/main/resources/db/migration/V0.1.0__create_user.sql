create table users
(
    id         bigserial primary key,
    username   varchar(128) not null,
    full_name  varchar(1024) not null,
    password   varchar(1024) not null,
    email      varchar(255) not null,
    role       varchar(30)  not null,
    created_at date default now(),
    updated_at date default now(),
    unique (username),
    unique (email)
);
