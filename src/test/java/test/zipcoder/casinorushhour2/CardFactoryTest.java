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
    public void givenCardFactoryIsInstantiatedCreateCard() {
        factory = new CardFactory();
        assertEquals("createCard() method should return an object of the card type", true, (factory.createCard() instanceof Card));
    }

    /**
     * If 52 cards have been created already, CardFactory createCard() method should return null
     */
    @Test
    public void givenCardFactoryHasCreatedFiftyTwoCardsCreateNoMoreCards() {
        factory = new CardFactory();
        for (int i = 0; i < 52; i++) {
            factory.createCard();
        }
        Card c = factory.createCard();

        assertEquals("Once 52 cards have been created don't allow factory to create any more cards. return null", null, c);
    }

    /**
     * This test ensures that when a card is created by CardFactory, it has the right suit based off of
     * how many cards have been created. The default suit for the first
     * 13 cards is HEARTS, the next 13s' suit is DIAMONDS, the next 13s' suit will be CLUBS and the final
     * 13s' suit will be spades. Uses the the getSuit() method in the Card class to get the value needed
     */
    @Test
    public void given52CardsCreatedThenCheckEachCardSuit() {
        factory = new CardFactory();
        Card[] cards = new Card[52];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = factory.createCard();
        }

        for (int i = 0; i < 13; i++) {
            assertEquals("Cards 1-13 should be HEARTS", "HEARTS", cards[i].getSuit());
        }
        for (int i = 13; i < 26; i++) {
            assertEquals("Cards 14-26 should be DIAMONDS", "DIAMONDS", cards[i].getSuit());
        }
        for (int i = 26; i < 39; i++) {

            assertEquals("Cards 27-39 should be CLUBS", "CLUBS", cards[i].getSuit());
        }
        for (int i = 39; i < 52; i++) {
            assertEquals("Cards 39-52 should be SPADES", "SPADES", cards[i].getSuit());
        }
    }

    /**
     * Test to make sure when the CardFactor creates a 52 cards, make sure the cards 1-13 of each
     * suit have the correct name- 1="Ace", 2 through 10 = "2" through "10, 11= "Jack", 12= "Queen",
     * 13= "King"
     */
    @Test
    public void given52CardsCreatedThenCheckName() {
        factory = new CardFactory();
        Card[] cards = new Card[52];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = factory.createCard();
        }

        for (int i = 1; i <= 52; i++) {
            String name = "";
            int num;
            if (i > 13 && i <= 26) {
                num = i - 13;
            } else if (i > 26 && i <= 39) {
                num = i - 26;
            } else if (i > 39) {
                num = i - 39;
            } else {
                num = i;
            }
            switch (num) {
                case (1): {
                    name = "Ace";
                    break;
                }
                case (11): {
                    name = "Jack";
                    break;
                }
                case (12): {
                    name = "Queen";
                    break;
                }
                case (13): {
                    name = "King";
                    break;
                }
                default: {
                    name = String.valueOf(num);
                }
            }
            assertEquals("Cards 1-13  of each suit should have name of Ace, 2, 3 ... 10, Jack, Queen, King", name, cards[i - 1].getName());
        }
    }


}
