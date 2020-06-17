insert into turnout.directions (id, name)
    values (0, 'FRONT'),
           (1, 'LEFT'),
           (2, 'BACK'),
           (3, 'RIGHT');

insert into turnout.users (FIRST_NAME, LAST_NAME)
    values ('Администратор', 'системы');

insert into turnout.credentials (ID, USERNAME, HASH)
    values (0, 'admin', '$2a$10$.6dBynLYtRVhNse1yKIAqeCaKHHcrsrgRS4FKSrpz5vRsIHW6I3V.');