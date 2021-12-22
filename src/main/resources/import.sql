insert into person (last_name, first_name) values ('Novak', 'Lilla');
insert into person (last_name, first_name) values ('Acs', 'Aron');
insert into person (last_name, first_name) values ('Balogh', 'Balazs');
insert into person (last_name, first_name) values ('Czifra', 'Cecilia');
insert into person (last_name, first_name) values ('Demeter', 'David');

insert into user (username, password, role) values ('admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_ADMIN');
insert into user (username, password, role) values ('Novak Lilla', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_ADMIN');
insert into user (username, password, role) values ('user', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_USER');
insert into user (username, password, role) values ('Acs Aron', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_USER');
insert into user (username, password, role) values ('Balogh Balazs', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_USER');
insert into user (username, password, role) values ('Czifra Cecilia', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_USER');
insert into user (username, password, role) values ('Demeter David', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_USER');

insert into bpluss_tree (create_date,tree_values) values ('2021-09-15','i45,i89,i9,i385,i12,i1,i3,d12');
insert into bpluss_tree (create_date,tree_values) values ('2021-09-15','i2,i5,i24,i56,i44,i3,i15,i39');
insert into bpluss_tree (create_date,tree_values) values ('2021-09-15','i23,i6,i64,i57,i61,d23,i15,i329');
insert into bpluss_tree (create_date,tree_values) values ('2021-09-16','i1,i5,i10,i15,i20,i25,i35,i45');

insert into BPLUSS_TREE_PERSONS (BPLUSS_TREES_ID, PERSONS_ID) values (1,3);
insert into BPLUSS_TREE_PERSONS (BPLUSS_TREES_ID, PERSONS_ID) values (2,3);
insert into BPLUSS_TREE_PERSONS (BPLUSS_TREES_ID, PERSONS_ID) values (3,3);
insert into BPLUSS_TREE_PERSONS (BPLUSS_TREES_ID, PERSONS_ID) values (4,3);