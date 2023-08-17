package org.example;

import javax.swing.*;
import java.util.ArrayList;

public interface Interfata {
    public void selectAllProducts(JTextArea textArea1, String DB_URL, String DB_USER, String DB_PASS);
    public void insertProduct(String nume1, int pret1, int cantitate1, String DB_URL, String DB_USER, String DB_PASS);
    public void updatePret(String nume2, int pret2, String DB_URL, String DB_USER, String DB_PASS);
    public void updateCantitate(String nume3, int cantitate3, String DB_URL, String DB_USER, String DB_PASS);
    public void deleteProdus(String nume4, String DB_URL, String DB_USER, String DB_PASS);
}
