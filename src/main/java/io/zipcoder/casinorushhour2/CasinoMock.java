package io.zipcoder.casinorushhour2;

/**
 * Created by emaron on 9/24/15.
 */
public class CasinoMock {
    public static void main(String[] args) {
        Player player = new Player("Evan");

        Deck deck = new Deck(player);

        Poker poker = new Poker(deck);

        poker.checkForWinner();
    }


}