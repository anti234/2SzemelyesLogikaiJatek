/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jatek;

import java.util.ArrayList;
import java.util.List;

public class Lepesek {

    private final List<Korong> korongok;
    private Szin aktivSzin;

    private List<Korong> kekNyeroKorongok = new ArrayList<>();
    private List<Korong> sargaNyeroKorongok = new ArrayList<>();

    public Lepesek(List<Korong> korongok) {
        this.korongok = korongok;
        aktivSzin = Szin.Kek;
    }

    public int Lepes(Korong honnan, Korong hova) {

        if (Ervenyeslepes(honnan, hova)) {
            hova.setSzin(aktivSzin);
            korongok.set(korongok.indexOf(honnan), hova);
            if (Nyert()) {
                return 2;
            }
            aktivSzin = getMasikSzin(aktivSzin);
            return 1;
        } else {
            return 0;
        }
    }

    public Boolean Ervenyeslepes(Korong honnan, Korong hova) {

        return ErvenyesRacsPont(hova) && ElerhetoRacsPont(honnan, hova)
                && Kovetkezik(honnan);
    }

    public Boolean ErvenyesRacsPont(Korong hova) {
        return hova.ErvenyesXY() && SzabadRacspont(hova);
    }

    public Boolean SzabadRacspont(Korong hova) {
        return !korongok.stream().anyMatch(e -> e.EgyenloKoordinatak(hova));
    }

    public Boolean ElerhetoRacsPont(Korong honnan, Korong hova) {
        return (Math.abs(honnan.getX() - hova.getX())) == 1
                && (Math.abs(honnan.getY() - hova.getY()) == 1);
    }

    public Boolean Kovetkezik(Korong honnan) {
        return honnan.getSzin() == aktivSzin;
    }

    public boolean Nyert() {
        if (aktivSzin == Szin.Kek) {
            return korongok.containsAll(kekNyeroKorongok);
        } else {
            return korongok.containsAll(sargaNyeroKorongok);
        }
    }

    public Szin getMasikSzin(Szin szin) {
        if (szin == Szin.Kek) {
            return Szin.Sarga;
        } else {
            return Szin.Kek;
        }
    }

    public Szin getAktivSzin() {
        return aktivSzin;
    }

    public List<Korong> getKorongok() {
        return korongok;
    }

    public List<Korong> getKekNyeroKorongok() {
        return kekNyeroKorongok;
    }

    public List<Korong> getSargaNyeroKorongok() {
        return sargaNyeroKorongok;
    }

    public void setKekNyeroKorongok(List<Korong> kekNyeroKorongok) {
        this.kekNyeroKorongok = kekNyeroKorongok;
    }

    public void setSargaNyeroKorongok(List<Korong> sargaNyeroKorongok) {
        this.sargaNyeroKorongok = sargaNyeroKorongok;
    }
}
