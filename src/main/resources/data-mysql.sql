-- Képek inicializálása
INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, image_name, mime_type)
VALUES (0, CURDATE(), CURDATE(), 'test_img0', 55810, 'img1.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, image_name, mime_type)
VALUES (1, CURDATE(), CURDATE(), 'test_img1', 31132, 'img2.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, image_name, mime_type)
VALUES (2, CURDATE(), CURDATE(), 'test_img2', 40940, 'img3.jpg', 'image/jpeg');

INSERT INTO ME_FDSZ.image (id, created_date, modified_date, content_id, content_length, image_name, mime_type)
VALUES (3, CURDATE(), CURDATE(), 'test_profile_img0', 15742, 'Profilkep-1-1-180x180-0.jpg', 'image/jpeg');

-- Alap felhasználó generálása
insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (4, CURDATE(), CURDATE(), '1994-11-22', 'Alap', 'Dr. Alap Elek', 'Miskolc', 06305257026, 'Elek', 'Dr.',
           'alap.elek@kamu.hu', 3, 'asdasd', 'ADMIN', 'alap.elek');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (5, CURDATE(), CURDATE(), '1956-04-26', 'Barack', 'Barack Ákos', 'Budapest', 06201598523, 'Ákos', null,
           'barack.akos@kamu.hu', null, 'asdasd', 'CLIENT', 'barack.akos');

-- Alap posztok generálása
insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (6, CURDATE(), CURDATE(), 'Ez az első poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez az első poszt rövid leírása',
        'Ez az első poszt címe.', 4, 0);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (7, CURDATE(), CURDATE(), 'Ez a második poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a második poszt rövid leírása',
        'Ez az első poszt címe.', 4, 1);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image)
values (8, CURDATE(), CURDATE(), 'Ez a harmadik poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a harmadik poszt rövid leírása',
        'Ez az első poszt címe.', 4, 2);

-- Alap felhasználói visszajelzések generálása
insert into user_report (id, created_date, modified_date, report)
values (9, CURDATE(), CURDATE(), 'Ez az első véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (10, CURDATE(), CURDATE(), 'Ez az mádosik véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (11, CURDATE(), CURDATE(), 'Ez az harmadik véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (12, CURDATE(), CURDATE(), 'Ez most egy másik felhasználó véleménye');

-- Hibernate seq frissítése, hogy tudomást szerezzen a test adatok insertálásáról
-- direkt az összes sort frissítem
update hibernate_sequence
set next_val= 13;

commit;
