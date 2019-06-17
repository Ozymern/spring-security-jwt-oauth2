

INSERT INTO user (user_id,email,username, password, enabled) values(1,'admin@gmail.com','admin','$2a$10$slGjVyFcrf3FFnq86ucvjeebWkSg.Tj/bEk/vmhcsIeSqSp9yPWqa',1);

INSERT INTO user (user_id,email,username, password, enabled) values(2,'user@gmail.com','user','$2a$10$zk1H50FcurUnyyxlIacnOu0OC66lxRB/W1PCOGcclVcwASnrJQPFa',1);



INSERT INTO role (role_id,role) values (1,'ADMIN');
INSERT INTO role (role_id,role) values (2,'USER');

INSERT INTO user_role (user_id,role_id) values (1,1);
INSERT INTO user_role (user_id,role_id) values (2,2);
INSERT INTO user_role (user_id,role_id) values (1,2);

INSERT INTO pet (pet_id,name) values (1,'yodita');
INSERT INTO pet (pet_id,name) values (2,'donny');
INSERT INTO pet (pet_id,name) values (3,'asesina');
