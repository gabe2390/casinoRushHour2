package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ghumphrey on 9/24/15.
 */
public class BlackJackTest {
    Player player;
    Dealer DEALER;
    Deck deckTest;
    BlackJack blackJackTest;
    ArrayList<Card> playerHand = new ArrayList<Card>();


    @Before
    public void staging() {
        player = new Player("test");
        deckTest = new Deck(player);
        blackJackTest = new BlackJack(deckTest);
        DEALER = new Dealer();
        int currentPot = 0;

        Card firstCard = new Card();
        firstCard.setName("Ace");
        firstCard.setSuit(Suit.DIAMONDS);
        Card secondCard = new Card();
        secondCard.setName("King");
        secondCard.setSuit(Suit.HEARTS);
        Card thirdCard = new Card();
        thirdCard.setName("Four");
        thirdCard.setSuit(Suit.SPADES);
        Card fourthCard = new Card();
        fourthCard.setName("Eight");
        fourthCard.setSuit(Suit.DIAMONDS);

        playerHand.add(firstCard);
        playerHand.add(secondCard);

        DEALER.addToHand(thirdCard);
        DEALER.addToHand(fourthCard);
    }

    @Test
    public void testCheckForWinner(){
       assertEquals(false, blackJackTest.checkForWinner());
    }
}



/** BlackJack jack;
 Dealer dealer;
 Deck deck;
 Map<String, List<Card>> hands;
 String[] computerNames;

 /**
 * creates a black jack instance ,a deck (then initializes it), a dealer, a map for holding player hands, and an
 * array for holding 4 player names. Then add two cards from the deck to each player's hand

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
 */