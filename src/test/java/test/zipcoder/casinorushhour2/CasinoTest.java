package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Casino;
import io.zipcoder.casinorushhour2.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ghumphrey on 9/25/15.
 */
public class CasinoTest {
    Casino casino;
    Player player;

    @Before
    public void setupCasino() {
        casino = new Casino();
        casino.setPlayer(new Player("Gabe"));
    }

    @Test
    public void testSetPlayer() {
        casino.setPlayer(player);

    }

    @Test
    public void testGetPlayer() {
        assertTrue("Get player should return a Player instance", casino.getPlayer() instanceof Player);
    }
}