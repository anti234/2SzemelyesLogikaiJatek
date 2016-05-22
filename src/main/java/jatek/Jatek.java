package jatek;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Meghatározza a játékteret a játék elkezdésénél.
 */
public final class Jatek {

    private final Logger logger = LoggerFactory.getLogger(Jatek.class);
    /**
     * A sárga játékos kezdő koordinátáit határozza meg.
     */
    private final int[][] sargaKezdoKoordinatak
            = {{3, 0}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};

    /**
     * A kék játékos kezdő koordinátáit határozza meg.
     */
    private final int[][] kekKezdoKoordinatak
            = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 4}};

    /**
     * A játék lépéseit, a játék lépéseinek előfeltételeit határozza meg.
     */
    private final Lepesek lepes;

    /**
     * Létrehozza a {@link Jatek} kezdő állapotát.
     */
    public Jatek() {
        lepes = new Lepesek(kezdoKorongok());
        lepes.setKekNyeroKorongok(kekNyeroKorongokMegadasa());
        lepes.setSargaNyeroKorongok(sargaNyeroKorongokMegadasa());
    }

    /**
     * Visszaadja a kezdő koordinátákból a játékosok {@link Korong}jait.
     *
     * @return a játékosok kezdo {@link Korong}jai
     */
    public List<Korong> kezdoKorongok() {
        logger.info("Kezdőkorongok inicializálása.");
        List<Korong> kezdoKorongok = new ArrayList<>();
        kezdoKorongok.addAll(getKorongok(kekKezdoKoordinatak, Szin.Kek));
        kezdoKorongok.addAll(getKorongok(sargaKezdoKoordinatak, Szin.Sarga));
        logger.debug("KezdoKorongok:" + kezdoKorongok);
        return kezdoKorongok;
    }

    /**
     * Visszaadja a sárga játékos nyerő {@link Korong}jait.
     *
     * @return a sárga játékos nyerő {@link Korong}jai
     */
    public List<Korong> sargaNyeroKorongokMegadasa() {
        return getKorongok(kekKezdoKoordinatak, Szin.Sarga);
    }

    /**
     * Visszaadja a kék játékos nyerő koordinátáit.
     *
     * @return a kék játékos nyerő koordinátái
     */
    public List<Korong> kekNyeroKorongokMegadasa() {
        return getKorongok(sargaKezdoKoordinatak, Szin.Kek);
    }

    /**
     * visszaad a paraméterekből egy korongokból álló listát
     *
     * @param koordinatak a {@link Korong}ok koordinátái
     * @param szin a {@link Korong}ok {@link Szin}e
     * @return {@link Korong}okból álló lista
     */
    public List<Korong> getKorongok(int[][] koordinatak, Szin szin) {
        List<Korong> korongok = new ArrayList<>();
        for (int[] koord : koordinatak) {
            korongok.add(new Korong(koord[0], koord[1], szin));
        }
        return korongok;
    }

    /**
     * Visszaadja, hogy lépett-e, és ha lépett, akkor nyert-e az adott játékos.
     *
     * @param honnan a mozgatandó {@link Korong}
     * @param hova a cél {@link Korong}
     * @return 0, ha nem volt a érvényes a lépés; 1, ha végrehajtotta a lépést;
     * 2, ha ezzel a lépéssel nyert a lépő játékos
     */
    public int lepes(Korong honnan, Korong hova) {
        return lepes.lepes(honnan, hova);
    }

    /**
     * Visszaadja a játékosok {@link Korong}jait.
     *
     * @return játékosok {@link Korong}jainak a listája
     */
    public List<Korong> getKorongok() {
        return lepes.getKorongok();
    }

    /**
     * Visszaadja a lépő játékos {@link Szin}ét.
     *
     * @return a lépő játékos {@link Szin}e
     */
    public Szin getAktivSzin() {
        return lepes.getAktivSzin();
    }

    /**
     * Visszadja a <code>lepes</code> objektumot.
     *
     * @return a <code>lepes</code> objektum
     */
    public Lepesek getLepes() {
        return lepes;
    }

    /**
     * Visszadja a sárga játékos kezdő koordinátáit
     *
     * @return a sárga játékos kezdő koordinátái tömben
     */
    public int[][] getSargaKezdoKoordinatak() {
        return sargaKezdoKoordinatak;
    }

    /**
     * Visszadja a kék játékos kezdő koordinátáit
     *
     * @return a kek játékos kezdő koordinátái tömben
     */
    public int[][] getKekKezdoKoordinatak() {
        return kekKezdoKoordinatak;
    }
}
