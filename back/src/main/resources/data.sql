TRUNCATE TABLE user_role;
delete from application;
delete from contact;
delete from company;


delete from role where 1=1;
delete from user where 1=1;
INSERT INTO role VALUES (1,'ADMIN');
INSERT INTO user (user_id, active, email, password) values (1, 1,'email@emm.com', '$2a$10$yFyUJQLITr5V3hfZk2re8ONdEJlP8YG3OmYl4HOSwSbMzg3d.PRIO');
INSERT INTO user_role VALUES (1, 1);

insert into address(address, city, postal_code) values ('123 rue des camomilles', 'Toulouse', '124563');
insert into company(email, name, phone, website, address_address_id) values ('company@email.com', 'DAVID COMPANY', '0123456789', 'www.davidscompany.fr', (select max(address_id) from address));
insert into address(address, city, postal_code) values ('12 rues des jonquilles', 'Mérignac', '33700');
insert into company(email, name, phone, website, address_address_id) values ('contact@company2.com', 'OTHER', NULL, 'company2.com', (select max(address_id) from address));

