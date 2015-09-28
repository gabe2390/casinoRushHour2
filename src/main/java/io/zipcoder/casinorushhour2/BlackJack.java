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
    int currentPot;

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


        while (state == GameState.RUNNING) {

            currentPot = askToBet(key);
            dealCards();

            //player hit loop
            while (wantToHit && adjustAceTotal(hands.get(player.getName())) < 21) {
                wantToHit= askForHit(key);
                printPoints(hands.get(player.getName()));
            }

            //Prints current Dealer total
            dealerPrintPoints(DEALER.getHand());

            /**
             * Dealer HIT loop.
             */
            while (adjustAceTotal(DEALER.getHand()) <= adjustAceTotal(hands.get(player.getName())) && adjustAceTotal(hands.get(player.getName())) <= 21) {
                System.out.println("DEALER HIT!!!!\n");
                DEALER.addToHand(DEALER.dealCards(1, deck));
               dealerPrintPoints(DEALER.getHand());
            }

            checkForWinner();
            System.out.println("Your bank is now " + player.getBank());
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

        System.out.println(player.getName() + "'s hand: " + hands.get(player.getName())+ ". " + adjustAceTotal(hands.get(player.getName())) + " points.");
        if ((adjustAceTotal(DEALER.getHand()) > adjustAceTotal(hands.get(player.getName())) && adjustAceTotal(DEALER.getHand()) <= 21) || (adjustAceTotal(hands.get(player.getName())) > 21)) {
            System.out.println("DEALER WON! YOU LOST " + currentPot + " DOLLARS\n");
            return true;
        } else if ((adjustAceTotal(DEALER.getHand()) < adjustAceTotal(hands.get(player.getName())) && adjustAceTotal(hands.get(player.getName())) <= 21)
                || adjustAceTotal((DEALER.getHand())) > 21 && adjustAceTotal(hands.get(player.getName())) <= 21) {

            player.addToBank(currentPot * 2);
            System.out.println(player.getName() + " WON " + (currentPot * 2));
        } else {
            player.addToBank(currentPot);
            System.out.println("It was a tie! Here's your money back... scrub.");
        }
        currentPot = 0;
        return false;
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

    private int alternateAcePoints(int i) {
        if (i <= 11) {
            return i + 10;
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

    /**
     * asks player to bet, removes the betting amount specified, and then returns that amount to be added to the current pot
     *
     * @param key
     * @return
     */
    private int askToBet(Scanner key) {
        int bet;

        System.out.println("Your bank total is $" + player.getBank() + " dollars. ");
        System.out.println("Please enter bet amount as an Integer:");
        bet = player.bet(Integer.parseInt(key.nextLine()));
        System.out.println("Your bank is now at $" + player.getBank());
        return bet;
    }

    /**
     * initial dealing of cards for the user, each computer player, and the dealer
     */
    private void dealCards() {

        DEALER.shuffleDeck(deck);
        hands.put(player.getName(), DEALER.dealCards(2, deck));
        DEALER.addToHand(DEALER.dealCards(2, deck));

        for (String name : hands.keySet()) {
            if (adjustAceTotal(hands.get(name)) != evaluatePoints(hands.get(name))) {


                System.out.println("Name: " + name + ".\nHand: " + hands.get(name) + "\nYou have " +evaluatePoints(hands.get(name)) + " or " + adjustAceTotal(hands.get(name)) + " points.");
            }
            else{
                System.out.println("Name: " + name + ".\nHand: " + hands.get(name) + "\nYou have " + adjustAceTotal(hands.get(name)) + " points.");
            }
        }


        System.out.println("Dealer's hand: " + DEALER.getHand().get(0) + " face up, and one card face down");

    }

    /**
     * prints all the cards in a player's or dealer's hand, as well as their points
     *
     * @param cards
     */
    private void printPoints(List<Card> cards) {
        if (handContainsAce(cards) && evaluatePoints(cards) <= 11) {
            System.out.println("Your cards: " + cards + " \nYou have " + evaluatePoints(cards)
                    + " or " + alternateAcePoints(evaluatePoints(cards)) + " points.");
        }
        else if (evaluatePoints(cards) > 21) {
            System.out.println("Your cards: " + cards + " \nBUST! with " + evaluatePoints(cards) + " points.");
        }
        else {
            System.out.println("Your cards: " + cards + " \nYou have " + evaluatePoints(cards) + " points.");
        }
    }

    private void dealerPrintPoints(List<Card> cards){
        if(evaluatePoints(cards) != adjustAceTotal(cards)){
            System.out.println("Dealer cards: " + cards + " Dealer has " + evaluatePoints(cards) + " or " + adjustAceTotal(cards)+".");
        }
        else{System.out.println("Dealer cards: " + cards + " Dealer has " + adjustAceTotal(cards)+".");}
    }
    /**
     * returns true if a player's hand contains an Ace
     *
     * @param cards
     * @return
     */
    private boolean handContainsAce(List<Card> cards) {
        boolean hasAce = false;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals("Ace")) {
                hasAce = true;
            }
        }
        return hasAce;
    }

    private int adjustAceTotal(List<Card> cards){
        if (handContainsAce(cards)&& alternateAcePoints(evaluatePoints(cards))<=21){
            return alternateAcePoints(evaluatePoints(cards));
        }
        else{
            return evaluatePoints(cards);
        }
    }

    /**
     * Adds a single card if the user enters 'y', and returns a true if a card was added
     *
     * @param key
     * @return boolean
     */
    private boolean askForHit(Scanner key) {
        System.out.println("Would you like to hit?");


        if (key.nextLine().equalsIgnoreCase("Y")) {
            List<Card> newHand = hands.get(player.getName());
            newHand.addAll(DEALER.dealCards(1, deck));
            hands.put(player.getName(), newHand);
            return true;
        }
        return false;
    }

}