RPSTag - Rock-Paper-Scissors Tag

5-on-5 elimination game in which the last team remaining wins

GOAL: eliminate all of the opposing players before they eliminate all the players on your team
Players are eliminated when they run out of "lives."

Game is played on a 30x30 grid. Players occupy one space each. 
Players play rock-paper-scissors when they encounter each other (when they move into a space that borders an opponent's space; must wait 5 turns before a previous encounter to re-engage)
	-each match of RPS is random number of rounds (between 1 and 10 with ties possible; in the case of a tie, both players move away unscathed)
	-the winner can either take a life or take an item from the other player; if you take the player's life, any items that player had are re-distributed around the board
Hidden items and non-hidden items including lives and balls, which you can throw; if you throw at a player and hit him/her, he/she loses a life; if you miss, the ball is re-distributed

Players have different throwing ranges and accuracies
Players can hide balls in their possession; lives are hidden by default

Classes in project:
Location: provides x and y coordinate of a spot in the game board
Locatable: a game object with a location: Location getLoc() (abstract class)
Player
Bonus Item: isHidden()
Ball or Life are both bonus item locatables

GameBoard is composed of 30x30 grid housing Locatables
-int RPSmatch(Player one, Player two) method; returns the player code of the winner, -1 if there's a draw
