package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ghumphrey on 9/24/15.
 */
public class BlackJackTest {
<<<<<<< HEAD
    BlackJack jack;
    Dealer dealer;
    Deck deck;
    Map<String, List<Card>> hands;
    String[] computerNames;

    /**
     * creates a black jack instance ,a deck (then initializes it), a dealer, a map for holding player hands, and an
     * array for holding 4 player names. Then add two cards from the deck to each player's hand
     */
    @Before
    public void createBlackJack() {
        deck = new Deck(new Player("No named brotha"));
        deck.init();

        dealer = new Dealer();

        jack = new BlackJack(deck);
        hands = new LinkedHashMap<String, List<Card>>();

        computerNames = new String[]{"tariq", "froilan", "joey", "gabe"};

       dealer.shuffleDeck(deck);

        for (int i = 0; i < computerNames.length; i++) {
            hands.put(computerNames[i], dealer.dealCards(2, deck));
        }

        for(String n : hands.keySet()){
            System.out.println("Name: "+ n + " Hand: "+ hands.get(n));
        }
    }

    @Test
    public void givenAllPlayersHaveTwoCardsGiveBackCards() {


    }
=======

>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78

}
