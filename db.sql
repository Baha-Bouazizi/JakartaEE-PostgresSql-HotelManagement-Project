create table hotel
(
    id          serial
        primary key,
    name        varchar(128) not null,
    city        varchar(64)  not null,
    stars       integer
        constraint hotel_stars_check
            check ((stars >= 0) AND (stars <= 5)),
    description varchar(512),
    image       varchar(512)
);

alter table hotel
    owner to abdallah;

create table roomtype
(
    id       serial
        primary key,
    label    varchar(128) not null
        unique,
    capacity integer      not null
        constraint roomtype_capacity_check
            check (capacity > 0)
);

alter table roomtype
    owner to abdallah;

create table account
(
    id       serial
        primary key,
    username varchar(128) not null
        unique,
    password varchar(128) not null,
    email    varchar(128) not null
        unique,
    role     varchar(32)  not null
        constraint account_role_check
            check ((role)::text = ANY
                   ((ARRAY ['admin'::character varying, 'agent'::character varying, 'client'::character varying])::text[]))
);

alter table account
    owner to abdallah;

create table hotelroomtype
(
    hotel_id    integer        not null
        constraint fk_hotel
            references hotel
            on delete cascade,
    roomtype_id integer        not null
        constraint fk_roomtype
            references roomtype
            on delete cascade,
    price       numeric(10, 2) not null,
    quantity    integer        not null,
    primary key (hotel_id, roomtype_id)
);

alter table hotelroomtype
    owner to abdallah;

create table reservation
(
    id           serial
        primary key,
    user_id      integer                                          not null
        references account,
    hotel_id     integer                                          not null
        references hotel,
    room_type_id integer                                          not null
        references roomtype,
    status       varchar(32) default 'pending'::character varying not null,
    period       integer                                          not null
);

alter table reservation
    owner to abdallah;

