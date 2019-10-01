=========================
Robot World
Author: DavidMOBrien
=========================

Robot World is a game made using JFrame and is played with the following rules:
	- You control a "Player" placed randomly on a grid. Every turn you can move to a neighboring space on the grid but
	only one space (you can also choose to stand still). Your objective is to survive longer than the "evil robots".
	- There are 3 "evil robots" randomly placed on the grid. Every time that you make a move, they will make a move closer
	to where your new location is, if an evil bot or a player ever hit the same tile, the player loses.
	- There are also 3 "scrap piles" which are depicted as skull tiles on the map. If you or an evil robot move into a 
	skull tile they "die". If it is an evil robot then they get removed from the board. If it is the player then they
	lose the game.
	- In the four corners there are "teleporters" which if stepped on by a player will teleport to a random, open spot
	on the grid (it will not place a player directly on top of a skull tile or an evil robot, but it is possible to
	teleport a player to a spot NEXT to an evil robot, and then during their next turn they can move directly on top
	of the player and end the game, so use teleporters only when necessary)
