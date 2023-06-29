# Tetris-in-Java

This is a Tetris game implemented in Java using JDK version 18.0 and Gradle 7.5. The game's graphical user interface (GUI) is built using JavaFX.

## Requirements

To run and build the Tetris game, you will need the following:

- Java Development Kit (JDK) version 18.0 or later
- Gradle 7.5 or later

## How to Run

Follow these steps to run the Tetris game:

1. Clone or download the repository to your local machine.
2. Open a terminal or command prompt and navigate to the project directory.
3. Build the project using Gradle by running the following command:

   gradle build

4. Once the build is successful, run the game using the following command:

   gradle run

5. The Tetris game should now launch in a new window.

## How to Play

- Use the arrow keys (up, down, left, right) to move and rotate the falling tetrominoes.
- The goal is to complete horizontal lines by filling them with tetrominoes.
- When a line is completed, it will be cleared, and you will earn points.
- As the game progresses, the tetrominoes will fall faster, increasing the difficulty.
- The game ends when the tetrominoes stack up to the top of the playfield.

## Project Structure

The project consists of the following classes:

- Controller.java: Acts as the controller, handling user input and game logic.
- Form.java: Acts as the model class, managing the playfield grid and tetrominoes.
- Tetris.java: Acts as the view and the main class, responsible for initializing the game window and UI.