package test.zipcoder.casinorushhour2;

import io.zipcoder.casinorushhour2.Gun;
import io.zipcoder.casinorushhour2.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ghumphrey on 9/23/15.
 */
public class GunTest {
    Gun gun;

    @Test
    public void givenGunIsInstantiatedThenCheckChamberValues() {
        gun = new Gun(new Player());

        for (int i = 0; i < gun.getBulletChamber().length; i++) {
            if (i == 0) {
                assertEquals("The values of the gun chamber should all be booleans, with the first value being true", true, gun.getBulletChamber()[i]);
            } else {
                assertEquals("The values of the gun chamber should all be booleans, with the first value being true", false, gun.getBulletChamber()[i]);
            }
        }
    }

    @Test
    public void givenGunIsInstantiatedThenSpinChamber() {
        gun = new Gun(new Player());
        Gun temp= new Gun(new Player());
        boolean allSame= true;

        gun.spinChamber();

        for(int i=0; i<gun.getBulletChamber().length; i++){
            if(gun.getBulletChamber()[i] != temp.getBulletChamber()[i]){
                allSame=false;
                break;
            }
        }
        assertEquals("The value true should be in a different index after the spinChamber() method is called", false, allSame);
    }

    @Test
    public void givenGunIsInstantiatedThenShoot() {
        gun = new Gun(new Player());
        assertEquals("");
    }

    @Test
    public void givenShootHasBeenCalledThenTestReset() {
        gun = new Gun(new Player());

    }
}
