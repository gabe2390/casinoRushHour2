package io.zipcoder.casinorushhour2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This is the template for a CardFactory class.
 */
public class CardFactory {
    List<Card> cards = new ArrayList<Card>();

    /**
     * @return Card
     * Should return a new instance of Card. If card.size() == 52 , return null to ensure no more cards
     * are able to be created.
     */
    public Card createCard() {

        if (cards.size() < 52) {
            Card card = new Card();
            setCardSuit(card);
            setCardName(card);
            cards.add(card);
            return card;
        } else {
            return null;
        }
    }

    /**
     * Sets the suit of the card being created. Sets suit based on the order in which the card was created.
     * Numbers 1-13 = HEARTS, 14-26= DIAMONDS, 27-39= CLUBS, 40-52= "SPADES"
     * @param card -receives the card from instance from the createCard method.
     */
    public void setCardSuit(Card card) {
        int numOfCards = cards.size() + 1;

        if (numOfCards > 13 && numOfCards <= 26) {
            card.setSuit(Suit.DIAMONDS);
        } else if (numOfCards > 26 && numOfCards <= 39) {
            card.setSuit(Suit.CLUBS);
        } else if (numOfCards > 39 && numOfCards <= 52) {
            card.setSuit(Suit.SPADES);
        } else {
            card.setSuit(Suit.HEARTS);
        }
    }

    /**
     * Sets the name of the card being created. Each suit has 13 cards.
     * Each card should be named as follows 1= "Ace", 11= "Jack", 12= "Queen", 13= "King",
     * all others = value of number (2 through 10);
     * @param card - receives the card instance from the createCard method
     */
    public void setCardName(Card card) {
        int numOfCards = cards.size() + 1;
        String name = "";

        numOfCards = adjustNumber(numOfCards);

        switch (numOfCards) {
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
                name = String.valueOf(numOfCards);
            }
        }
        card.setName(name);
    }

    /**
     * @param n - will be based on the size of the cards List
     * @return int - int returned will only be used to assign a name to a card
     */
    private int adjustNumber(int n) {
        if (n > 13 && n <= 26) {
            return n - 13;
        } else if (n > 26 && n <= 39) {
            return n - 26;
        } else if (n > 39) {
            return n - 39;
        } else {
            return n;
        }
    }
}