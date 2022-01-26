create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Молоко', 100),
       ('Сыр', 202),
       ('Лимонад', 230),
       ('Колбаса', 150);

