

-- Create fictive game no.1
call CreateGame("Game 1", 1, @id);
-- With 3 players, at various positions and states
insert into players values(1,1, "Player one", 21750, 0, "blue", 32);
insert into jail values(1,1, 0,0,false);
insert into players values(2,1, "Player two", 24000, 0, "white", 25);
insert into jail values(2,1,0,0,false);
insert into players values(3,1, "Player three", 12500, 0, "magenta", 30);
insert into jail values(3,1,0,0,false);
-- They also own a couple of fields
call SaveField(7,1,1,2,0);
call SaveField(9,1,1,2,0);
call SaveField(10, 1, 1, 1, 0);

call SaveField(14,1,2,0,0);
call SaveField(15,1,2,0,0);

call SaveField(38,1,3,0,true);
call SaveField(40,1,3,0,true);
