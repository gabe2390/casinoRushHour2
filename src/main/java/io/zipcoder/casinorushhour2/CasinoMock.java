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

        // SevenCardStud sevenCardStudPoker = new SevenCardStud(deck);

         Poker poker = new Poker(deck);

       //  FiveCardDraw fiveCardDraw = new FiveCardDraw((deck));

       // SevenCardDraw sevenCardDraw = new SevenCardDraw(deck);

/**
 * This Method changes the current GameState to start the game loop
 */
       // sevenCardDraw.playGame();
        //fiveCardDraw.playGame();
        poker.playGame();
        // sevenCardStudPoker.playGame();

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