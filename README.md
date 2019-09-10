# Dominion (The Deck-Building Card Game)
<span style="color:red">NOTE: This project is still a WIP</span>


## I. SETUP:

Since this project utilizes Maven, it only requires two manual installations to run the project:

- Java (Version 11 or higher)
- Maven Apache (Version 3.6.1 was used for this project)

Maven will resolve the rest of the dependencies for JavaFX, JUnit, and Protocol Buffers.

To build the game, enter the game directory that contains the pom.xml file and run the following
command:
<span style="color:darkgreen; background-color:lightgreen">mvn package</span>

To run the game, enter the /target directory that was generated and run the following command:
<span style="color:darkgreen; background-color:lightgreen">java -jar Dominion.jar</span>

This will open the game in the Start Menu, where you can set up a game to host or to join a game
that is being hosted by someone else.


## II. GAME INSTRUCTIONS:

### 2.1 Start Menu
![Start Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/start_menu.png)
Upon opening the game, the user will be greeted with a menu called “Match Options” with two
buttons, “Host Game” and “Join Game”. These buttons respectively enable the user to host a
game of Dominion or join a game that is being hosted.


Upon clicking the “Host Game” button, a menu will be presented with prompting for details of
the game you are hosting.
![Host Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/host_menu.png)

```
Username: The name of your player.
Port: A local port that other players will connect to.
Lobby: Size The number of players that will be playing the game.
```
Clicking the “Host Game” button will take you to the Setup Menu, where the user will select 10
cards that will be used for the game. In addition, the game will also create the server based on the
details the user has provided, as well as a client for the user which will connect to the created
server.


Upon clicking the “Join Game” button, a menu will be presented with prompting for details of a
game you are joining.
![Join Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/join_menu.png)

```
Username: The name that will be used for your player.
IP Address: The IP address of the host.
Port: The port that the server is being hosted on.
```
Clicking the “Join Game” button will take you to the Lobby Menu, where the user will wait until
the lobby is full and the 10 cards that will be used for the game have been selected.


### 2.2 Setup Menu

When the “Host Game” button is clicked the host will be brought the Setup Menu.
![Setup Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/setup_menu1.png)

This menu allows the host to select the 10 cards that will be used to play the game. Clicking on a
card will highlight the card to indicate that it has been selected.
![Setup Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/setup_menu2.png)

In the image above, the cards Bandit, Harbinger and Market have been selected. The host may
click on the highlighted cards again to deselect them.

If a host is unsure of what cards to choose, they can choose a pre-set game by clicking on one of
the five buttons on the right of the screen.
![Setup Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/setup_menu3.png)

Clicking on one of them will select 10 cards based on game configurations in the official
Dominion rule book


In this image, the “First Game” button has been clicked, and cards associated with that pre-set
have been selected.
![Setup Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/setup_menu4.png)

The “Reset” button is available to instantly deselect all cards that have been selected.

Once 10 cards have been selected, the “Host Game” button will be enabled and clicking it will
lock in the card choices. The host will be switched back to the “Start Menu” where they will wait
in the lobby.


### 2.3 Lobby Menu

Upon joining a game or hosting a game and then selecting the game’s kingdom cards, the user
will be taken to the “Start Menu” and wait until two conditions are fulfilled:

- The lobby must be full
- The game’s kingdom cards must have been selected

Once both of these conditions are fulfilled, the game will begin and all players will be taken to
the game screen.

The view of the lobby from the host:
![Lobby Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/lobby_menu_host.png)

The view of the lobby from a player joining:
![Lobby Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/lobby_menu_joined.png)

### 2.4 Gameplay

Once all players have joined and the kingdom cards have been selected, the game will begin.
![Gameplay](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/gameplay.png)

The user interface can be broken down as:
![Lobby Menu](https://raw.githubusercontent.com/victordiep/Dominion-Card-Game/master/images/gameplay_breakdown.png)

```
RED: The supply
PURPLE: The player’s deck and discard pile
BLUE: The player’s hand
GREEN: A turn details section which informs about the state of the turn
ORANGE: A log of events that occurred in the game and the trash pile
```
The game’s rule book outlines how the game works.


