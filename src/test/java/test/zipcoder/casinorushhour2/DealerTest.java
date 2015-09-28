package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Card;
import io.zipcoder.casinorushhour2.Dealer;
import io.zipcoder.casinorushhour2.Deck;
import io.zipcoder.casinorushhour2.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rsparks on 9/24/15.
 */
public class DealerTest {
    Dealer dealer;
    Deck testDeck;

    @Before
    public void beforeTestsCreateDealer() {
        dealer = new Dealer();
        testDeck = new Deck(new Player("Ricky"));
        testDeck.init();
        ArrayList<Card> testHand = new ArrayList<Card>();

        testHand.addAll(dealer.dealCards(2, testDeck));
    }

    @Test
    public void testDealCards1() {
        Deck testDeck = new Deck(new Player("Ricky"));
        testDeck.init();
        ArrayList<Card> testHand = new ArrayList<Card>();

        testHand.addAll(dealer.dealCards(2, testDeck));

        assertEquals("dealCards method should return a list of 2 cards, the first 2 available from the top of the deck. Test using toString from Card", "Ace of HEARTS", testHand.get(0).toString());

        assertEquals("dealCards method should return a list of 2 cards, the first 2 available from the top of the deck. Test using toString from Card", "2 of HEARTS", testHand.get(1).toString());

    }

    @Test
    public void testDealCards2() {
        assertEquals(50, testDeck.getCards().size());
    }

    @Test
    public void testGetHand(){
        assertTrue("Trying to make sure returns a list", dealer.getHand() instanceof List);
    }

    @Test
    public void testGetHandindex(){
        for (int i= 0 ; i<dealer.getHand().size(); i++){
            assertTrue("Checking each object in ArrayList to be a Card", dealer.getHand().get(i) instanceof  Card);
        }
    }


}
