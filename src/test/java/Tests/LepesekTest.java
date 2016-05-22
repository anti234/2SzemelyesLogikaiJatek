package Tests;

import jatek.Jatek;
import jatek.Korong;
import jatek.Lepesek;
import jatek.Szin;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class LepesekTest {

    @Test
    public void lepesTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        List<Korong> korongok = lepesek.getKorongok();
        if (lepesek.lepes(korongok.get(0), new Korong(1, 1)) > 0) {
            korongok.set(0, new Korong(1, 1, Szin.Kek));
            assertEquals(korongok, lepesek.getKorongok());
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void elerhatoRacspontTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.elerhetoRacsPont(new Korong(1, 1), new Korong(2, 2)));
        assertTrue(lepesek.elerhetoRacsPont(new Korong(5, 4), new Korong(4, 3)));
        assertTrue(lepesek.elerhetoRacsPont(new Korong(4, 3), new Korong(5, 2)));
        assertTrue(lepesek.elerhetoRacsPont(new Korong(2, 3), new Korong(1, 4)));
        assertFalse(lepesek.elerhetoRacsPont(new Korong(2, 3), new Korong(3, 3)));
        assertFalse(lepesek.elerhetoRacsPont(new Korong(2, 3), new Korong(2, 2)));
        assertFalse(lepesek.elerhetoRacsPont(new Korong(1, 1), new Korong(2, 4)));
    }

    @Test
    public void szabadRacspontTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.szabadRacspont(new Korong(1, 1, Szin.Kek)));
        assertTrue(lepesek.szabadRacspont(new Korong(1, 1, Szin.Sarga)));
        assertFalse(lepesek.szabadRacspont(new Korong(0, 0, Szin.Sarga)));
    }

    @Test
    public void kovetkezinTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertTrue(lepesek.kovetkezik(new Korong(1, 1, Szin.Kek)));
        assertFalse(lepesek.kovetkezik(new Korong(1, 1, Szin.Sarga)));
    }

    @Test
    public void nyertTest() {
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
        assertTrue(lepesek.nyert());
        lepesek.setKekNyeroKorongok(Arrays.asList(
                new Korong(0, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Sarga),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek)
        ));
        assertFalse(lepesek.nyert());

        lepesek.setKekNyeroKorongok(Arrays.asList(
                new Korong(2, 0, Szin.Kek),
                new Korong(0, 1, Szin.Kek),
                new Korong(0, 2, Szin.Kek),
                new Korong(0, 3, Szin.Kek),
                new Korong(0, 4, Szin.Kek),
                new Korong(1, 0, Szin.Kek),
                new Korong(1, 4, Szin.Kek)
        ));
        assertFalse(lepesek.nyert());
    }
    
    @Test
    public void getMasikSzinTest() {
        Jatek jatek = new Jatek();
        Lepesek lepesek = jatek.getLepes();
        assertEquals(Szin.Kek, lepesek.getMasikSzin(Szin.Sarga));
        assertEquals(Szin.Sarga, lepesek.getMasikSzin(Szin.Kek));
    }
}
