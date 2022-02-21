# SnakeTron
SnakeTron is a combination of the games Snake and Tron. Eat apples to grow your snake in order to trap your opponent and claim victory. I wrote this game in Junior year of High School for my AP project. It's simple, but a good few minutes of fun if you have a friend. Enjoy!

## Installation
The simplest way to install and play the game is to download SnakeTron.jar from the releases tab on the right.

If you would like to compile the source code directly instead, install the JDK and run the following commands:
1. `javac *.java`
2. `java Garden`

## How to Play
### Controls:
* The pink snake is controlled by WASD
* The blue snake is controlled by the arrow keys

### Rules:
1. The object of the game is to get more points that your opponent
2. Points are earned by killing your opponent
3. You can kill your opponents in three ways
    * Force their head to hit your tail
    * Force their head to hit their own tail
    * Force their head to hit the wall
    * If your heads collide perfectly, no points are gained
4. Eating the red apple that appears on the board will grow your tail
    * Be careful not to hit yourself!
5. The game has no set stopping point, so whoever has the most points when you get bored is the winner.

## How It's Made
The Garden file was written to manage the game. It calls the update function repeatedly, which itself updates the snakes' positions and checks for collisions.

The SnakeHead file is the primary controller for each snake. It's position is updated every update by the Garden based upon its direction.

The SnakeBody file is a child of SnakeHead. It stores the body part ahead of it and simply assumes its position each update.

### What I Learned
* How to use Swing to make graphics in Java
* How to better use object-oriented concepts such as inheritance to facilitate interaction between the game elements
* How annoying it is for 1 person to test a 2 player game

## Known Issues
* There are almost no comments. I could definitely use some comments. Apparently High School me found them unnecessary.

## Planned Features
As it stands, I consider this game complete. It was only meant to be a small project. However, I may continue its development if I am truly struck with the inspiration.
