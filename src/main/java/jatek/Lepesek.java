package jatek;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játék lépéseinek és a lépések előfeltételeinek implementációja.
 */
public class Lepesek {

    private final Logger logger = LoggerFactory.getLogger(Lepesek.class);

    private final List<Korong> korongok;
    private Szin aktivSzin;

    private List<Korong> kekNyeroKorongok = new ArrayList<>();
    private List<Korong> sargaNyeroKorongok = new ArrayList<>();

    /**
     * Létrehozza a játék kezdő állapotát.
     *
     * @param korongok a játékosok kezdő {@link Korong}jai.
     */
    public Lepesek(List<Korong> korongok) {
        this.korongok = korongok;
        aktivSzin = Szin.Kek;
        logger.info("Aktív játékos: " + aktivSzin);
    }

    /**
     * Ha érvényes a lépés, akkor végrehajtja a lépést, és megvizsgálja, hogy
     * nyert-e a lépő játékos, ha nem érvényes, akkor nem csinál semmit.
     *
     * @param honnan mozgatandó {@link Korong}
     * @param hova cél {@link Korong}
     * @return 0, ha nem volt lépés; 1, ha végrehajtotta a lépést; 2, ha ezzel a
     * lépéssel nyert a lépő játékos
     */
    public int lepes(Korong honnan, Korong hova) {
        try {
            if (ervenyesLepes(honnan, hova)) {
                hova.setSzin(aktivSzin);
                korongok.set(korongok.indexOf(honnan), hova);
                logger.info("lépés: " + honnan + "->" + hova);
                if (nyert()) {
                    logger.info(aktivSzin + " játékos nyert!");
                    return 2;
                }
                aktivSzin = getMasikSzin(aktivSzin);
                return 1;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            logger.error(ex.getMessage());
        }
        return 0;
    }

    /**
     * Meghatározza, hogy érvényes-e a lépés.
     *
     * @param honnan mozgatandó {@link Korong}
     * @param hova cél {@link Korong}
     * @return <code>true</code>, ha x és y érvényes koordinaták,
     * <code>false</code> egyébként
     */
    public Boolean ervenyesLepes(Korong honnan, Korong hova) {

        return ervenyesRacsPont(hova) && elerhetoRacsPont(honnan, hova)
                && kovetkezik(honnan);
    }

    /**
     * Meghatározza, hogy a <code>hova</code> {@link Korong} helyén van-e már
     * {@link Korong}, és azt, hogy a <code>hova</code> {@link Korong}
     * koordinátái érvényesek-e.
     *
     * @param hova a cél {@link Korong}
     * @return <code>true</code>, ha a <code>hova</code> {@link Korong} a táblán
     * van, és nincs ott másik {@link Korong}, <code>false</code> egyébként
     */
    public Boolean ervenyesRacsPont(Korong hova) {
        return hova.ervenyesXY() && szabadRacspont(hova);
    }

    /**
     * Meghatározza, hogy van-e {@link Korong} a <code>hova</code>
     * {@link Korong} helyén.
     *
     * @param hova mozgatando {@link Korong}
     * @return <code>true</code>, ha a <code>hova</code> {@link Korong} helyén
     * nincs másik {@link Korong}, <code>false</code> egyébként
     */
    public Boolean szabadRacspont(Korong hova) {
        return !korongok.stream().anyMatch(e -> e.egyenloKoordinatak(hova));
    }

    /**
     * Meghatározza, hogy a <code>honnan</code> és <code>hova</code>
     * {@link Korong}ok átlós szomszédok-e.
     *
     * @param honnan mozgatandó {@link Korong}
     * @param hova cél {@link Korong}
     * @return <code>true</code>, ha <code>honnan</code> és <code>hova</code>
     * {@link Korong}ok átlós szomszédok, <code>false</code> egyébként
     */
    public Boolean elerhetoRacsPont(Korong honnan, Korong hova) {
        return (Math.abs(honnan.getX() - hova.getX())) == 1
                && (Math.abs(honnan.getY() - hova.getY()) == 1);
    }

    /**
     * Meghatározza, hogy a lépő játékos a {@link Korong}ja-e a
     * <code>honnan</code> {@link Korong}.
     *
     * @param honnan a mozgatandó {@link Korong}
     * @return <code>true</code>, ha a <code>honnan</code> {@link Korong} a lépő
     * játékos korongja, <code>false</code> egyébként
     */
    public Boolean kovetkezik(Korong honnan) {
        return honnan.getSzin() == aktivSzin;
    }

    /**
     * Meghatározza, hogy nyert-e a lépő játékos.
     *
     * @return <code>true</code>, ha nyert a lépő játékos, <code>false</code>
     * egyébként
     */
    public boolean nyert() {
        if (aktivSzin == Szin.Kek) {
            return korongok.containsAll(kekNyeroKorongok);
        } else {
            return korongok.containsAll(sargaNyeroKorongok);
        }
    }

    /**
     * Visszaadja az ellenkező szint.
     *
     * @param szin egy {@link Szin}
     * @return {@link Szin#Sarga}, ha a paraméter
     * {@link Szin#Kek}, {@link Szin#Kek} egyébként
     */
    public Szin getMasikSzin(Szin szin) {
        if (szin == Szin.Kek) {
            return Szin.Sarga;
        } else {
            return Szin.Kek;
        }
    }

    /**
     * Visszaadja a lépő játékos {@link Szin}ét.
     *
     * @return a lépő játékos {@link Szin}e
     */
    public Szin getAktivSzin() {
        return aktivSzin;
    }

    /**
     * Visszaadja a játéjosok {@link Korong}jait.
     *
     * @return a játékosok {@link Korong}jai
     */
    public List<Korong> getKorongok() {
        return korongok;
    }

    /**
     * Visszaadja a kek játékos nyerő {@link Korong}jait.
     *
     * @return a kék játékos nyerő {@link Korong}jai
     */
    public List<Korong> getKekNyeroKorongok() {
        return kekNyeroKorongok;
    }

    /**
     * Visszaadja a sarga játékos nyerő {@link Korong}jait.
     *
     * @return a sarga játékos nyerő {@link Korong}jai
     */
    public List<Korong> getSargaNyeroKorongok() {
        return sargaNyeroKorongok;
    }

    /**
     * Módosítja a kék nyerő {@link Korong}okat.
     *
     * @param kekNyeroKorongok a kék nyerő {@link Korong}ok
     */
    public void setKekNyeroKorongok(List<Korong> kekNyeroKorongok) {
        logger.debug("Kek nyerő korongok:" + kekNyeroKorongok);
        this.kekNyeroKorongok = kekNyeroKorongok;
    }

    /**
     * Módosítja a sárga nyerő {@link Korong}okat.
     *
     * @param sargaNyeroKorongok a sarga nyerő {@link Korong}ok
     */
    public void setSargaNyeroKorongok(List<Korong> sargaNyeroKorongok) {
        logger.debug("Sárga nyerő korongok:" + sargaNyeroKorongok);
        this.sargaNyeroKorongok = sargaNyeroKorongok;
    }
}
