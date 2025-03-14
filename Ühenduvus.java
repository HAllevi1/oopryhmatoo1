import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ühenduvus {
    public static Connection yhilduAndmebaasi() { //Ühildume andmebaasi
        String link = "jdbc:postgresql://localhost:5432/Todo_list";
        String kasutaja = "postgres";
        String parool = "postgres";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(link, kasutaja, parool); // Ühildume To-do list andmebaasi
            System.out.println("Ühendus loodud"); //testi eesmärgil
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
