package org.example;

public class Produs {
    private String nume;
    private int pret;
    private int cantitate;
    public Produs(String nume, int pret, int cantitate){
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public int getCantitate() {
        return cantitate;
    }

    public int getPret() {
        return pret;
    }

    public String getNume() {
        return nume;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }
}
