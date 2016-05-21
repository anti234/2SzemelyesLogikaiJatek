package xmlFeldolgozas;

import javafx.beans.property.SimpleStringProperty;

/**
 * Egy eredményt reprezentáló osztály.
 */
public class Eredmeny {

    /**
     * A kék játékos neve.
     */
    private final SimpleStringProperty kekJatekosNeve;
    /**
     * A sárga játékos neve.
     */
    private final SimpleStringProperty sargaJatekosNeve;
    /**
     * A nyerő játékos neve.
     */
    private final SimpleStringProperty nyeroJatekosNeve;
    /**
     * A megtett lépés szám.
     */
    private final SimpleStringProperty lepesszam;

    /**
     * Létrehoz egy eredmény osztályt kezdő értékekkel.
     * 
     * @param kekJatekosNeve a kék játékos neve
     * @param sargaJatekosNeve a sarga játékos neve
     * @param nyeroJatekosNeve a nyerő játékos neve
     * @param lepesszam a megtett lépés szám
     */
    public Eredmeny(String kekJatekosNeve, String sargaJatekosNeve, String nyeroJatekosNeve, int lepesszam) {
        this.kekJatekosNeve = new SimpleStringProperty(kekJatekosNeve);
        this.sargaJatekosNeve = new SimpleStringProperty(sargaJatekosNeve);
        this.nyeroJatekosNeve = new SimpleStringProperty(nyeroJatekosNeve);
        this.lepesszam = new SimpleStringProperty(String.valueOf(lepesszam));
    }

    /**
     * Visszaadja a kék játékos nevét.
     * 
     * @return a kék játékos neve
     */
    public String getKekJatekosNeve() {
        return kekJatekosNeve.getValue();
    }

    /**
     * Visszaadja a sárga játékos nevét.
     * 
     * @return a sárga játékos neve
     */
    public String getSargaJatekosNeve() {
        return sargaJatekosNeve.getValue();
    }

    /**
     * Visszaadja a nyerő játékos nevét.
     * 
     * @return a nyerő játékos neve
     */
    public String getNyeroJatekosNeve() {
        return nyeroJatekosNeve.getValue();
    }

    /**
     * Visszaadja a megtett lépés szamot.
     * 
     * @return a megtett lépés szam
     */
    public String getLepesszam() {
        return lepesszam.getValue();
    }

    @Override
    public String toString() {
        return "Eredmeny{" + "kekJatekosNeve=" + kekJatekosNeve.getValue()
                + ", sargaJatekosNeve=" + sargaJatekosNeve.getValue() + ", nyeroJatekosNeve="
                + nyeroJatekosNeve.getValue() + ", lepesszam=" + lepesszam.getValue() + '}';
    }
}
