-- Képek inicializálása
INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, created, image_name, mime_type)
VALUES (0, '2019-11-20 17:14:19', '2019-11-20 17:14:19', '33f09af8-5914-49a9-b490-8f851179fa6d', 55810,
        '2019-11-20 17:14:18', 'img1.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, created, image_name, mime_type)
VALUES (1, '2019-11-20 17:14:23', '2019-11-20 17:14:23', '67eb3eca-749b-47a6-a9da-b9b8ace38ca2', 31132,
        '2019-11-20 17:14:23', 'img2.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, created, image_name, mime_type)
VALUES (2, '2019-11-20 17:14:27', '2019-11-20 17:14:27', 'a66219df-f83f-427e-ad53-3909cf297281', 40940,
        '2019-11-20 17:14:27', 'img3.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, created, image_name, mime_type)
VALUES (3, '2019-11-20 17:24:51', '2019-11-20 17:24:51', '8157e1b3-98c9-48b3-af57-3c28f0929cc1', 15742,
        '2019-11-20 17:24:51', 'Profilkep-1-1-180x180-0.jpg', 'image/jpeg');

-- Alap felhasználó generálása
insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (4, NOW(), NOW(), '1994-11-22', 'Alap', 'Dr. Alap Elek', 'Miskolc', 06305257026, 'Elek', 'Dr.',
           'alap.elek@kamu.hu', 3, 'asdasd', 'ADMIN', 'alap.elek');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (5, NOW(), NOW(), '1956-04-26', 'Barack', 'Barack Ákos', 'Budapest', 06201598523, 'Ákos', null,
           'barack.akos@kamu.hu', null, 'asdasd', 'CLIENT', 'barack.akos');

-- Alap posztok generálása
insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (6, NOW(), NOW(), 'Ez az első poszt hosszú tartalma. Sok érdekes infóval.', 'Ez az első poszt rövid leírása',
        'Ez az első poszt címe.', 4, 0);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (7, NOW(), NOW(), 'Ez a második poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a második poszt rövid leírása',
        'Ez az első poszt címe.', 4, 1);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (8, NOW(), NOW(), 'Ez a harmadik poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a harmadik poszt rövid leírása',
        'Ez az első poszt címe.', 4, 2);

-- direkt az összes sort frissítem
update hibernate_sequence
set next_val= 9;

commit;
