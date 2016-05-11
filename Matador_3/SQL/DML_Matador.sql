

-- Create fictive game no.1
call CreateGame("Medium Game Test", 1, @id);
-- With 3 players, at various positions and states
insert into players values(1,@id, "Player one", 21750, 0, "blue", 32);
insert into jail values(1,@id, 0,0,false);
insert into players values(2,@id, "Player two", 24000, 0, "white", 25);
insert into jail values(2,@id,0,0,false);
insert into players values(3,@id, "Player three", 12500, 0, "magenta", 30);
insert into jail values(3,@id,0,0,false);
-- They also own a couple of fields
call SaveField(7,@id,1,2,0);
call SaveField(9,@id,1,2,0);
call SaveField(10, @id, 1, 1, 0);

call SaveField(14,@id,2,0,0);
call SaveField(15,@id,2,0,0);

call SaveField(38,@id,3,0,true);
call SaveField(40,@id,3,0,true);
