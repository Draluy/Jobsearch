INSERT INTO role VALUES (1,'ADMIN');
INSERT INTO user (active, email, password) values (1,'email@emm.com', '$2a$10$yFyUJQLITr5V3hfZk2re8ONdEJlP8YG3OmYl4HOSwSbMzg3d.PRIO');
INSERT INTO user_role VALUES (1, 1);
insert into address(address, city, postal_code) values ('123 rue des camomilles', 'Toulouse', '124563');
insert into company(email, name, phone, website, address_address_id) values ('company@email.com', 'DAVID COMPANY', '0123456789', 'www.davidscompany.fr', 1);

