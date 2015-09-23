package io.zipcoder.casinorushhour2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ghumphrey on 9/23/15.
 */
public class Gun {
    private Player player;
    private boolean[] bulletChambers;

    public Gun(Player player) {
        this.player = player;
        bulletChambers = new boolean[]{true,false,false,false,false,false};
    }
    public boolean[] getBulletChamber(){
        return bulletChambers;
    }
    public void spinChamber() {
        Collections.shuffle(Arrays.asList(bulletChambers));
    }

    public Boolean shoot() {
        return null;
    }


    public Player getPlayer() {
        return null;
    }
}
