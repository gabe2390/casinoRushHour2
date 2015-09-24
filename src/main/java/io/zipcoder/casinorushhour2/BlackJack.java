package io.zipcoder.casinorushhour2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ghumphrey on 9/24/15.
 */
public class BlackJack implements CardGame {
    GameState state = GameState.NOTRUNNING;
    Player player;
    Deck deck;
    Map<String, List<Card>> hands;

    public BlackJack(Deck deck) {
        this.deck = deck;
        deck.init();
        setAllCardPoints(deck);
        System.out.println(DEALER.hand.add(new Card()));
        player = deck.getPlayer();
        hands = new HashMap<String, List<Card>>();
    }

    public void playGame() {
        Scanner key = new Scanner(System.in);
        boolean wantToHit = true;
        state = GameState.RUNNING;

        while (state == GameState.RUNNING) {

            DEALER.shuffleDeck(deck);
            hands.put(player.getName(), DEALER.dealCards(2, deck));

            while (wantToHit) {
                System.out.println("Your cards: " + hands.get(player.getName()) + ". You have " + evaluatePoints(hands.get(player.getName())) + " points.");
                System.out.println("Would you like to hit?");

                if (key.nextLine().equals("Y")) {
                    List<Card> newHand = hands.get(player.getName());
                    newHand.addAll(dealer.dealCards(1, deck));
                    hands.put(player.getName(), newHand);
                } else {
                    wantToHit = false;
                }
            }
            //cards dealt
            //bet
            //askForHit
            //done hitting
            //dealer deals own cards
            //dealer hits if necessary
            //determine winner
            //if player is winner add to their bank
            state = GameState.NOTRUNNING;
        }

        System.out.println("Do you want to play again?");
        if (key.nextLine().equals("Y")) {
            playGame();
        } else {
            System.out.println("Thanks for playing Black Jack, come again soon and give me all of your money!");
        }
    }

    public void changeGameState() {

    }

    public void exitGame() {

    }

    public void checkForWinner() {

    }

    public void setAllCardPoints(Deck deck) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getName().equals("Ace")) {
                deck.getCards().get(i).setValue(1);
            } else if (deck.getCards().get(i).getName().equals("Jack") || deck.getCards().get(i).getName().equals("Queen") || deck.getCards().get(i).getName().equals("King")) {
                deck.getCards().get(i).setValue(10);
            } else {
                deck.getCards().get(i).setValue(Integer.valueOf(deck.getCards().get(i).getName()));
            }
        }
    }

    public int evaluatePoints(List<Card> hand) {
        int points = 0;

        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getValue();
        }
        return points;
    }

    public int alternateAcePoints(int i) {
        return
    }

    public static void main(String[] args) {
        BlackJack j = new BlackJack(new Deck(new Player("Gabe")));
        j.playGame();
    }
}
