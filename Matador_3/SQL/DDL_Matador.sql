-- Make sure the DB exists
CREATE DATABASE if not EXISTS MatadorDB;
-- Reset the tables


drop table if exists player_properties;
drop table if exists bank;
drop table if exists jail;
drop table if exists players;
drop table if exists games;
drop view if exists game;
drop procedure if exists CreatePlayer;


create table games(
	game_id 			int AUTO_INCREMENT not null,
    game_name			varchar(30),
    last_saved_time		timestamp,
    current_player		int(1) check (current_player <= 6),
    cards				binary(30),
    primary key(game_id)
);

create table players(
	player_id 	int(1) check (player_id > 0 && player_id <=6),
    game_id 	int not null,
    player_name	varchar(20),
    account 	int(7),
    jailcards 	int(1),
	color		varchar(10),
    position	int(2) check(position <= 40 && position > 0),
    primary key(player_id, game_id),
    foreign key (game_id) references games(game_id )
);

create table player_properties(
	field_id	int(2) check (field_id > 0 && field_id <= 40),
    game_id		int,
    player_id	int,
    num_houses	int(1) check (num_houses <= 4 && num_houses >= 0),
    num_hotels	int(1) check (num_hotels = 0 || num_hotels = 1),
    field_type	varchar(10),
    primary key (field_id, game_id),
	foreign key (player_id) references players(player_id),
    foreign key (game_id) references games(game_id )

);

create table bank(
	game_id			int,
	houses_left		int(2) check(houses_left >= 0),
    hotels_left		int(2) check(hotels_left >= 0),
    primary key(game_id),
    foreign key(game_id) references games(game_id )
);

create table jail(
	player_id		int,
    game_id			int,
    num_doubles		int(1) check(num_doubles <= 3),
    jail_time		int(1) check(jail_time <= 3),
    isInJail		boolean,
    primary key (player_id, game_id),
    foreign key (player_id) references players(player_id),
    foreign key (game_id) references games(game_id )
);

create view game as (
	select * from 
		games
        join players using(game_id)
        left outer join player_properties using(game_id, player_id)
        left outer join bank using(game_id)
        left outer join jail using(game_id, player_id)
);

delimiter //
create procedure CreatePlayer(
	IN player_id int(1),
    IN game_id int,
    IN player_name varchar(20),
    IN account int(7),
    IN jailcards 	int(1),
	IN color		varchar(10),
	IN position	int(2)
)
BEGIN
	insert into players values (player_id, game_id, player_name, account, jailcards, color, position);
END//
delimiter ;

