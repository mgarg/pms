--DROP table rateplan;
--DROP table roomtype;
--DROP table property;
--DROP table country;
--DROP table state;
--DROP table city;
--DROP table chain;

create table country(id int primary key,name char(2));

    insert into country VALUES(1,'IN');
    insert into country VALUES(2,'UK');
    insert into country VALUES(3,'US');
    insert into country VALUES(4,'EN');
    insert into country VALUES(5,'SA');

create table state(
id int primary key,
code char(2),
statename varchar(256),
countryid int,
FOREIGN key (countryid) REFERENCES country(id)
);

insert into state VALUES(1,'DE','DELHI',1);
insert into state VALUES(2,'MU','MUMBAI',1);


create table city(
  id int primary key,
  cityname varchar(25),
  stateid int,
  countryid int,
  FOREIGN key (stateid) REFERENCES state(id),
  FOREIGN key (countryid) REFERENCES country(id)
);

insert into city VALUES(1,'new delhi',1,1);

create table chain(
  id int primary key,
  code varchar(3),
  chainname varchar(256)
);

insert into chain VALUES(1,'mr1','marriot');
insert into chain VALUES(2,'taj','taj');
insert into chain VALUES(3,'wyn','wyndham');


create table property(
  id int primary key,
  name varchar(256),
  address varchar(512),
  chainid int,
  cityid int,
  zipcode int,
  latitude int,
  longitude int,
  FOREIGN key (chainid) REFERENCES chain(id),
  FOREIGN key (cityid) REFERENCES city(id)
);

insert into property VALUES(111111,'abc','address1',1,1,'110019',10,20);
insert into property VALUES(111112,'bbc','address2',1,1,'110019',10,20);
insert into property VALUES(111113,'cbc','address3',1,1,'110019',10,20);
insert into property VALUES(111114,'dbc','address4',1,1,'110019',10,20);
insert into property VALUES(111115,'ebc','address5',1,1,'110019',10,20);
insert into property VALUES(111116,'fbc','address6',1,1,'110019',10,20);
insert into property VALUES(111117,'gbc','address7',1,1,'110019',10,20);
insert into property VALUES(111118,'hbc','address8',1,1,'110019',10,20);
insert into property VALUES(111119,'ibc','address9',1,1,'110019',10,20);
insert into property VALUES(111120,'jbc','address10',1,1,'110019',10,20);
insert into property VALUES(111121,'kbc','address11',1,1,'110019',10,20);
insert into property VALUES(111122,'lbc','address12',1,1,'110019',10,20);
insert into property VALUES(111123,'mbc','address13',1,1,'110019',10,20);
insert into property VALUES(111124,'nbc','address14',1,1,'110019',10,20);
insert into property VALUES(111125,'obc','address15',1,1,'110019',10,20);
insert into property VALUES(111126,'pbc','address16',1,1,'110019',10,20);
insert into property VALUES(111127,'qbc','address17',1,1,'110019',10,20);
insert into property VALUES(111128,'rbc','address18',1,1,'110019',10,20);
insert into property VALUES(111129,'sbc','address19',1,1,'110019',10,20);
insert into property VALUES(111130,'tbc','address20',1,1,'110019',10,20);

create table roomtype(
  id int primary key,
  code varchar(256),
  name varchar(256)
);

insert into roomtype VALUES(1,'r1','room1');
insert into roomtype VALUES(2,'r2','room2');

create table rateplan(
    id int primary key,
    pid int,
    rid1 int,
    rp1startdate date,
    rp1enddate date,
    rp1single DECIMAL,
    rp1double DECIMAL,
    rid2 int,
    rp2startdate date,
    rp2enddate date,
    rp2single DECIMAL,
    rp2double DECIMAL,
    FOREIGN key (pid) REFERENCES property(id),
    FOREIGN key (rid1) REFERENCES roomtype(id),
    FOREIGN key (rid2) REFERENCES roomtype(id)
);


insert into rateplan VALUES(
    10,
    111111, 1, '1-march-2016','4-march-2016',100,200,2,'1-march-2016','4-march-2016',100,200);

COMMIT;