package io.zipcoder.casinorushhour2;

import java.util.ArrayList;

/***
 * Created by Gabriel Humphrey on 9/23/15.
 * This Class is the template for a card that will be used to build a Deck.
 */
public class    Card {
    private String name;
    private Suit SUIT;

    /**
     * Constructor
     */
    public Card() {
    }

    /**
     * Sets the suit field
     *
     * @param suit received from the CardFactory when it creates the card
     */
    public void setSuit(Suit suit) {
        this.SUIT = suit;
    }

    /**
     * Sets the name of the card
     *
     * @param name - received from the CardFactory when it creates the card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String -returns the suit of the card in string form
     */
    public String getSuit() {
        return SUIT.name();
    }

    /***
     * @return String - returns the name of the card, "Ace' through "King"
     */
    public String getName() {
        return name;
    }

    /**
     * @return String - returns the full name of a Card - eg. "Ace of Hearts"
     */
    public String toString() {
        return name + " of " + getSuit();
    }
}