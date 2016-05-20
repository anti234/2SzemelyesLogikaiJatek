/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Jatek.Jatek;
import Jatek.Korong;
import Jatek.Szin;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anti
 */
public class JatekTest {

    @Test
    public void getKorongokTest() {
        Jatek jatek = new Jatek();
        List<Korong> korongok = Arrays.asList(
                new Korong(0, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Kek),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek)
        );
        int[][] kekKoordinatak = jatek.getKekKezdoKoordinatak();
        assertEquals(korongok, jatek.getKorongok(kekKoordinatak, Szin.Kek));
    }
    
    @Test
    public void KezdoKorongokTest() {
        Jatek jatek = new Jatek();
        List<Korong> korongok = Arrays.asList(
                new Korong(0, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Kek),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek),
                new Korong(3, 0, Szin.Sarga),
                new Korong(3, 4, Szin.Sarga),
                new Korong(4, 0, Szin.Sarga),
                new Korong(4, 1, Szin.Sarga),
                new Korong(4, 2, Szin.Sarga),
                new Korong(4, 3, Szin.Sarga),
                new Korong(4, 4, Szin.Sarga)
        );
        assertEquals(korongok, jatek.KezdoKorongok());
    }
}
