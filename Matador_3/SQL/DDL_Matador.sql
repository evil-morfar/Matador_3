-- Make sure the DB exists
CREATE DATABASE if not EXISTS MatadorDB;
-- Reset the tables


drop table if exists player_properties;
drop table if exists bank;
drop table if exists jail;
drop table if exists players;
drop table if exists games;
drop view if exists fullplayer;
drop procedure if exists CreatePlayer;
drop procedure if exists CreateGame;		
drop procedure if exists SavePlayer;
drop procedure if exists SaveField;
drop procedure if exists SaveGame;


create table games(
	game_id 			int AUTO_INCREMENT not null,
    game_name			varchar(30),
    last_saved_time		timestamp,
    current_player		int(1) check (current_player <= 6),
    primary key(game_id)
);

create table players(
	player_id 	int(1), #check (player_id > 0 && player_id <=6),
    game_id 	int not null,
    player_name	varchar(20),
    account 	int(7),
    jailcards 	int(1),
	color		varchar(10),
    position	int(2) check(position <= 40 && position > 0),
    primary key(player_id, game_id),
    foreign key (game_id) references games(game_id)
);

create table player_properties(
	field_id	int(2) check (field_id > 0 && field_id <= 40),
    game_id		int,
    player_id	int,
    num_houses	int(1) check (num_houses <= 4 && num_houses >= 0),
    num_hotels	int(1) check (num_hotels = 0 || num_hotels = 1),
    primary key (field_id, game_id, player_id),
	foreign key (player_id, game_id) references players(player_id, game_id)

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
    foreign key (player_id, game_id) references players(player_id, game_id)
);

create view fullplayer as (
	select * from 
		players
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
    insert into jail values (player_id, game_id, 0, 0, false);
END//

create procedure CreateGame(
	IN game_name varchar(30),
    IN current_player int(1),
    OUT id int
)
BEGIN
	insert into games values (null, game_name, CURTIME(), current_player);
    select LAST_INSERT_ID() into id;
    insert into bank values (id, 32, 12);
END//

create procedure SavePlayer(
	IN game_id int,
    IN player_id int,
    IN account int,
    IN jailcards int,
    IN position int,
    IN num_doubles int,
    IN jail_time int,
    IN isInJail boolean
)
BEGIN
	UPDATE players, jail
		set players.account = account,
			players.jailcards = jailcards,
            players.position = position,
            jail.num_doubles = num_doubles,
            jail.jail_time = jail_time,
            jail.isInJail = isInJail
		where players.game_id = game_id && players.player_id = player_id 
				&& jail.game_id = game_id && jail.player_id = player_id;
END//

create procedure SaveField(
	IN field_id int,
    IN game_id int,
    IN player_id int,
    IN num_houses int,
    IN num_hotels int
)
BEGIN
	insert into player_properties values(field_id, game_id, player_id, num_houses, num_hotels)	
		on duplicate key update player_properties.player_id = player_id,
			player_properties.num_houses = num_houses,
			player_properties.num_hotels = num_hotels;
END//

create procedure SaveGame(
	IN game_id int,
	IN current_player int
)
BEGIN
	UPDATE games 
		set last_saved_time = Now(),
			games.current_player = current_player
		where
			games.game_id = game_id;
END//
delimiter ;

