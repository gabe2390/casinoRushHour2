package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ghumphrey on 9/24/15.
 */
public class BlackJackTest {
    Player player;
    Dealer DEALER;
    Deck deck;
    BlackJack blackJackTest;
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> testHand = new ArrayList<Card>();
    ArrayList<Card> testHand2 = new ArrayList<Card>();
    Scanner key = new Scanner("100");
    Scanner key2 = new Scanner("y");
    Scanner key3 = new Scanner("n");
    Map<String, List<Card>> hands;


    @Before
    public void staging() {
        player = new Player("test");
        deck = new Deck(player);
        blackJackTest = new BlackJack(deck);
        DEALER = new Dealer();
        int currentPot = 0;
        hands = new HashMap<String, List<Card>>();



        Card firstCard = new Card();
        firstCard.setName("Ace");
        firstCard.setSuit(Suit.DIAMONDS);
        firstCard.setValue(1);
        Card secondCard = new Card();
        secondCard.setName("King");
        secondCard.setSuit(Suit.HEARTS);
        secondCard.setValue(10);
        Card thirdCard = new Card();
        thirdCard.setName("Four");
        thirdCard.setSuit(Suit.SPADES);
        thirdCard.setValue(4);
        Card fourthCard = new Card();
        fourthCard.setName("Eight");
        fourthCard.setSuit(Suit.DIAMONDS);
        fourthCard.setValue(8);

        playerHand.add(firstCard);
        playerHand.add(secondCard);

        testHand.add(thirdCard);
        testHand.add(fourthCard);


    }
        @Test
    public void evaluatePoints(){
            assertEquals("Returns raw total of hand", 11, blackJackTest.evaluatePoints(playerHand));

}
    @Test
    public void testAlternateAcePointsAdd10(){
        assertEquals("Should add 10 to your total (if amount entered was less than 12", 15, blackJackTest.alternateAcePoints(5));
    }

    @Test
    public void testAlternateAcePointsKeepSame(){
        assertEquals("Should add 10 to your total (if amount entered was less than 12", 15, blackJackTest.alternateAcePoints(15));
    }

    @Test
    public void testGiveCardsBack(){
        testHand2.add(deck.getCards().remove(0));
        testHand2.add(deck.getCards().remove(0));
        assertEquals("Should put cards back in deck to return true", true,blackJackTest.giveCardsBack(testHand2));
    }

   @Test
    public void testAskToBet(){
        int betTest = blackJackTest.askToBet(key);
        assertEquals("Should return 100 for the bet value", 100, betTest);
    }

    @Test
    public void testAdjustAceTotalWithAce(){
        assertEquals("Should add 10 to the raw score of 11 to return 21", 21,blackJackTest.adjustAceTotal(playerHand));
    }

    @Test
    public void testAdjustAceTotalWithoutAce(){
        assertEquals("Should check for Ace and then return raw score of 12", 12,blackJackTest.adjustAceTotal(testHand));
    }

    @Test
    public void testAskForHitYES(){
        blackJackTest.dealCards();
        assertEquals("Should return true when asked if you would like to hit.",true, blackJackTest.askForHit(key2));

    }

    @Test
    public void testAskForHitNO(){

        assertEquals("Should return false when asked if you want to hit.",false, blackJackTest.askForHit(key3));
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