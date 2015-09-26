package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by emaron on 9/25/15.
 */
public class PokerTest {


    /**
     * Sets up all the required fields to test prior to each test case
     */

    Player playerTest;
    Deck deckTest;
    Poker pokerTest;
    ArrayList<Card> playerHand = new ArrayList<Card>();


    @Before
    public void staging() {
        playerTest = new Player("test");
        deckTest = new Deck(playerTest);
        pokerTest = new Poker(deckTest);


        Card firstCard = new Card();
        firstCard.setName("Four");
        firstCard.setSuit(Suit.DIAMONDS);
        Card secondCard = new Card();
        secondCard.setName("Five");
        secondCard.setSuit(Suit.HEARTS);
        Card thirdCard = new Card();
        thirdCard.setName("Four");
        thirdCard.setSuit(Suit.SPADES);
        Card fourthCard = new Card();
        fourthCard.setName("Eight");
        fourthCard.setSuit(Suit.DIAMONDS);
        Card fifthCard = new Card();
        fifthCard.setName("Seven");
        fifthCard.setSuit(Suit.DIAMONDS);

        playerHand.add(firstCard);
        playerHand.add(secondCard);
        playerHand.add(thirdCard);
        playerHand.add(fourthCard);
        playerHand.add(fifthCard);

    }

    /**
     * Tests to determine if any card names match
     */

    @Test
    public void givenHandShouldReturnMapOfSimilarNamedCards() {


        assertEquals("Should return a size of four ", 4, pokerTest.checkForSimilarNamedCards(playerHand).size());
    }

    /**
     * Tests to determine if a player's hand has a flush
     */

    @Test
    public void givenHandShouldReturnEnum() {
        //assertEquals("Should return no flush", Kinds.NOFLUSH, pokerTest.checkForFlushCards(playerHand));
    }

    /**
     * Tests to determine if a player's hand has any possible poker combination
     */

    @Test
    public void givenASortedMapShouldReturn() {
       // assertEquals("Should return a pair", Poker.Kinds.PAIR, pokerTest.InterpretSimilarCardsToPokerHand(pokerTest.checkForSimilarNamedCards(playerHand)));
    }

    /**
     * Test should determine if the random number generated from DealerScore is between 0 and 800
     */

    @Test
    public void shouldReturnARandomInt() {
        assertTrue("Random number is within 0 and 800" + pokerTest.returnDealerScore(), 0 <= pokerTest.returnDealerScore() && pokerTest.returnDealerScore() <= 800);
    }




}