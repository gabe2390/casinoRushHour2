package io.zipcoder.casinorushhour2;

/**
 * Created by rsparks on 9/22/15.
 */
public interface Game {
    GameState defaultState = GameState.NotStarted;

    void playGame();
    void changeGameState();
    void exitGame();
    void checkForWinner();
}
