
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    public void setUp() {
        joukko = new IntJoukko(4, 2);

        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    @Test(expected =  IndexOutOfBoundsException.class)
    public void testNegatiivisellaKasvatuskoolla(){
        new IntJoukko(1,-2);
    }
    @Test(expected =  IndexOutOfBoundsException.class)
    public void testNegatiivisellaKasvatuskoollaJaNegatiivisellaKapasiteetilla(){
        new IntJoukko(-1,-2);
    }
}
