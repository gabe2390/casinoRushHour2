package io.zipcoder.casinorushhour2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rsparks on 9/24/15.
 * Dealer class that deals cards, shuffles deck, and returns cards to the deck
 */
public class Dealer {

    List<Card> hand;

    /**
     * Constructor for Dealer Class
     */
    public Dealer() {
        hand = new ArrayList<Card>();
    }

    /**
     * Deals an amount of cards specified by the input of the function
     * @param deal Tells the dealer how many cards to deal
     * @param deck Tells the dealer which deck to deal from
     * @return  Returns an ArrayList of dealt cards
     */
    public List<Card> dealCards(int deal, Deck deck) {
        List<Card> dealtCards = new ArrayList<Card>();

        for (int i = 0; i < deal; i++) {
            dealtCards.add(deck.getCards().remove(0));
        }
        return dealtCards;
    }

    /**
     * Shuffles the deck
     * @param deck inputs deck to be shuffled
     */
    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getCards());
    }

    /**
     * Gets the hand of the specified person
     * @return hand Arraylist of the hand specified
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Adds dealt cards to hand
     * @param cards cards that are to be added to hand
     */
    public void addToHand(List<Card> cards) {
        hand.addAll(cards);
    }
}