package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public InterfataGrafica(){
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        listaDeProduseButton.setPreferredSize(new Dimension(10, 50));
        textArea1.setPreferredSize(new Dimension(10, 500));


        listaDeProduseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  Interfata interfata;
                  interfata = new Methods();
                  interfata.selectAllProducts(textArea1, DB_URL, DB_USER, DB_PASS);
            }
        });
        adaugareProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus = textField1.getText();
                int pretProdus = Integer.parseInt(textPret.getText());
                int cantitateProdus = Integer.parseInt(textCantitate.getText());
                Interfata interfata;
                interfata = new Methods();
                interfata.insertProduct(numeProdus, pretProdus, cantitateProdus, DB_URL, DB_USER, DB_PASS);
            }
        });
        stergeProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus = textField1.getText();
                Interfata interfata;
                interfata = new Methods();
                interfata.deleteProdus(numeProdus, DB_URL, DB_USER, DB_PASS);
            }
        });
        modificaPretButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus1 = textNume1.getText();
                int pretNou = Integer.parseInt(textPretNou.getText());
                Interfata interfata;
                interfata = new Methods();
                interfata.updatePret(numeProdus1, pretNou,DB_URL, DB_USER, DB_PASS );
            }
        });
        modificaCantitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus1 = textNume1.getText();
                int cantitateNoua = Integer.parseInt(textCantitateNoua.getText());
                Interfata interfata;
                interfata = new Methods();
                interfata.updateCantitate(numeProdus1, cantitateNoua,DB_URL, DB_USER, DB_PASS );
            }
        });

    }
    public static void main(String[] args) {
        InterfataGrafica interfataGrafica = new InterfataGrafica();
        Interfata interfata;
        interfata = new Methods();
    }

}
