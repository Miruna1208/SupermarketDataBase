package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InterfataGrafica extends JFrame{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private JButton listaDeProduseButton;
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JButton adaugareProdusButton;
    private JButton modificaPretButton;
    private JButton modificaCantitateButton;
    private JButton stergeProdusButton;
    private JTextField textNume1;
    private JTextField textPretNou;
    private JTextField textCantitateNoua;
    private JTextField textField1;
    private JTextField textPret;
    private JTextField textCantitate;
    private JButton clearButton;
    private JTextField textSterg;
    private JButton clearButton1;
    private JButton clearButton2;
    private JTextField textMesaj1;
    private JTextField textMesaj2;


    public InterfataGrafica(){
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        listaDeProduseButton.setPreferredSize(new Dimension(10, 50));
        textArea1.setPreferredSize(new Dimension(10, 500));
        textArea1.setText("Apasati butonul \"Afisati lista de produse\" pentru a afisa lista de produse sau pentru a observa modificarile");


        listaDeProduseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  Interfata interfata;
                  interfata = new ProductRepository();
                  interfata.selectAllProducts(textArea1, DB_URL, DB_USER, DB_PASS);
            }
        });
            adaugareProdusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String numeProdus = textField1.getText();
                    try {
                        int pretProdus = Integer.parseInt(textPret.getText());
                        int cantitateProdus = Integer.parseInt(textCantitate.getText());
                        if (pretProdus > 0 && cantitateProdus > 0) {
                            Interfata interfata;
                            interfata = new ProductRepository();
                            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                                interfata.insertProduct(numeProdus, pretProdus, cantitateProdus, DB_URL, DB_USER, DB_PASS);
                                textMesaj1.setText("Ati adaugat produsul cu succes!");
                            } catch (SQLException exception) {
                                textMesaj1.setText("Nu s a putat face conexiunea la baza de date");
                                exception.printStackTrace();
                            }
                        } else {
                            textMesaj1.setText("Datele introduse nu sunt valide!");
                            Interfata interfata;
                            interfata = new ProductRepository();
                            interfata.negativeNumber(pretProdus);
                            interfata.negativeNumber(cantitateProdus);
                        }
                    } catch (NumberFormatException exception) {
                        textMesaj1.setText("Datele introduse nu sunt valide!");
                    }
                }
            });

        stergeProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus = textSterg.getText();
                Interfata interfata;
                interfata = new ProductRepository();
                interfata.deleteProdus(numeProdus, DB_URL, DB_USER, DB_PASS);
            }
        });
        modificaPretButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus1 = textNume1.getText();
                try {
                    int pretNou = Integer.parseInt(textPretNou.getText());
                    if(pretNou > 0) {
                        Interfata interfata;
                        interfata = new ProductRepository();
                        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                            interfata.updatePret(numeProdus1, pretNou, DB_URL, DB_USER, DB_PASS);
                            textMesaj2.setText("Modificare facuta cu succes!");
                        } catch (SQLException exception) {
                            textMesaj2.setText("Nu s a putat face conexiunea la baza de date");
                            exception.printStackTrace();
                        }
                    } else{
                        textMesaj2.setText("Datele introduse nu sunt valide!");
                        Interfata interfata;
                        interfata = new ProductRepository();
                        interfata.negativeNumber(pretNou);
                    }
                } catch (NumberFormatException exception){
                    textMesaj2.setText("Datele introduse nu sunt valide!");
                }
            }
        });

        modificaCantitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus2 = textNume1.getText();
                try{
                    int cantitateNoua = Integer.parseInt(textCantitateNoua.getText());
                    if(cantitateNoua>0){
                        Interfata interfata;
                        interfata = new ProductRepository();
                        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                            interfata.updateCantitate(numeProdus2, cantitateNoua,DB_URL, DB_USER, DB_PASS );
                            textMesaj2.setText("Modificare facuta cu succes!");
                        } catch (SQLException exception){
                            textMesaj2.setText("Nu s a putat face conexiunea la baza de date");
                            exception.printStackTrace();
                        }
                    }else{
                        textMesaj2.setText("Datele introduse nu sunt valide!");
                        Interfata interfata;
                        interfata = new ProductRepository();
                        interfata.negativeNumber(cantitateNoua);
                    }
                } catch(NumberFormatException exception){
                    textMesaj2.setText("Datele introduse nu sunt valide!");
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textPret.setText("");
                textCantitate.setText("");
            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textSterg.setText("");
            }
        });
        clearButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNume1.setText("");
                textPretNou.setText("");
                textCantitateNoua.setText("");
            }
        });
    }
    public static void main(String[] args) {
        InterfataGrafica interfataGrafica = new InterfataGrafica();
        Interfata interfata;
        interfata = new ProductRepository();
    }


}

