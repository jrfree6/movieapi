drop table if exists movies;

create table movies (
	id int auto_increment primary key,
	year int, 
	title varchar(250), 
	studios varchar(250), 
	producers varchar(120), 
	winner char(3) default 'no'
);

insert into movies (year, title, studios, producers, winner ) 
values( 2021,'Raya e o Último Dragão','Disney+','Osnat Shurer', 'no' );