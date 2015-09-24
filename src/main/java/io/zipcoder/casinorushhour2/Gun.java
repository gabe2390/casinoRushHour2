package io.zipcoder.casinorushhour2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This is the Gun class that will be used for every game of Russian Roulette and any other game requiring a gun
 */
public class Gun {
    private Player player;
    private ArrayList<Boolean> bulletChambers;

    /**
     * Constructor takes a Player object and sets it the player field. It also adds 6 Boolean values to the
     * bulletChamber List, one true, 5 false with the true value representing the single bullet
     * @param player
     */
    public Gun(Player player) {
        this.player = player;
        bulletChambers = new ArrayList<Boolean>();

        for (int i = 0; i < 6; i++) {
            if (i == 0) bulletChambers.add(true);
            else bulletChambers.add(false);
        }
    }

    /**
     *
     * @return ArrayList<Boolean> - returns the bulletChambers List
     */
    public ArrayList<Boolean> getBulletChambers() {
        return bulletChambers;
    }

    /**
     * shuffles the values in the bulletChambers List
     */
    public void spinChamber() {
        Collections.shuffle(bulletChambers);
    }

    /**
     *
     * @return returns the value of the first index in the bulletChambers List
     */
    public Boolean shoot() {
        return bulletChambers.get(0);
    }

    /**
     *
     * @return returns the player object that was set during construction of the Gun
     */
    public Player getPlayer() {
        return player;
    }
}