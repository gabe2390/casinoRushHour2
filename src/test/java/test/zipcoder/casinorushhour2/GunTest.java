package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Gun;
import io.zipcoder.casinorushhour2.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * Test class for the Gun class
 */
public class GunTest {
    Gun gun;

    /**
     * Create Gun instance before any tests are run
     */
    @Before
    public void beforeTestsInstantiateGun(){
        gun= new Gun(new Player());
    }

    /**
     * Checks that all values in the bulletChamber list are of type Boolean, with the first value being true and the
     * rest being false
     */
    @Test
    public void givenGunIsInstantiatedThenCheckChamberValues() {
        for (int i = 0; i < gun.getBulletChambers().size(); i++) {
            if (i == 0) {
                assertEquals("The values of the gun chamber should all be booleans, with the first value being true", true, gun.getBulletChambers().get(i));
            } else {
                assertEquals("The values of the gun chamber should all be booleans, with the first value being true", false, gun.getBulletChambers().get(i));
            }
        }
    }

    /**
     * Check to make sure that the spinChamber method shuffles the values in the bulletChamber List
     */
    @Test
    public void givenGunIsInstantiatedThenSpinChamber() {
        Gun temp= new Gun(new Player());
        boolean allSame= true;

        gun.spinChamber();

        for(int i=0; i<gun.getBulletChambers().size(); i++){
            if(gun.getBulletChambers().get(i) != temp.getBulletChambers().get(i)){
                allSame=false;
                break;
            }
        }

        assertEquals("The value true should be in a different index after the spinChamber() method is called", false, allSame);
    }

    /**
     * Test that the shoot method returns the value in index 0 of the bulletChamber list- first value should be true because
     * spinChamber() hasn't been called, then first valu shoul be randomized based off of spinChamber() method
     */
    @Test
    public void givenGunIsInstantiatedThenShoot() {
        assertEquals("Shoot method should return first boolean value in the bulletChambers Lists",gun.getBulletChambers().get(0),gun.shoot());
        gun.spinChamber();
        assertEquals("Shoot method should return first boolean value in the bulletChambers Lists", gun.getBulletChambers().get(0), gun.shoot());
    }

    /**
     * Test that the getPlayer() method returns the field player in the Gun class- an object of type Player
     */
    @Test
    public void givenGunIsInstantiatedGetPlayer(){
        assertEquals("Test to make sure getPlayer() returns a player instance that is its field",true, gun.getPlayer() instanceof Player);
    }
}