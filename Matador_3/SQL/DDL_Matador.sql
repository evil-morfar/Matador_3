-- Make sure the DB exists
CREATE DATABASE if not EXISTS MatadorDB;
-- Reset the tables
drop table if exists players;
drop table if exists games;
drop table if exists player_properties;
drop table if exists bank;
drop table if exists jail;


create table games(
	id 					int AUTO_INCREMENT not null,
    game_name			varchar(30),
    last_saved_time		timestamp,
    current_player		int(6),
    cards				binary(30),
    primary key(id)
);

create table players(
	id 			int,
    game_id		int,
    player_name	varchar(20),
    account 	int,
    jailcards 	int,
	color		varchar(10),
    position	int,
    primary key(id, game_id),
    foreign key (game_id) references games(id)
);

create table player_properties(
	field_id	int,
    num_houses	int,
    num_hotels	int,
    field_type	varchar(10),
    player_id	int,
    game_id		int,
    primary key (field_id),
	foreign key (player_id) references players(id),
    foreign key (game_id) references games(id)

);

create table bank(
	houses_left		int,
    hotels_left		int,
    game_id			int,
    primary key(game_id),
    foreign key(game_id) references games(id)
);

create table jail(
	player_id		int,
    game_id			int,
    num_doubles		int,
    jail_time		int,
    isInJail		boolean,
    primary key (player_id, game_id),
    foreign key (player_id) references players(id),
    foreign key (game_id) references games(id)
);