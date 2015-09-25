package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Gun;
import io.zipcoder.casinorushhour2.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ghumphrey on 9/23/15.
 */
public class GunTest {
    Gun gun;

    @Before
    public void instantiateGun() {
        gun = new Gun(new Player("Gabe"));
        gun.init();
    }

    /**
     * Tests if the bulletChamber list indexes are set to boolean values, the first one being true, the rest false
     */
    @Test
    public void givenGunIsInstantiatedThenCheckChamberValues() {
        for (int i = 0; i < gun.getBulletChamber().size(); i++) {
            if (i == 0) {
                assertTrue("The values of the gun chamber should all be booleans, with the first value being true", gun.getBulletChamber().get(i));
            } else {
                assertEquals("The values of the gun chamber should all be booleans, with the first value being true", false, gun.getBulletChamber().get(i));
            }
        }
    }

    /**
     * Test if the bulletChamber List is shuffled
     */
    @Test
    public void givenGunIsInstantiatedThenSpinChamber() {
        Gun temp = new Gun(new Player("Gabe"));
        temp.init();

        boolean allSame = true;

        gun.spinChamber();

        for (int i = 0; i < gun.getBulletChamber().size(); i++) {
            if (gun.getBulletChamber().get(i) != temp.getBulletChamber().get(i)) {
                allSame = false;
                break;
            }
        }
        assertEquals("The value true should be in a different index after the spinChamber() method is called", false, allSame);
    }

    /**
     * Test if the shoot method returns a boolean value
     */
    @Test
    public void givenGunIsInstantiatedThenShoot() {
        assertEquals("Shoot method should return a boolean value, in this case true because the gun chamber has not been spun", true, gun.shoot());
    }

    @Test
    public void givenPlayerIsNotNullGetPlayer(){
        assertTrue("Test to make sure getPlayer returns a Player instance", gun.getPlayer() instanceof Player);
    }
}
