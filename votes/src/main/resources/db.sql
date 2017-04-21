create table subject(
	id int primary key auto_increment,
	title varchar(100),
	type int ,
	version int,
	userid varchar(100)
)
create table options(
	id int primary key auto_increment,
	orders int,
	name varchar(100),
	subjectid int
)
create table user(
	userid varchar(100) primary key,
	username varchar(100),
	password varchar(100),
	version int,
	status int
)
create table item(
	id int primary key auto_increment,
	subjectid int,
	optionid int,
	userid varchar(100)
)





