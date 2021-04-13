create table posts(
    id serial primary key,
    description text not null,
    mark varchar not null ,
    body_type varchar not null,
    status varchar not null generated always as ('Not sold') stored
);

create table users(
    id serial primary key,
    name varchar not null,
    email varchar unique not null,
    phone varchar unique not null,
    password varchar not null
);