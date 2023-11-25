"# project-l12gr03"
# Tic Tac Toe project.Game 2.0

The game is basically composed of a Main Tic Tac Toe project.Game that contains a Mini Tic Tac Toe project.Game within each space of the Main Tic Tac Toe project.Game. The game begins with the order of play of players "X" and "O" being defined by a coin toss, randomly. Next, the player who starts the game chooses where he wants to start playing, but the place where the player places his piece will define which Mini Tic Tac Toe project.Game the opponent will play in his turn.
When someone wins one of the games, the entire mini tic-tac-toe becomes a point for that player and whoever makes a row with three mini tic-tac-toe games in the Main Tic-Tac-Toe project.Game wins.
During the game two things can happen:
- A player is forced to play in a Mini Tic Tac Toe project.Game that has already ended, in which case the player can choose which available Mini Tic Tac Toe project.Game he wants to play.
- A Mini Tic Tac Toe will tie, in this case, this mini-game will work as a joker that works with both players to form the Main Tic Tac Toe row.

## LDTS_1203 - Tick Tock Toe
In this 2 player game we have a tic-tac-toe grid where each square has inside it a traditional game of tic-tac-toe.
At first a player chooses in which smaller tic-tac-toe square it wants to play, after that,the next player
is forced to play on the smaller game whose position on the bigger grid corresponds to the one played by the last player,
this means that if a player places its symbol on the middle square of a small tic-tac-toe then the next player
has to place his on any square from the small tick-tac-toe that it's placed at the center of the bigger grid,
when a small tic-tac-toe ends then that square in the bigger grid is considered won by the victorious symbol or as none if the smaller game ended in a tie.

The game is over when one player has won 3 smaller games in adjacent positions (identically to a tradition game of tick-tac-to)
or when there aren't any more spaces to be filled.

After the game ends the players scores are updated accordingly to the result, it is shown how much time tha game lasted and the time of the shortest game,
players also have the choice to keep playing in which case the game restarts. 

This project was developed by Amanda Tartarotti (@up.pt) Gonçalo Sousa (@up.pt) and Pedro Oliveira (up202206498@up.pt) for LDTS 2023⁄24.

### IMPLEMENTED FEATURES

- **Menu** - The game provides a menu capable of starting the game, showing the rules or exiting the program.
- **Rules** - Shows on the screen a text describing the rules of the game.
- **Registration** - Allows player 1 to decide if it wants to have the crosses or circles symbol.


### PLANNED FEATURES

-**Move** - A player can change the selected square either on the bigger grid or a smaller one when appropriate
-**Select** - A player can choose to play its symbol that it has selected
