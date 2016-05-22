package grafikusFelulet;

import jatek.Jatek;
import jatek.Korong;
import jatek.Szin;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xmlFeldolgozas.Eredmeny;
import xmlFeldolgozas.EredmenyKezelo;

/**
 * Összeköti az alkalmazást a felhasználói felülettel és az xml-t feldolgozó
 * osztályokkal.
 */
public final class GrafikusVezerlo {

    private static final Logger logger = LoggerFactory.getLogger(GrafikusVezerlo.class);
    /**
     * A kék játékos neve.
     */
    private static String kekJatekosNeve = "Kék Játékos";
    /**
     * A sárga játékos neve.
     */
    private static String sargaJatekosNeve = "Sárga Játékos";
    /**
     * Lephetnek-e a játékosok.
     */
    private static boolean lephet;

    /**
     * A grafikus felület játék táblája.
     */
    private final GridPane jatekTabla;

    /**
     * A játék funkcionalitását valósítja meg.
     */
    private Jatek jatek;
    /**
     * A kijelölt {@link Korong}. Ha nincs kijelölt {@link Korong}, akkor az
     * értéke <code>null</code>.
     */
    private Korong aktivKorong;
    /**
     * Nyert-e valamelyik játékos.
     */
    private boolean nyert;

    /**
     * A megtett lépések száma.
     */
    private int lepesSzam;
    /**
     * Az xml feldolgozását valósítja meg.
     */
    private final EredmenyKezelo eredmenykezelo;

    /**
     * Létrehoz egy GrafikusVezérlő osztályt kezdő értékekkel.
     *
     * @param gridPane a játék táblát reprezentáló osztály({@link GridPane})
     */
    public GrafikusVezerlo(GridPane gridPane) {
        jatekTabla = gridPane;
        ujJatek();
        eredmenykezelo = new EredmenyKezelo("/xml/VegEredmenyek.xml");
    }

    /**
     * új játékot indít, tehát visszaállítja a kezdő állapotot.
     */
    public void ujJatek() {
        korngokTorlese();
        jatek = new Jatek();
        korongokAbrazolasa(jatek.getKorongok());
        aktivKorong = null;
        nyert = false;
        lepesSzam = 0;
        logger.info("Új játék kezdése.");
    }

    /**
     * Ábrázolja a {@link Korong}okat a játék táblán.
     *
     * @param korongok {@link Korong}okból álló lista
     */
    public void korongokAbrazolasa(List<Korong> korongok) {
        for (Korong korong : korongok) {
            GrafikusVezerlo.this.korongSzinezese(korong);
        }
    }

    /**
     * Egy {@link Korong}ot ábrázol a játék táblán.
     *
     * @param korong egy {@link Korong} repzentáló objektum
     */
    public void korongSzinezese(Korong korong) {
        Circle kor = (Circle) jatekTabla.getChildren().get(korong.getX() * 5 + korong.getY());
        kor.setFill(getColor(korong.getSzin()));
    }

    /**
     * Egy {@link Korong}ot szinez a játék táblán.
     *
     * @param korong egy {@link Korong} repzentáló objektum
     * @param color a {@link Korong} szine({@link Color})
     */
    public void korongSzinezese(Korong korong, Color color) {
        Circle kor = (Circle) jatekTabla.getChildren().get(korong.getX() * 5 + korong.getY());
        kor.setFill(color);
    }

    /**
     * Minden {@link Korong}ot töröl a játék táblán.
     */
    public void korngokTorlese() {
        for (Node node : jatekTabla.getChildren()) {
            Circle kor = (Circle) node;
            kor.setFill(Color.TRANSPARENT);
        }
    }

    /**
     * Egy {@link Korong} kijelölésének vagy lépésének megvalósítása.
     *
     * @param kor egy {@link Korong}ot reprezentáló {@link Circle} objektum,
     * amire a felhasználó rákattintott
     * @return üres {@link String}, ha szabályos a kijelölés|lépés, egyébként
     * hiba üzenet.
     */
    public String esemeny(Circle kor) {
        if (getColor(jatek.getAktivSzin()) == kor.getFill()) {
            kijeloles(kor);
            return "";
        }

        if (aktivKorong != null) {
            return mozgatas(getKorong(kor));
        }

        logger.info("Hibás Lépés.");
        if (kor.getFill() == Color.TRANSPARENT) {
            return "Jelöljön ki egy " + jatek.getAktivSzin() + " korongot!";
        }

        return "Ön a " + jatek.getAktivSzin() + " Koronggal van!";
    }

    /**
     * Lépés végrehajtása az alkalmazással, majd a változtatás ábrázolása a
     * grafikus felületen.
     *
     * @param korong egy {@link Korong} repzentáló objektum
     * @return üres {@link String}, ha szabályos a lépés, egyébként hiba üzenet.
     */
    public String mozgatas(Korong korong) {

        int lepes = jatek.lepes(aktivKorong, korong);
        if (lepes > 0) {
            korongSzinezese(aktivKorong, Color.TRANSPARENT);
            GrafikusVezerlo.this.korongSzinezese(korong);
            logger.debug("Lépés: " + aktivKorong + "->" + korong);
            aktivKorong = null;
            if (lepes == 2) {
                nyert = true;
                logger.info(getAktivJatekos() + " nyert!");
                eredmenyMentese();
            }
            lepesSzam++;
            return "";

        } else {
            return "Szabálytalan lépés!";
        }
    }

    /**
     * Kijelöli azt a {@link Korong}ot, amivel a játékos lépni szeretne.
     *
     * @param kor egy {@link Korong}ot reprezentáló {@link Circle} objektum,
     * amire a felhasználó rákattintott
     */
    public void kijeloles(Circle kor) {
        if (aktivKorong != null) {
            korongSzinezese(aktivKorong);
        }
        aktivKorong = getKorong(kor);
        logger.info("Korong kijelölése: " + aktivKorong);
        kor.setFill(Color.RED);
    }

    /**
     * Visszaad egy {@link Korong}ot, amit a kor paraméterből határoz meg.
     *
     * @param kor egy korongot reprezentáló {@link Circle} objektum
     * @return {@link Korong}ot reprezentáló osztály
     */
    public Korong getKorong(Circle kor) {
        return new Korong(GridPane.getRowIndex(kor), GridPane.getColumnIndex(kor),
                getSzin((Color) kor.getFill()));
    }

    /**
     * Visszaad egy {@link Color} objektumot, amit a szin paraméterből határoz
     * meg.
     *
     * @param szin egy {@link Szin}t reprezentáló osztály
     * @return egy {@link Color} objektum
     */
    public Color getColor(Szin szin) {
        return szin == Szin.Sarga ? Color.YELLOW : Color.BLUE;
    }

    /**
     * Visszaad egy {@link Szin}t, amit a color paraméterből határoz meg.
     *
     * @param color egy szint reprezentáló osztály a grafikus
     * felületen({@link Color})
     * @return egy {@link Szin} objektum
     */
    public Szin getSzin(Color color) {
        if (color == Color.TRANSPARENT) {
            return jatek.getAktivSzin();
        } else {
            return color == Color.YELLOW ? Szin.Sarga : Szin.Kek;
        }
    }

    /**
     * Visszaadja a kék játékos nevét.
     *
     * @return a kék játékos neve
     */
    public static String getKekJatekosNeve() {
        return kekJatekosNeve;
    }

    /**
     * Módosítja a kék játékos nevét.
     *
     * @param kekJatekosNeve a kék játékos új neve.
     */
    public static void setKekJatekosNeve(String kekJatekosNeve) {
        GrafikusVezerlo.kekJatekosNeve = kekJatekosNeve.length() > 0
                ? kekJatekosNeve : "1. Játékos";
        logger.info("Kék játékos új neve: " + getKekJatekosNeve());
    }

    /**
     * Visszaadja a sárga játékos nevét.
     *
     * @return a sárga játékos neve
     */
    public static String getSargaJatekosNeve() {
        return sargaJatekosNeve;
    }

    /**
     * Módosítja a sárga játékos nevét.
     *
     * @param sargaJatekosNeve a sárga játékos új neve.
     */
    public static void setSargaJatekosNeve(String sargaJatekosNeve) {
        logger.info("Sárga játékos új neve: " + sargaJatekosNeve);
        GrafikusVezerlo.sargaJatekosNeve = sargaJatekosNeve.length() > 0
                ? sargaJatekosNeve : "2. Játékos";
        logger.info("Sárga játékos új neve: " + getSargaJatekosNeve());
    }

    /**
     * Visszaadja, hogy léphetnek-e a játékosok.
     *
     * @return <code>true</code>, ha léphetnek a játékosok, <code>false</code>
     * egyébként.
     */
    public static boolean isLephet() {
        return lephet;
    }

    /**
     * Módosítja a lephet változó értékét.
     *
     * @param lephet egy logikai értéket reprezentál
     */
    public static void setLephet(boolean lephet) {
        GrafikusVezerlo.lephet = lephet;
        logger.info("Léphetnek-e a játékosok: " + lephet);
    }

    /**
     * Visszaadja a lépő játékos szinét {@link Color}ként.
     *
     * @return a lépő játékos szine {@link Color}ként
     */
    public Color getAktivColor() {
        return getColor(jatek.getAktivSzin());
    }

    /**
     * Visszaadja a lépő játékos nevét.
     *
     * @return a lépő játékos neve
     */
    public String getAktivJatekos() {
        if (jatek.getAktivSzin() == Szin.Kek) {
            return kekJatekosNeve;
        } else {
            return sargaJatekosNeve;
        }
    }

    /**
     * Visszaadja, hogy nyert-e már valaki.
     *
     * @return <code>true</code>, ha nyert az egyik játékos, <code>false</code>
     * egyébként.
     */
    public boolean isNyert() {
        return nyert;
    }

    /**
     * Lekéri a már lejátszott játékok {@link Eredmeny}eit.
     *
     * @return a már lejátszott játékok eredményei
     */
    public List<Eredmeny> getEredmenyLista() {
        return eredmenykezelo.eredmenyLista();
    }

    /**
     * Elmenti egy lejátszott játék {@link Eredmeny}eit.
     */
    private void eredmenyMentese() {
        eredmenykezelo.eredmenyHozzaadasa(kekJatekosNeve, sargaJatekosNeve, getAktivJatekos(), lepesSzam);
    }
}
