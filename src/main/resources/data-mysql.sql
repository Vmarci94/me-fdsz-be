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

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (24, CURDATE(), CURDATE(), '1980-05-21', 'Demó', 'Demó Dénes', 'Siófok', 06302589632, 'Dénes', null,
           'demo.denes@kamu.hu', null, 'asdasd', 'CLIENT', 'demo.denes');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (25, CURDATE(), CURDATE(), '1950-12-21', 'Minden', 'Minden Áron', 'Győr', 06805284682, 'Áron', null,
           'minden.aron@kamu.hu', null, 'asdasd', 'ADMIN', 'minden.aron');


-- Alap posztok generálása
insert into post (id, created_date, modified_date, content_text, introduction, title, author, image, last_modified_by)
values (6, CURDATE(), CURDATE(), 'Ez az első poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez az első poszt rövid leírása',
        'Ez az első poszt címe.', 4, 0, 4);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image, last_modified_by)
values (7, CURDATE(), CURDATE(), 'Ez a második poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a második poszt rövid leírása',
        'Ez a második poszt címe.', 4, 1, 4);

insert into post (id, created_date, modified_date, content_text, introduction, title, author, image, last_modified_by)
values (8, CURDATE(), CURDATE(), 'Ez a harmadik poszt hosszú tartalma. Sok érdekes infóval.',
        'Ez a harmadik poszt rövid leírása',
        'Ez a harmadik poszt címe.', 4, 2, 4);

-- Alap felhasználói visszajelzések generálása
insert into user_report (id, created_date, modified_date, report)
values (9, CURDATE(), CURDATE(), 'Ez az első véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (10, CURDATE(), CURDATE(), 'Ez az mádosik véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (11, CURDATE(), CURDATE(), 'Ez az harmadik véleményem, és király');

insert into user_report (id, created_date, modified_date, report)
values (12, CURDATE(), CURDATE(), 'Ez most egy másik felhasználó véleménye');

-- Szobák felvétele TODO: (ez egyébként nem árt ha majd mindig incializálódik)

-- Teszt üzenetek feltöltése
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (17, CURDATE() - INTERVAL 5 DAY, CURDATE(), 'Ez az első üzenet amit a felhasználó írt az adminnak.', 5, 4,
           'N');
-- ez egy user -> admin üzenet

-- ez egy admin --> user üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (18, CURDATE() - INTERVAL 3 DAY, CURDATE(), 'Ez egy válasz az admintól.', 4, 5, 'N');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (19, CURDATE() - INTERVAL 2 DAY, CURDATE(), 'Ez a második üzenet amit a felhasználó írt az adminnak', 5, 4,
           'N');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (20, CURDATE() - INTERVAL 8 DAY, CURDATE(), 'Demóka régi üzcsije', 24, 25, 'Y');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (21, CURDATE() - INTERVAL 1 DAY, CURDATE(), 'Demóka új üzcsije', 24, 25, 'N');

-- Teszt szobák feltöltése
insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (22, CURDATE(), CURDATE(), 10000, 1, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (23, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (24, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (25, CURDATE(), CURDATE(), 10000, 4, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (26, CURDATE(), CURDATE(), 5000, 5, 'THREE_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (27, CURDATE(), CURDATE(), 5000, 6, 'THREE_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (28, CURDATE(), CURDATE(), 5000, 7, 'THREE_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (29, CURDATE(), CURDATE(), 5000, 8, 'THREE_BED');

-- Teszt turnus létrehozása
insert into turnus (id, created_date, modified_date, enabled, end_date, start_date, last_modified_by, author)
    value (30, CURDATE(), CURDATE(), 'Y', '2020-2-10', '2020-2-14', 4, 4);

insert into turnus_room (room_id, turnus_id) value (22, 30);
insert into turnus_room (room_id, turnus_id) value (23, 30);
insert into turnus_room (room_id, turnus_id) value (24, 30);
insert into turnus_room (room_id, turnus_id) value (25, 30);
insert into turnus_room (room_id, turnus_id) value (26, 30);
insert into turnus_room (room_id, turnus_id) value (27, 30);
insert into turnus_room (room_id, turnus_id) value (28, 30);
insert into turnus_room (room_id, turnus_id) value (29, 30);

insert into turnus (id, created_date, modified_date, enabled, end_date, start_date, last_modified_by, author)
    value (31, CURDATE(), CURDATE(), 'N', '2020-03-16', '2020-03-20', 4, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (33, CURDATE(), CURDATE(), 10000, 1, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (34, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (35, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED');

insert into turnus_room (room_id, turnus_id) value (33, 31);
insert into turnus_room (room_id, turnus_id) value (34, 31);
insert into turnus_room (room_id, turnus_id) value (35, 31);

-- Régi lejárt turnus létrehozása
insert into turnus (id, created_date, modified_date, enabled, end_date, start_date, last_modified_by, author)
    value (32, '2019-09-01', '2019-09-01', 'Y', '2019-09-09', '2019-09-13', 4, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (36, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (37, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (38, CURDATE(), CURDATE(), 10000, 4, 'FOUR_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (39, CURDATE(), CURDATE(), 5000, 5, 'THREE_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (40, CURDATE(), CURDATE(), 5000, 6, 'THREE_BED');

insert into room (id, created_date, modified_date, price, room_number, room_type)
    value (41, CURDATE(), CURDATE(), 5000, 7, 'THREE_BED');

insert into turnus_room (room_id, turnus_id) value (36, 32);
insert into turnus_room (room_id, turnus_id) value (37, 32);
insert into turnus_room (room_id, turnus_id) value (38, 32);
insert into turnus_room (room_id, turnus_id) value (39, 32);
insert into turnus_room (room_id, turnus_id) value (40, 32);
insert into turnus_room (room_id, turnus_id) value (41, 32);

INSERT INTO ME_FDSZ.message (id, created_date, modified_date, message_content, readed, reciever, sender)
VALUES (28, '2019-11-30', '2019-11-30', 'Egy másik admin válasza', 'N', 5, 25);

-- Teszt Foglalások készítése
INSERT INTO ME_FDSZ.booking (id, created_date, modified_date, booking_date, number_of_nights, author, last_modified_by)
VALUES (42, '2019-12-01', '2019-12-01', '2020-02-14', 4, 4, 4);
INSERT INTO ME_FDSZ.booking (id, created_date, modified_date, booking_date, number_of_nights, author, last_modified_by)
VALUES (43, '2019-12-01', '2019-12-01', '2019-09-13', 4, 4, 4);

UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 5000,
    room_number   = 6,
    room_type     = 'THREE_BED',
    booking_id    = 43
WHERE id = 40;
UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 5000,
    room_number   = 5,
    room_type     = 'THREE_BED',
    booking_id    = 43
WHERE id = 39;
UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 10000,
    room_number   = 3,
    room_type     = 'FOUR_BED',
    booking_id    = 43
WHERE id = 37;
UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 10000,
    room_number   = 2,
    room_type     = 'FOUR_BED',
    booking_id    = 42
WHERE id = 23;
UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 10000,
    room_number   = 3,
    room_type     = 'FOUR_BED',
    booking_id    = 42
WHERE id = 24;
UPDATE ME_FDSZ.room
SET created_date  = '2019-12-01',
    modified_date = '2019-12-01',
    price         = 5000,
    room_number   = 5,
    room_type     = 'THREE_BED',
    booking_id    = 42
WHERE id = 26;

-- Hibernate seq frissítése, hogy tudomást szerezzen a test adatok insertálásáról
-- direkt az összes sort frissítem
update hibernate_sequence
set next_val= 44;

commit;
