package io.zipcoder.casinorushhour2;

/**
 * Created by rsparks on 9/23/15.
 * Player constructor with functions to getName, getBank, place a bet, and add winnings to the Player's bank
 */
public class Player {

    private String name;
    private int bank;


    /**
     * Player constructor that takes in a name and initialized bank to 3000
     * @param name
     */
    public Player(String name){
        this.name = name;
        bank = 3000;
    }

    /**
     * Gets name of player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Bet function, subtracts bet from bank and returns bet amount
     * @param b -- bet amount
     * @return bet amount
     */
    public int bet(int b){
        bank -= b;
        return b;
    }

    /**
     * Adds pot to bank total
     * @param pot
     */
    public void addToBank(int pot){
        bank += pot;
    }

    /**
     * Gets your bank total
     * @return bank total
     */
    public int getBank(){
        return bank;
    }

}
