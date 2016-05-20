package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Jatek.Jatek;
import Jatek.Korong;
import Jatek.Lepesek;
import Jatek.Szin;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class LepesekTest {

    @Test
    public void LepesTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        List<Korong> korongok = lepesek.getKorongok();
        if (lepesek.Lepes(korongok.get(0), new Korong(1, 1)) > 0) {
            korongok.set(0, new Korong(1, 1, Szin.Kek));
            assertEquals(korongok, lepesek.getKorongok());
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void ElerhatoRacspontTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.ElerhetoRacsPont(new Korong(1, 1), new Korong(2, 2)));
        assertTrue(lepesek.ElerhetoRacsPont(new Korong(5, 4), new Korong(4, 3)));
        assertTrue(lepesek.ElerhetoRacsPont(new Korong(4, 3), new Korong(5, 2)));
        assertTrue(lepesek.ElerhetoRacsPont(new Korong(2, 3), new Korong(1, 4)));
        assertFalse(lepesek.ElerhetoRacsPont(new Korong(2, 3), new Korong(3, 3)));
        assertFalse(lepesek.ElerhetoRacsPont(new Korong(2, 3), new Korong(2, 2)));
        assertFalse(lepesek.ElerhetoRacsPont(new Korong(1, 1), new Korong(2, 4)));
    }

    @Test
    public void SzabadRacspontTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.SzabadRacspont(new Korong(1, 1, Szin.Kek)));
        assertTrue(lepesek.SzabadRacspont(new Korong(1, 1, Szin.Sarga)));
        assertFalse(lepesek.SzabadRacspont(new Korong(0, 0, Szin.Sarga)));
    }

    @Test
    public void KovetkezinTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.Kovetkezik(new Korong(1, 1, Szin.Kek)));
        assertFalse(lepesek.Kovetkezik(new Korong(1, 1, Szin.Sarga)));
    }

    @Test
    public void NyertTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        lepesek.setKekNyeroKorongok(Arrays.asList(
                new Korong(1, 4, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 2, Szin.Kek),
                new Korong(0, 0, Szin.Kek)
        ));
        assertTrue(lepesek.Nyert());
        lepesek.setKekNyeroKorongok(Arrays.asList(
                new Korong(0, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Sarga),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek)
        ));
        assertFalse(lepesek.Nyert());

        lepesek.setKekNyeroKorongok(Arrays.asList(
                new Korong(2, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Kek),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek)
        ));
        assertFalse(lepesek.Nyert());
    }
    
    @Test
    public void getMasikSzinTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertEquals(Szin.Kek, lepesek.getMasikSzin(Szin.Sarga));
        assertEquals(Szin.Sarga, lepesek.getMasikSzin(Szin.Kek));
    }
}
