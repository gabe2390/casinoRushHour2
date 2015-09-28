package io.zipcoder.casinorushhour2;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is a Blackjack game for our Casino
 * Created by ghumphrey on 9/24/15.
 */
public class BlackJack implements CardGame {

    //declaring fields
    GameState state = GameState.NOTRUNNING;
    Player player;
    Deck deck;
    Map<String, List<Card>> hands;
    int currentPot;

    /**
     * Blackjack game constructor
     *
     * @param deck
     */
    public BlackJack(Deck deck) {
        this.deck = deck;
        deck.init();
        setAllCardPoints(deck);
        player = deck.getPlayer();
        hands = new HashMap<String, List<Card>>();
    }

    /**
     * Starts the game loop(s)
     */
    public void playGame() {
        Scanner key = new Scanner(System.in);
        boolean wantToHit = true;
        state = GameState.RUNNING;
        //game loop
        while (state == GameState.RUNNING) {
            //takes bet, deals cards
            currentPot = askToBet(key);
            dealCards();

            //player hit loop
            while (wantToHit && adjustAceTotal(hands.get(player.getName())) < 21) {

                wantToHit = askForHit(key);
                printPoints(hands.get(player.getName()));
            }

            //Prints current Dealer total
            dealerPrintPoints(DEALER.getHand());

            //DEALER hit loop.
            while (adjustAceTotal(DEALER.getHand()) <= adjustAceTotal(hands.get(player.getName())) && adjustAceTotal(hands.get(player.getName())) <= 21) {
                System.out.println("DEALER HIT!!!!\n");
                DEALER.addToHand(DEALER.dealCards(1, deck));
                dealerPrintPoints(DEALER.getHand());
                waitForDealerHit();
            }

            //compares totals, outputs new bank value
            checkForWinner();
            System.out.println("Your bank is now $" + player.getBank());
            state = GameState.NOTRUNNING;
        }

        //Asks if you would like to play again
        //Resets the hands and deck if you say yes
        System.out.println("Do you want to play again?");
        if (key.nextLine().equalsIgnoreCase("Y")) {
            giveCardsBack(DEALER.getHand());
            giveCardsBack(hands.get(player.getName()));
            DEALER.getHand().clear();
            hands.get(player.getName()).clear();
            currentPot = 0;
            playGame();
        } else {
            System.out.println("Thanks for playing Black Jack, come again soon and give me all of your money!");
        }
    }

    public void changeGameState() {
    }

    public void exitGame() {
    }

    /**
     * Compares scores and outputs the winner.
     * Updates the bank total.
     *
     * @return
     */
    public boolean checkForWinner() {
        List<Card> playerHand = hands.get(player.getName());
        List<Card> dealerHand = DEALER.getHand();
        int playerScore = adjustAceTotal(playerHand);
        int dealerScore = adjustAceTotal(dealerHand);


        System.out.println(player.getName() + "'s hand: " + playerHand + ". " + playerScore + " points.");
         if ((dealerScore < playerScore && playerScore <= 21)
                || (dealerScore > 21 && playerScore <= 21)) {

            player.addToBank(currentPot * 2);
            System.out.println(player.getName() + " WON $" + (currentPot * 2));
            return true;
        }
        else if ((dealerScore > playerScore && dealerScore <= 21) || playerScore > 21) {
            System.out.println("DEALER WON! YOU LOST $" + currentPot + " :(\n");
        }
        else {
            player.addToBank(currentPot);
            System.out.println("It was a tie! Here's your money back...");
        }
        return false;
    }

    /**
     * Sets the point values for each card in the deck
     *
     * @param deck
     */
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

    /**
     * Calculates raw score (Ace = 1 here)
     *
     * @param hand
     * @return Score
     */
    public int evaluatePoints(List<Card> hand) {
        int points = 0;
        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getValue();
        }
        return points;
    }

    /**
     * Checks for a hand less than 12, increases total by 10 if true
     *
     * @param i your raw score
     * @return increased score
     */
    public int alternateAcePoints(int i) {
        if (i <= 11) {
            return i + 10;
        } else {
            return i;
        }
    }

    /**
     * Puts cards back into the deck
     *
     * @param hand
     * @return
     */
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
     * @return bet
     */
    public int askToBet(Scanner key) {
        int bet;
        System.out.println("Your bank total is $" + player.getBank());
        System.out.println("Please enter bet amount:");
        bet = player.bet(Integer.parseInt(key.nextLine()));
        System.out.println("Your bank is now at $" + player.getBank());
        return bet;
    }

    /**
     * initial dealing of cards for the user, each computer player, and the dealer
     */
    public void dealCards() {

        DEALER.shuffleDeck(deck);
        hands.put(player.getName(), DEALER.dealCards(2, deck));
        DEALER.addToHand(DEALER.dealCards(2, deck));

        for (String name : hands.keySet()) {
            printPoints(hands.get(name));
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
            System.out.println("Your cards: " + cards + " You have " + evaluatePoints(cards)
                    + " or " + alternateAcePoints(evaluatePoints(cards)) + " points.");
        } else if (evaluatePoints(cards) > 21) {
            System.out.println("Your cards: " + cards + " BUST! with " + evaluatePoints(cards) + " points.");
        } else {
            System.out.println("Your cards: " + cards + " You have " + evaluatePoints(cards) + " points.");
        }
    }
    /**
     * Pause the flow of the game for 2 seconds to add to "realism"
     */
    public void waitForDealerHit() {
        long currentTime = System.nanoTime();
        while ((System.nanoTime() - currentTime) <= 1500000000) {
        }
    }
    /**
     * Prints the total of the Dealer's hand
     *
     * @param cards
     */
    private void dealerPrintPoints(List<Card> cards) {
        if (evaluatePoints(cards) != adjustAceTotal(cards)) {
            System.out.println("Dealer cards: " + cards + " Dealer has " + evaluatePoints(cards) + " or " + adjustAceTotal(cards) + ".");
        } else {
            System.out.println("Dealer cards: " + cards + " Dealer has " + adjustAceTotal(cards) + ".");
        }
    }

    /**
     * returns true if a player's hand contains an Ace
     *
     * @param cards
     * @return true if contains Ace
     */
      public boolean handContainsAce(List<Card> cards) {
        boolean hasAce = false;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals("Ace")) {
                hasAce = true;
            }
        }
        return hasAce;
    }


    /**
     * Changes Ace from 11 to 1 if you go over 21
     *
     * @param cards hand
     * @return total points
     */
    public int adjustAceTotal(List<Card> cards) {
        if (handContainsAce(cards) && alternateAcePoints(evaluatePoints(cards)) <= 21) {
            return alternateAcePoints(evaluatePoints(cards));
        } else {
            return evaluatePoints(cards);
        }
    }

    /**
     * Adds a single card if the user enters 'y', and returns a true if a card was added
     *
     * @param key
     * @return boolean
     */
    public boolean askForHit(Scanner key) {
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