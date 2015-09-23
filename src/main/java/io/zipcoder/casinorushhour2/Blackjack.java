package io.zipcoder.casinorushhour2;

import java.util.List;

/**
 * Created by rsparks on 9/22/15.
 */
public class Blackjack implements CardGame{


    Player player;
    state;
    Dealer DEALER = new Dealer();
    int points;


    public BlackJack(Deck d){
        player= d.getPlayer();
    }


    public void playGame() {

    }

    public void exitGame() {

    }

    public void checkForWinner() {

    }

    /**
     * adds up the points of your current hand
     * @return int
     */
    public int evaluatePoints(List<hand>) {
        int points = 0;
        for(int i = 0; i<hand.size(); i++){
            points += hand.get(i);
        }
        return points;
        //different method for dealer's total?
    }

    public void changeGameState() {
        if(state == GameState.NotStarted){
            state = GameState.Running;
        }
        else if(state == GameState.Running){
            state = GameState.finished;
        }
        else{
            state = GameState.NotStarted;
        }

    }
    public void addToHand(Card){

    }
    public Card askForHit(){

        DEALER
    }

}
