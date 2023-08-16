package org.example;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    public static void main(String[] args) {
        selectAllProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doriti sa adaugati un produs in magazin? yes/no");
        String answer1 = scanner.nextLine();
        if (answer1.equals("yes")) {

            System.out.println("Introduceti numele produsului: ");
            String nume1 = scanner.nextLine();
            System.out.println("Introduceti pretul produsului: ");
            int pret1 = scanner.nextInt();
            System.out.println("Introduceti cantitatea produsului: ");
            int cantitate1 = scanner.nextInt();
            insertProduct(nume1, pret1, cantitate1);
        }
        System.out.println("Doriti sa modificati pretul unui produs? yes/no");
        String answer2 = scanner.nextLine();
        if (answer2.equals("yes")) {
            System.out.println("Introduceti numele produsului de modificat: ");
            String nume2 = scanner.nextLine();
            System.out.println("Introduceti noul pret: ");
            int pret2 = scanner.nextInt();
            updatePret(nume2, pret2);
        }
        System.out.println("Doriti sa modificati cantitatea unui produs? yes/no");
        String answer3 = scanner.nextLine();
        if (answer3.equals("yes")) {
            System.out.println("Introduceti numele produsului de modificat: ");
            String nume3 = scanner.nextLine();
            System.out.println("Introduceti noua cantitate: ");
            int cantitate3 = scanner.nextInt();
            updateCantitate(nume3, cantitate3);
        }
        System.out.println("Doriti sa stergeti un produs? yes/no");
        String answer4 = scanner.nextLine();
        if(answer4.equals("yes")){
            System.out.println("Introduceti numele produsului pe care doriti sa il stergeti");
            String nume4 = scanner.nextLine();
            deleteProdus(nume4);
        }

    }

    public static void selectAllProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySQL database");
            System.out.println("Prepare statement");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produse");

            System.out.println("Lista de produse");
            while (resultSet.next()) {
                System.out.println(" Nume: " + resultSet.getString("nume") + ", Pret: " +
                        resultSet.getString("pret") + ", Cantitate: " +
                        resultSet.getString("cantitate"));
            }
            System.out.println("-------");
        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }

    public static void insertProduct(String nume1, int pret1, int cantitate1) {
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

    public static void updatePret(String nume2, int pret2) {
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

    public static void updateCantitate(String nume3, int cantitate3) {
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

    public static void deleteProdus(String nume4) {
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


