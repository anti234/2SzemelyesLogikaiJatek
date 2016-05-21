package Tests;

import jatek.Korong;
import jatek.Szin;

import org.junit.Test;

import static org.junit.Assert.*;

public class KorongTest {

    @Test
    public void ervenyesXTest() {
        for (int i = 0; i < 5; i++) {
            assertTrue((new Korong(i, 0).ervenyesX()));
        }
        assertFalse((new Korong(-1, 0).ervenyesX()));
        assertFalse((new Korong(5, 0).ervenyesX()));
    }

    @Test
    public void ervenyesYTest() {
        for (int i = 0; i < 5; i++) {
            assertTrue((new Korong(0, i).ervenyesY()));
        }
        assertFalse((new Korong(0, -1).ervenyesY()));
        assertFalse((new Korong(0, 5).ervenyesY()));
    }

    @Test
    public void equalsTest() {
        Korong korong1 = new Korong(1, 1, Szin.Kek);
        assertFalse(korong1.equals(new Korong(2, 1, Szin.Kek)));
        assertFalse(korong1.equals(new Korong(1, 2, Szin.Kek)));
        assertFalse(korong1.equals(new Korong(2, 1, Szin.Sarga)));
        assertTrue(korong1.equals(new Korong(1, 1, Szin.Kek)));
        assertFalse(korong1.equals(null));
    }

    @Test
    public void egyenloKoordinatakTest() {
        Korong korong1 = new Korong(1, 1, Szin.Kek);
        assertFalse(korong1.egyenloKoordinatak(new Korong(2, 1, Szin.Kek)));
        assertFalse(korong1.egyenloKoordinatak(new Korong(1, 2, Szin.Kek)));
        assertTrue(korong1.egyenloKoordinatak(new Korong(1, 1, Szin.Sarga)));
        assertTrue(korong1.egyenloKoordinatak(new Korong(1, 1, Szin.Kek)));
        assertFalse(korong1.equals(null));
    }
}
