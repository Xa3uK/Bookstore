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
INSERT INTO books (title, publisher_id, sub_theme_id, language_id, vendor_code, pages, price,
                   cover_id, image_url, year, author_id)
VALUES ('Tik Tok minutka', 1, 1, 1, 123, 222, 400, 1, 'ssilka-1', 2021, 2),
       ('InstaRaskrutka', 2, 2, 1, 432, 31, 350, 1, 'ssilka-2', 2015, 3),
       ('Arbuzi za 100', 1, 3, 2, 23, 12, 100, 2, 'ssilka-3', 1990, 1),
       ('Prodavay apelsini - bud Korolem', 2, 4, 3, 66, 43, 150, 2, 'ssilka-4', 1985, 1),
       ('Shashlichnaya dieta ot Valentiny', 1, 5, 1, 4311, 56, 220, 2, 'ssilka-5', 2007, 4),
       ('Oy vi shechki moi shechki', 2, 6, 3, 311, 67, 50, 2, 'ssilka-6', 2011, 4),
       ('Majem litso smetanoy ot zagara', 2, 6, 3, 21, 21, 75, 1, 'ssilka-7', 2011, 4),
       ('InstaPump ot infoTsigana', 1, 2, 2, 102, 151, 999, 1, 'ssilka-8', 2018, 3);