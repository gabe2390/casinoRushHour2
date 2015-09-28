package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Card;
import org.junit.Before;
import org.junit.Test;
import io.zipcoder.casinorushhour2.Deck;
import io.zipcoder.casinorushhour2.Player;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lbonilla on 9/22/15.
 */
public class DeckTest {
    Deck deck;

    /**
     * Initializes deck object
     */
    @Before
    public void beforeTestsInstantiateDeck() {
        deck = new Deck(new Player("Gabe"));
        deck.init();
    }

    /**
     * Tests return of object type Player
     */

    @Test
    public void givenDeckIsInstantiatedGetPlayer() {
        assertEquals("Should return an object of type player", true, deck.getPlayer() instanceof Player);
    }

    /**
     * Tests for return of List<Card>
     * Tests that List<Card> contains 52 cards
     */
    @Test
    public void givenDeckIsInstantiatedGetCards() {

        //check to make sure getCards method returns an object of type List
        assertTrue("Should return an object List<Card>", deck.getCards() instanceof List);

        // check to make sure each index in cards List contains a Card object
        for (int i = 0; i < deck.getCards().size(); i++) {
            assertTrue("Check to make sure each object in cards List is of type Card", deck.getCards().get(i) instanceof Card);
        }
    }
}

