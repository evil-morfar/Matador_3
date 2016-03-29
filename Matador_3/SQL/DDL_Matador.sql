-- Make sure the DB exists
CREATE DATABASE if not EXISTS MatadorDB;
-- Reset the tables


drop table if exists player_properties;
drop table if exists bank;
drop table if exists jail;
drop table if exists players;
drop table if exists games;


create table games(
	id 					int AUTO_INCREMENT not null,
    game_name			varchar(30),
    last_saved_time		timestamp,
    current_player		int(1) check (current_player <= 6),
    cards				binary(30),
    primary key(id)
);

create table players(
	id 			int auto_increment not null,
    game_id 	int not null,
    player_name	varchar(20),
    account 	int(7),
    jailcards 	int(1),
	color		varchar(10),
    position	int(2) check(position <= 40 && position > 0),
    primary key(id, game_id),
    foreign key (game_id) references games(id)
);

create table player_properties(
	field_id	int(40),
    game_id		int,
    player_id	int,
    num_houses	int(4),
    num_hotels	int(1),
    field_type	varchar(10),
    primary key (field_id, game_id),
	foreign key (player_id) references players(id),
    foreign key (game_id) references games(id)

);

create table bank(
	game_id			int,
	houses_left		int(2) check(houses_left >= 0),
    hotels_left		int(2) check(hotels_left >= 0),
    primary key(game_id),
    foreign key(game_id) references games(id)
);

create table jail(
	player_id		int,
    game_id			int,
    num_doubles		int(1) check(num_doubles <= 3),
    jail_time		int(1) check(jail_time <= 3),
    isInJail		boolean,
    primary key (player_id, game_id),
    foreign key (player_id) references players(id),
    foreign key (game_id) references games(id)
);