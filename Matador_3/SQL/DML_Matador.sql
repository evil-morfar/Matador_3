

-- Create fictive game no.1
insert into games values(1, "Test game 1", null, 2, 0); -- Cards not yet done
insert into bank values(1,32,12);

insert into players values(1,1, "Player one", 25000, 0, "black", 32);
insert into players values(2,1, "Player Two", 29500, 0, "red", 25);
insert into players values(3,1, "Player three", 28350, 0, "blue", 30);

insert into jail values(1,1,0,0,false);
insert into jail values(2,1,0,0,false);
insert into jail values(3,1,0,0,false);

insert into player_properties values(10,1,1, 0, 0, "green");
