CREATE TABLE users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(80) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

CREATE TABLE roles
(
    id         bigserial primary key,
    name       varchar(36) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_id)

);

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users(username, password, email)
values ('dima', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'dima@mail.ru'),
       ('masha', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'masha@mail.ru');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);
