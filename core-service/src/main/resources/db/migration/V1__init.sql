CREATE TABLE categories
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);


CREATE TABLE products
(

    id          BIGSERIAL PRIMARY KEY,
    product_id  INTEGER,
    title       VARCHAR(255),
    price       NUMERIC(9, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp

);

insert into categories(title)
values ('Food'),
       ('Electronics');

INSERT INTO products (title, product_id, price, category_id)
VALUES ('water', 10, 38.00, 1),
       ('milk', 11, 54.00, 1),
       ('bread', 12, 33.00, 1),
       ('cheese', 13, 156.00, 1);


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

insert into users(username,password,email)
values ('dima','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','dima@mail.ru'),
       ('masha','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'masha@mail.ru');

insert into users_roles(user_id, role_id)
values (1,1),
       (2,2);


create table orders
(
    id              bigserial primary key,
    user_id         bigint not null references users (id),
    total_price     numeric(8, 2),
    address         varchar(255) not null,
    phone           varchar(255) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references users (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int not null,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);