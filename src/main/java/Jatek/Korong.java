package Jatek;

import java.util.Objects;

/**
 * Egy Korongot reprezentáló osztály.
 * 
 */
public class Korong {

    /**
     * A korong vízszintes koordinátája.
     */
    private int x;
    /**
     * A korong függőleges koordinátája.
     */
    private int y;
    /**
     * A korong {@link Szin}e.
     */
    private Szin szin;

    /**
     * Létrehoz egy korongot szin nélkül.
     * 
     * @param x a korong vízszintes koordinátája
     * @param y a korong függőleges koordinátája
     */
    public Korong(int x, int y) {
        this.x = x;
        this.y = y;
    }

        /**
     * Létrehoz egy korongot szinnel.
     * 
     * @param x a korong vízszintes koordinátája
     * @param y a korong függőleges koordinátája
     * @param szin a korong {@link Szin}e
     */
    public Korong(int x, int y, Szin szin) {
        this.x = x;
        this.y = y;
        this.szin = szin;
    }

    /**
     * MegHatározza, hogy az x koordinata érvényes-e.
     * 
     * @return  <code>true</code> ha x érvényes koordinata, <code>false</code> egyébként
     */
    public Boolean ErvenyesX() {
        if (x >= 0 && x < 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Visszadja, hogy az y koordinata érvényes-e.
     * 
     * @return  <code>true</code> ha y érvényes koordinata, <code>false</code> egyébként
     */
    public Boolean ErvenyesY() {
        if (y >= 0 && y < 5) {
            return true;
        } else {
            return false;
        }
    }

     /**
     * Visszadja, hogy az x és y koordinaták érvényesek-e.
     * 
     * @return <code>true</code> ha x és y érvényes koordinaták, <code>false</code> egyébként
     */
    public Boolean ErvenyesXY() {
        if (ErvenyesX() && ErvenyesY()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return szin + "Korong(" + "x=" + x + ", y=" + y + ')';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        hash = 31 * hash + Objects.hashCode(this.szin);
        return hash;
    }

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
        if (this.szin != other.szin) {
            return false;
        }
        return true;
    }

    /**
     * Visszaadja, hogy a paraméterként adott korong objektum koordinátái 
     * megegyeznek-e ennek az objektumnak a koordinátáival.
     * 
     * @param korong az összehasonlítandó objectum
     * @return  <code>true</code> ha az o objektum koordinátái  megegyeznek ennek 
     * az objektumnak a koordinátáival  <code>false</code> egyébként
     */
    public boolean EgyenloKoordinatak(Korong korong) {
        if (korong == null) {
            return false;
        } else {
            return korong.getX() == x && korong.getY() == y;
        }
    }

    /**
     * Visszaadja a korong x koordinátáját.
     * 
     * @return a korong x koordinátája
     */
    public int getX() {
        return x;
    }

    /**
     * Módosítja a korong x koordinátájának az értékét.
     *
     * @param x az x koordináta új értéke
     */
    public void setX(int x) {
        this.x = x;
    }

        /**
     * Visszaadja a korong y koordinátáját.
     * 
     * @return a korong y koordinátája
     */
    public int getY() {
        return y;
    }
     
     /**
     * Módosítja a korong y koordinátájának az értékét.
     * 
     * @param y az y koordináta új értéke
     */
    public void setY(int y) {
        this.y = y;
    }

     /**
     * Visszaadja a korong {@link Szin}ét
     * 
     * @return a korong szine
     */
    public Szin getSzin() {
        return szin;
    }

     /**
     * Módosítja a Korong {@link Szin}ét.
     * 
     * @param szin a korong új szine
     */
    public void setSzin(Szin szin) {
        this.szin = szin;
    }
}
