# Robot Game

Move the robot along the grid by giving it commands!

Allowed commands - 
1) POSITION X Y DIRECTION<br/>

Sets initial position of the robot. e.g-
```aidl
POSITION 1 3 EAST
```
2) FORWARD N

Move the robot N steps in the same direction it was already in. e.g-
```aidl
FORWARD 3
```
3) WAIT - Do nothing!
4) TURNAROUND - Turn 360!
5) LEFT - Turn left!
6) RIGHT - Turn right!

## Prequisites to run program
<ul>
<li>JDK 11</li>
<li>Maven</li>
</ul>

## How to run the tests
```
mvn clean
mvn test
```

## How to run
```
mvn clean
mvn spring-boot:run
```

Then, visit
```aidl
http://localhost:9086/robot-game/
```
from browser to play the game.

<br/>
Have fun!

