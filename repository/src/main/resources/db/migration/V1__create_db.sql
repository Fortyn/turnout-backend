CREATE SCHEMA turnout;

CREATE TABLE turnout.users (
    id integer primary key generated always as identity (start with 0, increment by 1),
    first_name varchar(60) not null,
    middle_name varchar(60),
    last_name varchar(60) not null
);
CREATE TABLE turnout.credentials (
    id integer primary key,
    username varchar(60) unique,
    hash varchar(60),
    constraint user_credentials foreign key (id) references turnout.users(id)
                   on delete cascade
                   on update no action
);
CREATE TABLE turnout.points (
    id integer primary key generated always as identity (start with 1, increment by 1),
    abscissa integer not null,
    ordinate integer not null
);
create table turnout.rooms (
    id                 integer primary key generated always as identity (start with 1, increment by 1),
    lower_left_corner  integer not null,
    upper_right_corner integer not null,
    identifier         varchar(10) not null,
    constraint llc foreign key (lower_left_corner) references turnout.points (id)
        on delete no action
        on update no action,
    constraint urc foreign key (upper_right_corner) references turnout.points (id)
        on delete no action
        on update no action
);
create table turnout.directions (
  id integer primary key ,
  name varchar(20) not null
);
create table turnout.walls (
    id integer primary key generated always as identity (start with 1, increment by 1),
    room_id integer not null,
    direction integer not null,
    constraint room_fk foreign key (room_id) references turnout.rooms(id)
                   on delete cascade
                   on update no action,
    constraint direction_fk foreign key (direction) references turnout.directions(id)
                   on delete no action
                   on update no action
);
create table turnout.doors (
    id integer primary key generated always as identity (start with 1, increment by 1),
    wall_id integer not null,
    position integer not null,
    constraint wall_fk foreign key (wall_id) references turnout.walls(id)
                   on delete cascade
                   on update no action,
    constraint position_fk foreign key (position) references turnout.points(id)
                   on delete no action
                   on update no action
);
create table turnout.anchors (
    id integer primary key generated always as identity (start with 1, increment by 1),
    server_name varchar(255) not null,
    service_name varchar(255) not null,
    property_name varchar(255) not null,
    property_value varchar(255) not null
);
create table turnout.rooms_anchors (
    room_id integer not null,
    anchor_id integer not null,
    constraint rooms_anchors_room_fk foreign key (room_id) references turnout.rooms(id)
                   on delete cascade
                   on update no action,
    constraint rooms_anchors_anchor_fk foreign key (anchor_id) references turnout.anchors(id)
                   on delete cascade
                   on update no action
);
create table turnout.turnout (
    id integer primary key generated always as identity (start with 1, increment by 1),
    user_id integer not null,
    room_id integer not null,
    datetime timestamp not null,
    constraint turnout_user_fk foreign key (user_id) references turnout.users(id)
                   on delete cascade
                   on update no action,
    constraint turnout_room_fk foreign key (room_id) references turnout.rooms(id)
                   on delete cascade
                   on update no action
);