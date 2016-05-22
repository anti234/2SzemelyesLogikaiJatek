package jatek;

import java.util.Objects;

/**
 * Egy korongot reprezentáló osztály.
 *
 */
public class Korong {

    private int x;
    private int y;
    private Szin szin;

    /**
     * Létrehoz egy {@link Korong}ot koordinátákkal, {@link Szin} nélkül.
     *
     * @param x a {@link Korong} vízszintes koordinátája
     * @param y a {@link Korong} függőleges koordinátája
     */
    public Korong(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Létrehoz egy {@link Korong}ot koordinátákkal és {@link Szin}nel.
     *
     * @param x a {@link Korong} vízszintes koordinátája
     * @param y a {@link Korong} függőleges koordinátája
     * @param szin a {@link Korong} {@link Szin}e
     */
    public Korong(int x, int y, Szin szin) {
        this.x = x;
        this.y = y;
        this.szin = szin;
    }

    /**
     * MegHatározza, hogy az x koordinata érvényes-e.
     *
     * @return  <code>true</code>, ha x érvényes koordinata, <code>false</code>
     * egyébként
     */
    public Boolean ervenyesX() {
        return x >= 0 && x < 5;
    }

    /**
     * Visszadja, hogy az y koordinata érvényes-e.
     *
     * @return  <code>true</code>, ha y érvényes koordinata, <code>false</code>
     * egyébként
     */
    public Boolean ervenyesY() {
        return y >= 0 && y < 5;
    }

    /**
     * Visszadja, hogy az x és y koordinaták érvényesek-e.
     *
     * @return <code>true</code>, ha x és y érvényes koordinaták,
     * <code>false</code> egyébként
     */
    public Boolean ervenyesXY() {
        return ervenyesX() && ervenyesY();
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    @Override
    public String toString() {
        return szin + "Korong(" + "x=" + x + ", y=" + y + ')';
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        hash = 31 * hash + Objects.hashCode(this.szin);
        System.out.println(Objects.hashCode(this.szin));
        return hash;
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korong other = (Korong) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return this.szin == other.szin;
    }

    /**
     * Visszaadja, hogy a <code>korong</code> koordinátái megegyeznek-e ennek az
     * objektumnak a koordinátáival.
     *
     * @param korong az összehasonlítandó {@link Korong} objektum
     * @return  <code>true</code>, ha a paraméterben megadott {@link Korong}
     * objektum koordinátái megegyeznek ennek az objektumnak a koordinátáival,
     * <code>false</code> egyébként
     */
    public boolean egyenloKoordinatak(Korong korong) {
        if (korong == null) {
            return false;
        } else {
            return korong.getX() == x && korong.getY() == y;
        }
    }

    /**
     * Visszaadja a {@link Korong} x koordinátáját.
     *
     * @return a {@link Korong} x koordinátája
     */
    public int getX() {
        return x;
    }

    /**
     * Módosítja a {@link Korong} x koordinátájának az értékét.
     *
     * @param x az x koordináta új értéke
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Visszaadja a {@link Korong} y koordinátáját.
     *
     * @return a {@link Korong} y koordinátája
     */
    public int getY() {
        return y;
    }

    /**
     * Módosítja a {@link Korong} y koordinátájának az értékét.
     *
     * @param y az y koordináta új értéke
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Visszaadja a {@link Korong} {@link Szin}ét.
     *
     * @return a {@link Korong} {@link Szin}e
     */
    public Szin getSzin() {
        return szin;
    }

    /**
     * Módosítja a {@link Korong} {@link Szin}ét.
     *
     * @param szin a {@link Korong} új {@link Szin}e
     */
    public void setSzin(Szin szin) {
        this.szin = szin;
    }
}
