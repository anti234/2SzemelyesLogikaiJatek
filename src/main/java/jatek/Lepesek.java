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
    /**
     * Játékosok korongjai.
     */
    private final List<Korong> korongok;
    /**
     * A lépő játékos szine.
     */
    private Szin aktivSzin;

    /**
     * A kék játékos nyerő korongjai.
     */
    private List<Korong> kekNyeroKorongok = new ArrayList<>();
    /**
     * A sárga játékos nyerő korongja.
     */
    private List<Korong> sargaNyeroKorongok = new ArrayList<>();

    /**
     * Létrehozza a játék kezdő állapotát.
     *
     * @param korongok a játékosok kezdő korongjai.
     */
    public Lepesek(List<Korong> korongok) {
        this.korongok = korongok;
        aktivSzin = Szin.Kek;
        logger.info("Aktív játékos: " + aktivSzin);
    }

    /**
     * Meghatározza, hogy a lépés érvényes-e, ha érvényes, akkor lép és
     * megvizsgálja, hogy nyert-e a lépő játékos, ha nem érvényes, akkor nem
     * csinál semmit.
     *
     * @param honnan mozgatandó korong
     * @param hova cél korong
     * @return 0, ha nem volt a érvényes a lépés; 1, ha végrehajtotta a lépést,
     * 2, ha ezzel a lépéssel nyert a lépő játékos
     */
    public int lepes(Korong honnan, Korong hova) {
        try {
            if (ervenyeslepes(honnan, hova)) {
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
     * Meghatározza, hogy érvényes,e a lépés.
     *
     * @param honnan mozgatandó korong
     * @param hova cél korong
     * @return <code>true</code>, ha x és y érvényes koordinaták,
     * <code>false</code> egyébként
     */
    public Boolean ervenyeslepes(Korong honnan, Korong hova) {
        
        return ervenyesRacsPont(hova) && elerhetoRacsPont(honnan, hova)
                && kovetkezik(honnan);
    }

    /**
     * Meghatározza, hogy a cél korong helyén van-e már korong, vagy
     *
     * @param hova a cél korong
     * @return <code>true</code>, ha a cél korong a táblán van, és nincs ott
     * másik korong <code>false</code> egyébként
     */
    public Boolean ervenyesRacsPont(Korong hova) {
        return hova.ervenyesXY() && szabadRacspont(hova);
    }

    /**
     * Meghatározza, hogy van-e korong a cél korong helyén.
     *
     * @param hova mozgatando korong
     * @return <code>true</code>, ha a cél korong helyén nincs másik korong,
     * <code>false</code> egyébként
     */
    public Boolean szabadRacspont(Korong hova) {
        return !korongok.stream().anyMatch(e -> e.egyenloKoordinatak(hova));
    }

    /**
     * Meghatározza, hogy a honnan és hova korong átlós szomszédok-e.
     *
     * @param honnan mozgatandó korong
     * @param hova cél korong
     * @return <code>true</code>, ha honnan és hova korong átlós szomszédok,
     * <code>false</code> egyébként
     */
    public Boolean elerhetoRacsPont(Korong honnan, Korong hova) {
        return (Math.abs(honnan.getX() - hova.getX())) == 1
                && (Math.abs(honnan.getY() - hova.getY()) == 1);
    }

    /**
     *
     *
     * @param honnan
     * @return
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
     * @param szin egy Szin
     * @return az ellenkező szin
     */
    public Szin getMasikSzin(Szin szin) {
        if (szin == Szin.Kek) {
            return Szin.Sarga;
        } else {
            return Szin.Kek;
        }
    }

    /**
     * Visszaadja a lépő játékos szinét.
     *
     * @return a lépő játékos szine
     */
    public Szin getAktivSzin() {
        return aktivSzin;
    }

    /**
     * Visszaadja a játéjosok korongjait.
     *
     * @return a játékosok korongjai
     */
    public List<Korong> getKorongok() {
        return korongok;
    }

    /**
     * Visszaadja a kek játékos nyerő korongjait.
     *
     * @return a kék játékos nyerő korongjai
     */
    public List<Korong> getKekNyeroKorongok() {
        return kekNyeroKorongok;
    }

    /**
     * Visszaadja a sarga játékos nyerő korongjait.
     *
     * @return a sarga játékos nyerő korongjai
     */
    public List<Korong> getSargaNyeroKorongok() {
        return sargaNyeroKorongok;
    }

    /**
     * Módosítja a kék nyerő korongokat.
     *
     * @param kekNyeroKorongok a kék nyerő korongok
     */
    public void setKekNyeroKorongok(List<Korong> kekNyeroKorongok) {
        logger.debug("Kek nyerő korongok:" + kekNyeroKorongok);
        this.kekNyeroKorongok = kekNyeroKorongok;
    }

    /**
     * Módosítja a sárga nyerő korongokat.
     *
     * @param sargaNyeroKorongok a sarga nyerő korongok
     */
    public void setSargaNyeroKorongok(List<Korong> sargaNyeroKorongok) {
        logger.debug("Sárga nyerő korongok:" + sargaNyeroKorongok);
        this.sargaNyeroKorongok = sargaNyeroKorongok;
    }
}
