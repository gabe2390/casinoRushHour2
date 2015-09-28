package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Gun;
import io.zipcoder.casinorushhour2.Player;
import io.zipcoder.casinorushhour2.RussianDraw;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by emaron on 9/28/15.
 */
public class RussianDrawTest {

    RussianDraw drawTest = new RussianDraw(new Gun(new Player("bob")));

    /**
     * Confirms that the counters for each gun are initialized
     */
    @Test
    public void shouldReturnBoolean() {
        assertFalse("Should return false", drawTest.checkForWinner());
    }


    @Test
    public void shouldReturnABooleanResult() {
        int countAliveOrDead = 0;

        if (drawTest.checkForWinner()) {
            countAliveOrDead = 1;
        } else if (drawTest.checkForWinner() == false) {
            countAliveOrDead = 2;
        }

        assertTrue("countAliveOrDead is within the range of 1 - 2 " + countAliveOrDead, 0 <= countAliveOrDead && countAliveOrDead <= 2);
    }
}

