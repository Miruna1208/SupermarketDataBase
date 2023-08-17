package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Methods implements Interfata {

    @Override
    public void selectAllProducts(JTextArea textArea1, String DB_URL, String DB_USER, String DB_PASS) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySQL database");
            System.out.println("Prepare statement");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produse");

            System.out.println("Lista de produse");
            textArea1.setText("");
            while (resultSet.next()) {
                String column1 = resultSet.getString("nume");
                String column2 = resultSet.getString("pret");
                String column3 = resultSet.getString("cantitate");
                textArea1.setText(textArea1.getText() + column1 + "\t" + column2 +" \t"+ column3 + "\n");

            }

        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(String nume1, int pret1, int cantitate1, String DB_URL, String DB_USER, String DB_PASS) {
        Produs produs = new Produs(nume1, pret1, cantitate1);
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUSE VALUES(?, ?, ?)");
            statement.setString(1, produs.getNume());
            statement.setInt(2, produs.getPret());
            statement.setInt(3, produs.getCantitate());
            statement.execute();
            System.out.println("Insert executed succesfully");
        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }

    @Override
    public void updatePret(String nume2, int pret2, String DB_URL, String DB_USER, String DB_PASS) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySQL database");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE PRODUSE SET PRET=? WHERE NUME=?");
            statement.setInt(1, pret2);
            statement.setString(2, nume2);

            statement.execute();
            System.out.println("Update executed successfully");
        } catch (SQLException e) {
            System.out.println("Coneextion failure");
            e.printStackTrace();
        }
    }

    @Override
    public void updateCantitate(String nume3, int cantitate3, String DB_URL, String DB_USER, String DB_PASS) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySQL database");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE PRODUSE SET CANTITATE=? WHERE NUME=?");
            statement.setInt(1, cantitate3);
            statement.setString(2, nume3);

            statement.execute();
            System.out.println("Update executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProdus(String nume4, String DB_URL, String DB_USER, String DB_PASS) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySQL database");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement(" DELETE FROM PRODUSE WHERE NUME=?");
            statement.setString(1, nume4);

            statement.execute();
            System.out.println("Delete executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }

    }
}
