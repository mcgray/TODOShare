insert into todoshare_account (id, nickname, first_name, last_name) values (2, 'orezchykov', 'Olesksiy', 'Rezchykov');
insert into todoshare_account (id, nickname, first_name, last_name) values (1, 'mcgray', 'Alex', 'McGray');

insert into user (id, email_address, password, todoshare_account_id, active) values (1, 'oleksiy.rezchykov@gmail.com', '$2a$10$6.p2FiKeFjJMLpGV3VhDquo1TnN0nOVGklYeUbWV1J/qHkJs3qFrS', 2, 1);
insert into user (id, email_address, password, todoshare_account_id, active) values (2, 'alex.mcgray@gmail.com', '$2a$10$CMfHaHsCOvXgnCkPM4gntu8IKBA3hkvaJjXYdfgZzqBoZJEh4jWw2', 1  , 1);

insert into user_authorities (user, authorities) values (1, 'ROLE_ADMIN');
insert into user_authorities (user, authorities) values (2, 'ROLE_USER');

insert into todo_list (id, title, todoshare_account_id) values (1, 'Admin personal list', 1);
insert into todo_list (id, title, todoshare_account_id) values (2, 'Admin shared list', 1);
insert into todo_list (id, title, todoshare_account_id) values (3, 'User personal list', 2);

insert into list_members (list_id, todoshare_account_id) values (2, 2);

insert into todo (id, todoshare_account_id, todolist_id, title, done) values (1, 1, 1, 'Admin personal ToDo', 0);
insert into todo (id, todoshare_account_id, todolist_id, title, done) values (2, 1, 2, 'Admin shared ToDo', 0);
insert into todo (id, todoshare_account_id, todolist_id, title, done) values (3, 2, 3, 'User personal ToDo', 0);