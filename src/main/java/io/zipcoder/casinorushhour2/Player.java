package io.zipcoder.casinorushhour2;

/**
 * Created by ghumphrey on 9/23/15.
 */
public class Player {
    private int bank=-1300;

    public void addToBank(int i) {
        bank+=i;
    }

    public int getBank() {
        return bank;
    }
}
