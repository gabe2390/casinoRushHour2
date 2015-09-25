package io.zipcoder.casinorushhour2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ghumphrey on 9/23/15.
 * This is the class for a Gun that will be used in RussianRoulette and future games involving a Gun object
 */
public class Gun {
    private Player player;
    private ArrayList<Boolean> bulletChambers;

    /**
     * Constructor -  sets the player field to the Player that is passed in.
     * Also initializes bulletChambers List
     * @param player
     */
    public Gun(Player player) {
        this.player = player;
        bulletChambers= new ArrayList<Boolean>();
    }

    /**
     * Initializes the gun by adding boolean values to the bulletChamber,
     * the first of which is true- representing a single bullet
     */
    public void init() {
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                bulletChambers.add(true);
            } else {
                bulletChambers.add(false);
            }
        }
    }

    /**
     * Returns the bulletChamber List
     * @return List
     */
    public List<Boolean> getBulletChamber() {
        return bulletChambers;
    }

    /**
     * Shuffles the values in the bulletChamber
     */
    public void spinChamber() {
        Collections.shuffle(bulletChambers);
    }

    /**
     * Returns the first value in the bulletChamber List
     * @return Boolean
     */
    public Boolean shoot() {
        return bulletChambers.get(0);
    }

    /**
     * returns the Player that is passed in at instantiation
     * @return Player
     */
    public Player getPlayer() {
        return null;
    }
}