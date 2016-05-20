package GrafikusFelulet;

import Jatek.Jatek;
import Jatek.Korong;
import Jatek.Szin;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Anti
 */
public final class GrafikusVezerlo {

    private static String kekJatekosNeve = "Kék Játékos";
    private static String sargaJatekosNeve = "Sárga Játékos";
    private static boolean lephet;

    private final GridPane jatekTabla;

    private Jatek jatek;
    private Korong aktivKorong;
    private boolean nyert;

    public GrafikusVezerlo(GridPane gridPane) {
        jatekTabla = gridPane;
        UjJatek();
    }

    public void UjJatek() {
        KorngokTorlese();
        jatek = new Jatek();
        KorongokAbrazolasa(jatek.getKorongok());
        aktivKorong = null;
        nyert = false;
    }

    public void KorongokAbrazolasa(List<Korong> korongok) {
        for (Korong korong : korongok) {
            KorongSzinezese(korong);
        }
    }

    public void KorongSzinezese(Korong korong) {
        Circle kor = (Circle) jatekTabla.getChildren().get(korong.getX() * 5 + korong.getY());
        kor.setFill(getColor(korong.getSzin()));
    }

    public void KorongSzinezese(Korong korong, Color color) {
        Circle kor = (Circle) jatekTabla.getChildren().get(korong.getX() * 5 + korong.getY());
        kor.setFill(color);
    }

    public void KorngokTorlese() {
        for (Node node : jatekTabla.getChildren()) {
            Circle kor = (Circle) node;
            kor.setFill(Color.TRANSPARENT);
        }
    }

    public String Esemeny(Circle kor) {
        if (getColor(jatek.getAktivSzin()) == kor.getFill()) {
            Kijeloles(kor);
            return "";
        }

        if (aktivKorong != null) {
            return Mozgatas(getKorong(kor));
        }

        if (kor.getFill() == Color.TRANSPARENT) {
            return "Jelöljön ki egy " + jatek.getAktivSzin() + " korongot!";
        }

        return "Ön a " + jatek.getAktivSzin() + " Koronggal van!";
    }

    private String Mozgatas(Korong korong) {

        int lephet = jatek.Lepes(aktivKorong, korong);
        if (lephet != 0) {
            KorongSzinezese(aktivKorong, Color.TRANSPARENT);
            KorongSzinezese(korong);
            aktivKorong = null;
            if (lephet == 2) {
                 nyert = true;
            }
                return "";
            
        } else {
            return "Szabálytalan lépés!";
        }
    }

    public void Kijeloles(Circle kor) {
        if (aktivKorong != null) {
            KorongSzinezese(aktivKorong);
        }
        aktivKorong = getKorong(kor);
        kor.setFill(Color.RED);
    }

    public Korong getKorong(Circle kor) {
        return new Korong(GridPane.getRowIndex(kor), GridPane.getColumnIndex(kor),
                getSzin((Color) kor.getFill()));
    }

    public Color getColor(Szin szin) {
        return szin == Szin.Sarga ? Color.YELLOW : Color.BLUE;
    }

    public Szin getSzin(Color color) {
        if (color == Color.TRANSPARENT) {
            return jatek.getAktivSzin();
        } else {
            return color == Color.YELLOW ? Szin.Sarga : Szin.Kek;
        }
    }

    public GridPane getJatekTabla() {
        return jatekTabla;
    }

    public static String getKekJatekosNeve() {
        return kekJatekosNeve;
    }

    public static void setKekJatekosNeve(String kekJatekosNeve) {
        GrafikusVezerlo.kekJatekosNeve = kekJatekosNeve.length() > 0
                ? kekJatekosNeve : "1. Játékos";
    }

    public static String getSargaJatekosNeve() {
        return sargaJatekosNeve;
    }

    public static void setSargaJatekosNeve(String sargaJatekosNeve) {
        GrafikusVezerlo.sargaJatekosNeve = sargaJatekosNeve.length() > 0
                ? sargaJatekosNeve : "2. Játékos";
    }

    public static boolean isLephet() {
        return lephet;
    }

    public static void setLephet(boolean lephet) {
        GrafikusVezerlo.lephet = lephet;
    }

    public Jatek getJatek() {
        return jatek;
    }

    public Color getAktivColor() {
        return getColor(jatek.getAktivSzin());
    }

    public String getAktivJatekos() {
        if (jatek.getAktivSzin() == Szin.Kek) {
            return kekJatekosNeve;
        } else {
            return sargaJatekosNeve;
        }
    }

    public boolean isNyert() {
        return nyert;
    }
}
