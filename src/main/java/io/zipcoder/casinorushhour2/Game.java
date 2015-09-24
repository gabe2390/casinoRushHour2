package io.zipcoder.casinorushhour2;

/**
<<<<<<< HEAD
 * Created by rsparks on 9/24/15.
=======
 * Created by rsparks on 9/22/15.
 * Interface of Games in our Casino. Contains methods that all games need.
>>>>>>> master
 */
public interface Game {
<<<<<<< HEAD
=======
    /**
     * Sets the default GameState to NOTSTARTED
     */
    GameState state = GameState.NOTSTARTED;

    /**
     * Initiates the game loop for a particular game
     */
    void playGame();

    /**
     * Changes game state from NOTRUNNING to RUNNING or vice versa
     */
    void changeGameState();

    /**
     * Exits you to the Main Menu (Casino Menu)
     */
    void exitGame();

    /**
     * Checks for a winner of a particular game
     * @return boolean -- Win or Lose
     */
    void checkForWinner();
>>>>>>> master
}
