package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Produs produs = new Produs("lapte", 10, 2);
        System.out.println("Nume: " + produs.getNume());
        System.out.println("Pret: "+ produs.getPret());
        System.out.println("Cantitate: " + produs.getCantitate());

    }
}