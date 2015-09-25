package io.zipcoder.casinorushhour2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke Bonilla on 9/22/15.
 * This is a class that creates a deck of cards
 */
public class Deck {

    private CardFactory factory;
    private Player player;
    private List<Card> cards;

    /**
     * Constructor sets and initializes fields
     * @param player - Sets the player field
     */

    public Deck(Player player) {
        this.player = player;
        cards = new ArrayList<Card>();
        factory= new CardFactory();
    }

    /**
     * Add 52 cards to List<Cards>
     */
    public void init(){
        for (int i=0; i<52; i++){
            cards.add(factory.createCard());
        }
    }

    /**
     * returns the object referenced by the player field
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * returns the field cards
     * @return List<Cards>
     */
    public List<Card> getCards() {
        return cards;
    }

    public void resetDeck(){

    }
}