create extension if not exists pgcrypto;

create table if not exists event_publication
(
    id               uuid primary key,
    publication_date timestamp not null default now(),
    listener_id      varchar(255) not null,
    event_type       varchar(255) not null,
    serialized_event text         not null,
    completion_date  timestamp
);
