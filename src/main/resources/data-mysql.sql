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

-- Háromágyasok
insert into room (id, created_date, modified_date, price, room_number, room_type)
values (13, CURDATE(), CURDATE(), 3000, 0, 3);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (14, CURDATE(), CURDATE(), 3000, 0, 3);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (15, CURDATE(), CURDATE(), 3000, 0, 3);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (16, CURDATE(), CURDATE(), 3000, 0, 3);

-- Négyágyasok
insert into room (id, created_date, modified_date, price, room_number, room_type)
values (17, CURDATE(), CURDATE(), 5000, 0, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (18, CURDATE(), CURDATE(), 5000, 0, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (19, CURDATE(), CURDATE(), 5000, 0, 4);

insert into room (id, created_date, modified_date, price, room_number, room_type)
values (20, CURDATE(), CURDATE(), 5000, 0, 4);

-- Teszt üzenetek feltöltése
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (21, CURDATE() - 5, CURDATE(), 'Ez az első üzenet amit a felhasználó írt az adminnak.', 5, 4, 'N');
-- ez egy user -> admin üzenet

-- ez egy admin --> user üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (22, CURDATE() - 3, CURDATE(), 'Ez egy válasz az admintól.', 4, 5, 'N');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (23, CURDATE() - 2, CURDATE(), 'Ez a második üzenet amit a felhasználó írt az adminnak', 5, 4, 'N');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (26, CURDATE() - 8, CURDATE(), 'Demóka régi üzcsije', 24, 25, 'Y');

-- ez egy user -> admin üzenet
insert into message (id, created_date, modified_date, message_content, sender, reciever, readed)
    value (27, CURDATE() - 1, CURDATE(), 'Demóka új üzcsije', 24, 25, 'N');

-- Hibernate seq frissítése, hogy tudomást szerezzen a test adatok insertálásáról
-- direkt az összes sort frissítem
update hibernate_sequence
set next_val= 28;

commit;
