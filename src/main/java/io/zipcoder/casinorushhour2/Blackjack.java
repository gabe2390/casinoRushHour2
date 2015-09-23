package io.zipcoder.casinorushhour2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsparks on 9/22/15.
 */
public class Blackjack implements CardGame {


    Player player;
    state;
    Dealer DEALER = new Dealer();
    int points;

    List<Card> hand = new ArrayList<Card>(2);
    public  void a(){

    }
    public Blackjack(Deck d) {
        player = d.getPlayer();
    }


    public void playGame() {

    }

    public void exitGame() {

    }

    public void checkForWinner() {

    }


    public int evaluatePoints(ArrayList hand) {
        int points = 0;
        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i);
        }
        return points;
        //different method for dealer's total?
    }

    public void changeGameState() {
        if (state == GameState.NotRunning) {
            state = GameState.Running;
        } else {
            state = GameState.NotRunning;
        }

    }

    public void addToHand(Card) {


    }

    public Card askForHit() {


    }

}
