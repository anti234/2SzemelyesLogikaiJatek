/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Jatek.Korong;
import Jatek.Szin;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorongTest {

    @Test
    public void ErvenyesXTest() {
        for (int i = 0; i < 5; i++) {
            assertTrue((new Korong(i, 0).ErvenyesX()));
        }
        assertFalse((new Korong(-1, 0).ErvenyesX()));
        assertFalse((new Korong(5, 0).ErvenyesX()));
    }

    @Test
    public void ErvenyesYTest() {
        for (int i = 0; i < 5; i++) {
            assertTrue((new Korong(0, i).ErvenyesY()));
        }
        assertFalse((new Korong(0, -1).ErvenyesY()));
        assertFalse((new Korong(0, 5).ErvenyesY()));
    }

    @Test
    public void EqualsTest() {
        Korong korong1 = new Korong(1, 1, Szin.Kek);
        assertFalse(korong1.equals(new Korong(2, 1, Szin.Kek)));
        assertFalse(korong1.equals(new Korong(1, 2, Szin.Kek)));
        assertFalse(korong1.equals(new Korong(2, 1, Szin.Sarga)));
        assertTrue(korong1.equals(new Korong(1, 1, Szin.Kek)));
    }

    @Test
    public void EgyenloKoordinatakTest() {
        Korong korong1 = new Korong(1, 1, Szin.Kek);
        assertFalse(korong1.EgyenloKoordinatak(new Korong(2, 1, Szin.Kek)));
        assertFalse(korong1.EgyenloKoordinatak(new Korong(1, 2, Szin.Kek)));
        assertTrue(korong1.EgyenloKoordinatak(new Korong(1, 1, Szin.Sarga)));
        assertTrue(korong1.EgyenloKoordinatak(new Korong(1, 1, Szin.Kek)));
    }
}
