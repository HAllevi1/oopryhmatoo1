import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Todo_list";
        String user = "henri2";
        String password = "tusti";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Ühendus PostgreSQL andmebaasiga õnnestus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
