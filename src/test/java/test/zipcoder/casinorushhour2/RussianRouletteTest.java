package test.zipcoder.casinorushhour2;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.zipcoder.casinorushhour2.Gun;
import io.zipcoder.casinorushhour2.Player;
import io.zipcoder.casinorushhour2.RussianRoulette;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ghumphrey on 9/25/15.
 * This is the test class for the methods in the RussianRoulette class
 */
public class RussianRouletteTest {

    RussianRoulette game;
    Gun gun;
    Player player;

    /**
     * Creating a player with a negative balance is necessary to ensure the game flows correctly
     */
    @Before
    public void createPlayerWithNegativeBalance(){
        player= new Player("Gabe");
        player.addToBank(-6000);
        System.out.println(player.getBank());
    }

    /**
     * Self explanatory
     */
    @Before
    public void instantiateRussianRoulette(){
        gun = new Gun(player);
        game=new RussianRoulette(gun);
    }

    /**
     * The gun has not been spun yet, and the check winner just checks if the the gun.shoot() method returns a bullet (true)
     * Given that the gun has not been spun, checkForWinner() should return true;
     */
    @Test
    public void testCheckForWinner(){
        assertTrue("If the chamber has not been spun, should return true", game.checkForWinner());
  }
}
