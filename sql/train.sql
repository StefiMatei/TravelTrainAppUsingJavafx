use train;



create table if not exists employee (
employeeId integer not null auto_increment, 
userId integer not null, 
name varchar(70) not null,
email varchar(70) not null, 
phone varchar(70) not null, 
primary key(employeeId), 
key userId(userId));

create table if not exists Role (
roleId integer not null auto_increment,
label varchar(60) not null,
primary key(roleId));

create table if not exists User (
userId integer not null auto_increment,
username varchar(60) not null,
password varchar(60) not null,
roleId integer not null,
primary key(userId),
key roleId (roleId));

create table if not exists Train (
trainId integer not null auto_increment, 
number integer not null,
dateOfDeparture date not null, 
dateOfArrival date not null, 
leavesFrom varchar(60) not null, 
goesTo varchar(70) not null, 
primary key(trainId));



create table if not exists Ticket (
ticketId integer not null auto_increment, 
trainId integer not null,
price double not null, 
quantity integer not null, 
placeNumber integer not null, 
primary key(ticketId),
key trainId(trainId));

create table if not exists Activity (
activityId integer not null auto_increment,
employeeId integer not null,
dateOfEnterApp date not null,
dateOfExitApp date not null,
primary key(activityId),
key employeeId(employeeId));

insert into Employee values
(10, 4, "Boanta", "boanta@yahoo.com", "07456368728");
insert into Employee values
(12, 5, "Boanta4", "boanta@yahoo.com", "07456368728");
insert into Employee values
(13, 10, "Boanta5", "boanta@yahoo.com", "07456368728");
insert into Employee values
(14, 11, "Boanta6", "boanta@yahoo.com", "07456368728");


insert into Role values(
1, "admin");
insert into Role values(
2, "employee");

insert into User values
(1, "admin", "admin", 1);
insert into User values
(2, "angajat1", "angajat1", 2);
insert into User values
(4, "angajat3", "angajat3'", 2);
insert into User values
(5, "angajat4", "angajat4", 2);
insert into User values
(10, "stefi", "stefi", 2);
insert into User values
(11, "cipri", "cipri", 2);