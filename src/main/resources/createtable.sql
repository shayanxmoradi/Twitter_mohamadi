set schema 'twitter2';
create table if not exists users
(
    id       serial primary key,
    username varchar(50) unique,
    password varchar(10)
);

create table if not exists tweet
(
    id          serial primary key,
    user_id     int references users (id),
    content     text,
    create_time time,
    create_date date
);