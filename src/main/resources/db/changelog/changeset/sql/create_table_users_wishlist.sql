DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id     serial PRIMARY KEY,
    username varchar(100) UNIQUE NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(100) NOT NULL
);

DROP TABLE IF EXISTS wishlist CASCADE;
CREATE TABLE wishlist
(
    id     serial PRIMARY KEY,
    user_id int NOT NULL,
    book_id int NOT NULL
);

INSERT INTO users (username, password, role)
VALUES ('admin', 'admin', 'ROLE_ADMIN'),
       ('user', 'user', 'ROLE_USER');



