import org.example.Interfata;
import org.example.InterfataGrafica;
import org.example.ProductRepository;
import org.example.Produs;
import org.junit.Test;

import javax.swing.*;
import java.sql.*;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class projectTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    @Test
    public void testUpdatePret() throws SQLException {
        Produs produs = new Produs("test", 10, 20);
        InterfataGrafica interfataGrafica = new InterfataGrafica();
        Interfata interfata;
        interfata = new ProductRepository();
        interfata.insertProduct("test", 10, 20, DB_URL, DB_USER, DB_PASS);
        interfata.updatePret("test", 20, DB_URL, DB_USER, DB_PASS);
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT PRET FROM PRODUSE WHERE NUME=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "test");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int value = resultSet.getInt("pret");
                        assertEquals(20, value);
                        interfata.deleteProdus("test", DB_URL, DB_USER, DB_PASS);
                    } else {
                        System.out.println("No matching records found.");
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void negativeNumberTest(){
        int pretTest = -1;
       assertThrows(IllegalArgumentException.class,
               () -> {
                   Interfata interfata;
                   interfata = new ProductRepository();
                   interfata.negativeNumber(pretTest);
               });
    }

}
