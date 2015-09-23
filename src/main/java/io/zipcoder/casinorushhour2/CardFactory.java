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
     *
     * @return Card
     * Should return a new instance of Card. If card.size() == 52 , return null to ensure no more cards
     * are able to be created.
     */
    public Card createCard() {
        if (cards.size() < 52) {
            Card card = new Card(cards.size() + 1);
            cards.add(card);
            return card;
        } else {
            return null;
        }
    }
}