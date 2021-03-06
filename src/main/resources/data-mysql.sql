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
           'alap.elek@kamu.hu', 3, '$2a$12$i4V9UYua/OrcqBX8o/jI0.QWDkAn96cfz2aWcXw93GcuT973f.Bx6', 'ADMIN',
           'alap.elek');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (5, CURDATE(), CURDATE(), '1956-04-26', 'Barack', 'Barack Ákos', 'Budapest', 06201598523, 'Ákos', null,
           'barack.akos@kamu.hu', null, '$2a$12$i4V9UYua/OrcqBX8o/jI0.QWDkAn96cfz2aWcXw93GcuT973f.Bx6', 'CLIENT',
           'barack.akos');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (24, CURDATE(), CURDATE(), '1980-05-21', 'Demó', 'Demó Dénes', 'Siófok', 06302589632, 'Dénes', null,
           'demo.denes@kamu.hu', null, '$2a$12$i4V9UYua/OrcqBX8o/jI0.QWDkAn96cfz2aWcXw93GcuT973f.Bx6', 'CLIENT',
           'demo.denes');

insert into user (id, created_date, modified_date, birth_day, first_name, full_name, location, phone_number,
                  secound_name, title, email, image, password, role, username)
    value (25, CURDATE(), CURDATE(), '1950-12-21', 'Minden', 'Minden Áron', 'Győr', 06805284682, 'Áron', null,
           'minden.aron@kamu.hu', null, '$2a$12$i4V9UYua/OrcqBX8o/jI0.QWDkAn96cfz2aWcXw93GcuT973f.Bx6', 'ADMIN',
           'minden.aron');


insert into post (id, created_date, modified_date, content_text, introduction, title, author, image, last_modified_by)
values (-1, CURDATE(), CURDATE(),
        '<p>
            Pellentesque volutpat ipsum diam, at consequat neque imperdiet in. Etiam pharetra lorem in augue sollicitudin facilisis. Vivamus dignissim, augue luctus eleifend dictum, mi felis accumsan neque, vel malesuada odio lectus eget odio. Nam sed condimentum magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sollicitudin convallis cursus. Ut semper velit mauris, ut faucibus ipsum bibendum non. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce maximus, felis et rutrum bibendum, lectus nisl posuere ante, quis facilisis lorem arcu nec lorem. Etiam sit amet vehicula dui. Aenean fermentum ullamcorper augue. Cras ut sem lobortis mauris hendrerit blandit sed vitae libero. Donec viverra elementum dui. Nam euismod, lacus in lobortis molestie, nulla urna facilisis ipsum, id efficitur magna odio nec purus. Suspendisse potenti.
            </p>
            <p>
            Maecenas ornare ipsum ac pharetra gravida. In viverra ligula vel massa pellentesque laoreet. Nulla eu scelerisque mauris. Pellentesque ut mi ut turpis consequat aliquet. Proin vitae sem sem. Donec eu mauris fermentum, rutrum justo nec, pellentesque sem. Nulla eleifend, turpis non sollicitudin convallis, turpis diam posuere eros, vel ullamcorper urna mi id nisi. Proin vel tellus a neque sollicitudin dignissim. Suspendisse fermentum ex vitae odio faucibus, at tincidunt leo feugiat. Nam sed lacus eu nunc gravida faucibus sed non urna.
            </p>',
        concat(
                'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum facilisis tortor pretium dui cursus imperdiet.'
            ,
                ' Sed nec est vitae orci aliquam faucibus. Aliquam erat volutpat. Nullam facilisis, felis eu commodo semper,'
            ,
                ' mi leo sollicitudin est, vitae tempor turpis ante at lorem. Suspendisse eget lectus ac nisi placerat tempor sit amet et lorem.'
            ,
                ' Donec efficitur, dolor ut ultricies pellentesque, mi orci pulvinar felis, eu finibus lectus mauris a ex.'
            ,
                ' Quisque dapibus lacus a sapien bibendum, lacinia placerat ante convallis. Pellentesque tempor mauris porttitor sollicitudin ornare.'
            ,
                ' Maecenas sit amet elit pharetra, luctus nisl sit amet, congue dui. Nam eros neque, sagittis non neque eget, faucibus vestibulum erat.'),
        'ME-FDSZ', 4, null, 4);


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
insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (22, CURDATE(), CURDATE(), 10000, 1, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (23, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (24, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (25, CURDATE(), CURDATE(), 10000, 4, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (26, CURDATE(), CURDATE(), 5000, 5, 'THREE_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (27, CURDATE(), CURDATE(), 5000, 6, 'THREE_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (28, CURDATE(), CURDATE(), 5000, 7, 'THREE_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (29, CURDATE(), CURDATE(), 5000, 8, 'THREE_BED', 'Y');

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

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (33, CURDATE(), CURDATE(), 10000, 1, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (34, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (35, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED', 'Y');

insert into turnus_room (room_id, turnus_id) value (33, 31);
insert into turnus_room (room_id, turnus_id) value (34, 31);
insert into turnus_room (room_id, turnus_id) value (35, 31);

-- Régi lejárt turnus létrehozása
insert into turnus (id, created_date, modified_date, enabled, end_date, start_date, last_modified_by, author)
    value (32, '2019-09-01', '2019-09-01', 'Y', '2019-09-09', '2019-09-13', 4, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (36, CURDATE(), CURDATE(), 10000, 2, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (37, CURDATE(), CURDATE(), 10000, 3, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (38, CURDATE(), CURDATE(), 10000, 4, 'FOUR_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (39, CURDATE(), CURDATE(), 5000, 5, 'THREE_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (40, CURDATE(), CURDATE(), 5000, 6, 'THREE_BED', 'Y');

insert into room (id, created_date, modified_date, price, room_number, room_type, available)
    value (41, CURDATE(), CURDATE(), 5000, 7, 'THREE_BED', 'Y');

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
set next_val= 50;

commit;
