-- Make sure the DB exists
CREATE DATABASE if not EXISTS MatadorDB;
-- Reset the tables
drop table if exists players;
drop table if exists games;
drop table if exists player_properties;
drop table if exists bank;
drop table if exists jail;

create table players(
	id 			int(6),
    game_id		int(1000),
    name 		varchar(20),
    account		int(1000000),
    jailcards 	int(2),
	color		varchar(10),
    position	int(40),
    primary key(id, game_id),
    foreign key (game_id) references games(id)
		on delete set null
);

create table games(
	id 					int(1000),
    game_name			varchar(30),
    last_saved_time		timestamp,
    current_player		int(6),
    cards				binary(30),
    primary key(id)
);

create table player_properties(
	field_id	int(40),
    num_houses	int(4),
    num_hotels	int(1),
    field_type	varchar(10),
    player_id	int(6),
    game_id		int(1000),
    primary key (field_id),
	foreign key (player_id) references players(id)
		on delete set null,
    foreign key (game_id) references games(id)
		on delete set null

);

create table bank(
	houses_left		int(32),
    hotels_left		int(12),
    game_id			int(1000),
    primary key(game_id),
    foreign key(game_id) references games(id) on delete set null
);

create table jail(
	player_id		int(6),
    game_id			int(1000),
    num_doubles		int(3),
    jail_time		int(3),
    isInJail		boolean,
    primary key (player_id, game_id),
    foreign key (player_id) references players(id) on delete set null,
    foreign key (game_id) references games(id) on delete set null
);