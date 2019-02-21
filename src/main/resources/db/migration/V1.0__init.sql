create table wasdapp_entry (
    id int8 primary key not null,
    name varchar(255),
    locatie varchar(64),
    straat varchar(96),
    nummer varchar(10),
    post_Code varchar(6),
    gemeente varchar(64),
    land varchar(64),
    omschrijving varchar(2048),
    wiki_Link varchar(64),
    website varchar(64),
    telefoon_Nummer varchar(64),
    email varchar(64),
    prijs double(64),
    persoon varchar(64),
    aanmaak_Datum timestamp,
    wijzig_Datum timestamp,
    lat double precision,
    lon double precision
);

insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (1,'Kevin','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (2,'Larry','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (3,'Joren','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (4,'Jasper','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (5,'Kobe','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (6,'Shmerik','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(id,name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values (7,'Shelter','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);

