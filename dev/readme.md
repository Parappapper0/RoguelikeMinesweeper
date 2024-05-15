-----------------
 Admin database:
-----------------
http://localhost:8000/index.php

hostname: app-db:3306
username: root
password: root


-----------------
 Frontend:
-----------------
Start: cd fe-angular && npm start
http://localhost:4200



-----------------
 Backend:
-----------------
http://localhost:8080

-----------------
DATABASE CELL FORMAT
-----------------
xxxxyyyy

xxxx = num-mines
{
0 = 0 mines
1 = 1 mine
2 = 2 mines
...
9 = 9 mines
10 = unrevealed

[11, 15] = special states
}

yyyy = entity-id
{
0 = none
1 = player
2 = coin
3 = mine

[4, 15] = other entities
}
