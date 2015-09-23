package test.io.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rsparks on 9/23/15.
 */
public class PlayerTest {

    Player player1 = new Player ("Rick");

    //Tests getName
    @Test

    public void testGetName(){
        player1 = new Player("Rick");
        assertEquals("Should return whatever is in the name field","Rick",player1.getName());
    }


    //Tests bet
    @Test

    public void testBet(){
        player1 = new Player("Rick");
        assertEquals("Should return bet value", 2500, player1.bet(2500));
    }

    //Tests getBank
    @Test

    public void testGetBank(){
        player1 = new Player("Rick");
        assertEquals("Should return bank total", 3000, player1.getBank());
    }


}
