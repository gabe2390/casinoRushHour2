package io.zipcoder.casinorushhour2;

/**
 * Created by rsparks on 9/24/15.
 * Created by rsparks on 9/22/15.
 * Interface of Games in our Casino. Contains methods that all games need.
 */
public interface Game {

    /**
     * Initiates the game loop for a particular game
     */
    void playGame();

    /**
     * Exits you to the Main Menu (Casino Menu)
     */
    void exitGame();

    /**
     * Checks for a winner of a particular game
     * @return boolean -- Win or Lose
     */
    boolean checkForWinner();
}
