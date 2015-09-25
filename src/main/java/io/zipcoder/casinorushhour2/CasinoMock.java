package io.zipcoder.casinorushhour2;

/**
 * Created by emaron on 9/24/15.
 */
public class CasinoMock {
    public static void main(String[] args) {


/**
 * Method calls all the appropriate constructors
 */

        Player player = new Player("Evan");

        Deck deck = new Deck(player);

        Poker poker = new Poker(deck);

/**
 * This Method changes the current GameState to start the game loop
 */

        poker.playGame();

/**
 * Deals cards to player
 * Player prompted to bet
 */




/**
 * Checks to see who the winner is
 */




/**
 * This Method exits the Poker game loop
 */




    }


}