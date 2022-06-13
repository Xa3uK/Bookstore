INSERT INTO themes (theme)
VALUES ('Business'),
       ('Health');
INSERT INTO languages (language)
VALUES ('English'),
       ('Ukrainian'),
       ('French');
INSERT INTO authors (author)
VALUES ('Jan Pol Anatol'),
       ('Yasha Pistolet'),
       ('Innokentiy Smoktunovskiy'),
       ('Hulk Hogan');
INSERT INTO covers (cover_type)
VALUES ('Hard'),
       ('Soft');
INSERT INTO publishers (publisher)
VALUES ('Vidavnitstvo Veselka'),
       ('Azbuka Print');
INSERT INTO sub_themes (sub_theme, main_theme_id)
VALUES ('Biznes v Tik Toke', 1),
       ('Biznes v Instagrame', 1),
       ('Biznes na arbuzah', 1),
       ('Biznes na bazare', 1),
       ('Pravilnoe pitanie', 2),
       ('Uhod za litsom', 2);
INSERT INTO books (title, publisher_id, sub_theme_id, language_id, price, cover_id, author_id)
VALUES ('Tik Tok minutka', 1, 1, 1, 400, 1, 2),
       ('InstaRaskrutka', 2, 2, 1, 350, 1, 3),
       ('Arbuzi za 100', 1, 3, 2, 100, 2, 1),
       ('Prodavay apelsini - bud Korolem', 2, 4, 3, 150, 2, 1),
       ('Shashlichnaya dieta ot Valentiny', 1, 5, 1, 220, 2, 4),
       ('Oy vi shechki moi shechki', 2, 6, 3, 50, 2, 4),
       ('Majem litso smetanoy ot zagara', 2, 6, 3, 75, 1, 4),
       ('InstaPump ot infoTsigana', 1, 2, 2, 999, 1, 3);