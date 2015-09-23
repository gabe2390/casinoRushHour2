package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Card;
import io.zipcoder.casinorushhour2.CardFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This test suite tests the CardFactory class and all of its public methods
 */
public class CardFactoryTest {
    CardFactory factory;

    /**
     * CardFactory createCard() method should return an instance of type Card
     */
    @Test
    public void givenCardFactoryIsInstantiatedCreateCard(){
        factory= new CardFactory();
        assertEquals("createCard() method should return an object of the card type", true, (factory.createCard() instanceof Card));
    }

    /**
     * If 52 cards have been created already, CardFactory createCard() method should return null
     */
    @Test
    public void givenCardFactoryHasCreatedFiftyTwoCardsCreateNoMoreCards(){
        factory= new CardFactory();
        for(int i=0; i< 52; i++){
            factory.createCard();
        }
        Card c= factory.createCard();

        assertEquals("Once 52 cards have been created don't allow factory to create any more cards. return null", null, c);
    }
}
