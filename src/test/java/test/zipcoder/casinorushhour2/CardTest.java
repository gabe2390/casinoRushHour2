package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Card;
import io.zipcoder.casinorushhour2.CardFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This test suite test the Card class and all o its methods
 */
public class CardTest {
    CardFactory factory;
    Card[] cards;

    /**
     * This test ensures that when a card is instantiated, after it's init() method is called
     * it is created with the right suit based off of how many cards have been created. The default suit for the first
     * 13 cards is HEARTS, the next 13s' suit is DIAMONDS, the next 13s' suit will be CLUBS and the final
     * 13s' suit will be spades. Uses the the getSuit() method to get the value needed
     */
    @Test
    public void givenCardIsInstantiatedThenGetSuit() {
        factory = new CardFactory();
        Card[] cards = new Card[52];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = factory.createCard();
            cards[i].init();
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
     * Testing to make sure the cards 1-13 of each suit have the correct name- 1="Ace",
     * 2 through 10 = "2" through "10, 11= "Jack", 12= "Queen", 13= "King"
     */
    @Test
    public void givenCardIsInstantiatedThenGetName() {
        factory = new CardFactory();
        Card[] cards = new Card[52];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = factory.createCard();
            cards[i].init();
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

    /**
     * Makes sure the toString() method returns the correct full card name. eg- "King of Spades"
     */
    @Test
    public void givenCardIsInstantiatedThenToString(){
        factory = new CardFactory();
        Card[] cards = new Card[52];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = factory.createCard();
            cards[i].init();
        }
        for(int i=0; i<cards.length; i++){
            assertEquals("Ensure that toString is returning the full name of the card",cards[i].getName() + " of "+ cards[i].getSuit(), cards[i].toString());
        }
    }
}