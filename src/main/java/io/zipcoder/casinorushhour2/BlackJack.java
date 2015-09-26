package io.zipcoder.casinorushhour2;

import java.time.Year;
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
        player = deck.getPlayer();
        hands = new HashMap<String, List<Card>>();
    }

    public void playGame() {
        Scanner key = new Scanner(System.in);
        boolean wantToHit = true;
        state = GameState.RUNNING;
        int currentPot = 0;

        while (state == GameState.RUNNING) {

            DEALER.shuffleDeck(deck);
            hands.put(player.getName(), DEALER.dealCards(2, deck));
            System.out.println("Your bank total is " + player.getBank() + " dollars. ");
            System.out.println("Please enter bet amount as an Integer:");
            currentPot += player.bet(Integer.parseInt(key.nextLine()));


            DEALER.addToHand(DEALER.dealCards(2, deck));

            System.out.println("Dealer has a " + DEALER.getHand().size() + " cards. " + DEALER.getHand().get(0) + " face up ");


            while (wantToHit && evaluatePoints(hands.get(player.getName())) < 21) {
                System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName())) + " points.");
                System.out.println("Would you like to hit?");

                if (key.nextLine().equalsIgnoreCase("Y")) {
                    List<Card> newHand = hands.get(player.getName());
                    newHand.addAll(DEALER.dealCards(1, deck));
                    hands.put(player.getName(), newHand);
                } else {
                    wantToHit = false;
                }

            }
            /**
             * Add case for going over
             */
            System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName())) + " points.");
            System.out.println("Dealer has " + DEALER.getHand());


            while (evaluatePoints(DEALER.getHand()) < (evaluatePoints(hands.get(player.getName()))) && evaluatePoints(hands.get(player.getName())) <= 21) {
                System.out.println("DEALER HIT!!!!\n");
                DEALER.addToHand(DEALER.dealCards(1, deck));
                System.out.println("Dealer cards: " + DEALER.getHand() + " Dealer has " + evaluatePoints(DEALER.getHand()));
            }


            if ((evaluatePoints(DEALER.getHand()) > evaluatePoints(hands.get(player.getName())) && evaluatePoints(DEALER.getHand()) <= 21) || (evaluatePoints(hands.get(player.getName())) > 21)) {
                System.out.println("DEALER WON! YOU LOST " + currentPot + " DOLLARS\n");
            } else if (evaluatePoints(DEALER.getHand()) < evaluatePoints(hands.get(player.getName())) && evaluatePoints(hands.get(player.getName())) <= 21 || evaluatePoints((DEALER.getHand())) > 21) {

                player.addToBank(currentPot * 2);
                System.out.println(player.getName() + " WON " + (currentPot * 2));
            } else {
                player.addToBank(currentPot);
                System.out.println("It was a tie! Here's your money back... scrub.");
            }


            System.out.println("Your bank is now " + player.getBank());


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


        if (key.nextLine().equalsIgnoreCase("Y")) {

            giveCardsBack(DEALER.getHand());
            giveCardsBack(hands.get(player.getName()));

            DEALER.getHand().clear();
            hands.get(player.getName()).clear();

            playGame();
        } else {
            System.out.println("Thanks for playing Black Jack, come again soon and give me all of your money!");
        }
    }

    public void changeGameState() {

    }

    public void exitGame() {

    }

    public boolean checkForWinner() {
        return true;
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
        if (i < 11) {
            return 1 + 10;
        } else {
            return i;
        }
    }

    public boolean giveCardsBack(List<Card> hand) {
        int i = deck.getCards().size();
        deck.getCards().addAll(hand);
        return deck.getCards().size() == i + hand.size();
    }

    public static void main(String[] args) {
        BlackJack j = new BlackJack(new Deck(new Player("Gabe")));
        j.playGame();
    }
}
