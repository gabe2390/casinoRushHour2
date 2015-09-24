package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Card;
import org.junit.Before;
import org.junit.Test;

import static io.zipcoder.casinorushhour2.Suit.DIAMONDS;
import static io.zipcoder.casinorushhour2.Suit.HEARTS;
import static org.junit.Assert.assertEquals;

/***
 * Created by Gabriel Humphrey on 9/23/15.
 * This test suite tests the Card class and all of its public methods
 */
public class CardTest {
    Card card;

    /**
     * Instantiate a card before every test
     */
    @Before
    public void instantiateCard(){
        card= new Card();
    }

    /**
     * Tests to make sure the getName() returns the correct card name
     */
    @Test
    public void givenCardNameIsSetThenGetName(){
        card.setName("King");
        assertEquals("Test that name field returns the correct name when getName() is called", "King", card.getName());
    }

    /**
     * Tests to make sure the getSuit method returns the correct suit
     */
    @Test
    public void givenCardSuitIsSetThenGetSuit(){
        card.setSuit(HEARTS);
        assertEquals("Test that suit field returns the correct String representation if the suit when getSuit is called", "HEARTS", card.getSuit());
    }


    /**
     * Makes sure the toString() method returns the correct full card name. eg- "King of Spades"
     */
    @Test
    public void givenCardIsInstantiatedThenToString(){
        card.setName("Ace");
        card.setSuit(DIAMONDS);

        assertEquals("Check to make sure toString returns the full name of a card",card.getName()+" of "+card.getSuit(), card.toString());
    }
}