

DROP TABLE IF EXISTS Themes cascade;
CREATE TABLE themes (id serial PRIMARY KEY,theme varchar(100) UNIQUE NOT NULL);

DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors
(
    id     serial PRIMARY KEY,
    author varchar(100) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS covers CASCADE;
CREATE TABLE covers
(
    id         serial PRIMARY KEY,
    cover_type varchar(100) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS languages CASCADE;
CREATE TABLE languages
(
    id       serial PRIMARY KEY,
    language varchar(50) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS publishers CASCADE;
CREATE TABLE publishers
(
    id        serial PRIMARY KEY,
    publisher varchar(100) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS sub_themes CASCADE;
CREATE TABLE sub_themes
(
    id            serial PRIMARY KEY,
    sub_theme     varchar(100) UNIQUE NOT NULL,
    main_theme_id int                 NOT NULL,
    FOREIGN KEY (main_theme_id) REFERENCES themes (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books
(
    id           serial PRIMARY KEY,
    title        varchar(100)        NOT NULL,
    price        numeric             NOT NULL,
    publisher_id int                 NOT NULL,
    sub_theme_id int                 NOT NULL,
    language_id  int                 NOT NULL,
    cover_id     int                 NOT NULL,
    author_id    int                 NOT NULL,
    FOREIGN KEY (publisher_id) REFERENCES publishers (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sub_theme_id) REFERENCES sub_themes (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cover_id) REFERENCES covers (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX index ON books USING btree (price);
