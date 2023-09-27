INSERT INTO days (id, date, name)
VALUES (1, '2024-07-01','FRIDAY'),
       (2, '2024-07-02', 'SATURDAY');

INSERT INTO artists (id, name, date, bio, day_id)
VALUES (1, 'Bob Marley','2024-07-01','Regi',1),
       (2, 'Edda', '2024-07-02', 'urleny',2);

INSERT INTO organizers (id, email, name, password)
VALUES (1,'leo@gmail.hu','Leo','$2a$10$O7IEvhwRfIRE6dvB8FHH4.vvKpKDhLasn6a20ekzg0dbS.j3iu/tq'),
       (2, 'kristof@gmail.hu', 'Kristof','$2a$10$SaPeFYZJS8QzSye/ySdf2uRIoFVwagRckQv2PeMnnC7xvmrH3x0Ym');


