package Jatek;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anti
 */
public final class Jatek {

    private final int[][] sargaKezdoKoordinatak
            = {{3, 0}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};

    private final int[][] kekKezdoKoordinatak
            = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 4}};

    private final Lepesek lepes;

    public Jatek() {
        lepes = new Lepesek(KezdoKorongok());
        lepes.setKekNyeroKorongok(KekNyeroKorongokMegadasa());
        lepes.setSargaNyeroKorongok(SargaNyeroKorongokMegadasa());
    }

    public List<Korong> KezdoKorongok() {
        List<Korong> kezdoKorongok = new ArrayList<>();
        kezdoKorongok.addAll(getKorongok(kekKezdoKoordinatak, Szin.Kek));
        kezdoKorongok.addAll(getKorongok(sargaKezdoKoordinatak, Szin.Sarga));
        return kezdoKorongok;
    }

    public List<Korong> SargaNyeroKorongokMegadasa() {
        return getKorongok(kekKezdoKoordinatak, Szin.Sarga);
    }
    
    public List<Korong> KekNyeroKorongokMegadasa() {
        return getKorongok(sargaKezdoKoordinatak, Szin.Kek);
    }

    public List<Korong> getKorongok(int[][] koordinatak, Szin szin) {
       List<Korong> korongok = new ArrayList<>();
        for (int[] koord : koordinatak) {
            korongok.add(new Korong(koord[0], koord[1], szin));
        }
       return korongok;
    }

    public int Lepes(Korong honnan, Korong hova) {
        return lepes.Lepes(honnan, hova);
    }

    public List<Korong> getKorongok() {
        return lepes.getKorongok();
    }

    public Szin getAktivSzin() {
        return lepes.getAktivSzin();
    }

    public Lepesek getLepes() {
        return lepes;
    }

    public int[][] getSargaKezdoKoordinatak() {
        return sargaKezdoKoordinatak;
    }

    public int[][] getKekKezdoKoordinatak() {
        return kekKezdoKoordinatak;
    }
}
