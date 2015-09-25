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
        System.out.println(player.getBank());
    }

    @Before
    public void instantiateRussianRoulette(){
        gun = new Gun(player);
        game=new RussianRoulette(gun);
    }

    @Test
    public void testCheckForWinner(){
        assertTrue("If the chamber has not been spun, should return true", game.checkForWinner());
  }

}
