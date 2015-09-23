package io.zipcoder.casinorushhour2;

/**
 * Created by rsparks on 9/23/15.
 */
public class Player {

    private String name;
    private int bank;


    /**
     * Player constructor
     * @param name
     */
    public Player(String name){
        this.name = name;
        bank = 3000;
    }

    /**
     * Name getter
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Bet function
     * @param b
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
