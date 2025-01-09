/**
 * 
 */
package fr.univ_lyon1.info.m1.poneymon_fx.Model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de PoneyModel
 *
 */
public class PoneyModelTest {

    private PoneyModel poney;

    /**
     * Test du constructeur
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        poney = new PoneyModel();
    }

    /**
     * Test que la vitesse est bornée entre 0 et 1.
     *
     */
    @Test
    public void testRandomSpeed() {
        poney.randomSpeed();
        assertTrue("La vitesse du poney doit être comprise entre 0 et 1",
                (0.0 < poney.speed) && (poney.speed < 1.0));
    }

    /**
     * Test que le poney ne sort pas du terrain.
     *
     */
    @Test
    public void testStep() {
        poney.step();
        assertTrue("Le poney ne doit pas sortir du terrain", poney.progress < 111.0);
    }
}
