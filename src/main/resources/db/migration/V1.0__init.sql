create table wasdapp_entry (
    id int8 primary key not null,
    name varchar(255) not null,
    description varchar(2048),
    street varchar(96),
    number varchar(10),
    city varchar(86),
    lat double precision,
    lon double precision
);
