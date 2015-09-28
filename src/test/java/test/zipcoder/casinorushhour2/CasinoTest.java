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
    public void testSetCurrentGame() {
        casino.setCurrentGame(1);
        assertEquals("Entering a 1 as the parameter should make the current game poker", "Blackjack", casino.getCurrentGame().name());
        casino.setCurrentGame(2);
        assertEquals("Entering a 2 as the parameter should make the current game blackjack", "Poker", casino.getCurrentGame().name());
        casino.setCurrentGame(3);
        assertEquals("Entering a 3 as the parameter should make the current game poker", "Russian_Roulette", casino.getCurrentGame().name());
        casino.setCurrentGame(4);
        assertEquals("Entering a 4 as the parameter should make the current game poker", "Leave_Casino", casino.getCurrentGame().name());
        casino.setCurrentGame(5280);
        assertEquals("Entering any other parameter as the parameter should make the current game Russian_Roulette", "Russian_Roulette", casino.getCurrentGame().name());
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