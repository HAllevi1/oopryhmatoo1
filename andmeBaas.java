import java.sql.*;
import java.util.Scanner;

public class andmeBaas {


    public Connection yhilduAndmebaasi() { //Ühildume andmebaasi
        String link = "jdbc:postgresql://localhost:5432/Todo_list";
        String kasutaja = "postgres";
        String parool = "postgres";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(link, kasutaja, parool); // Ühildume To-do list andmebaasi
            System.out.println("Ühendatud PostgreSQL andmebaasiga edukalt!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void lisaYlesanne() { //lisame ülesande andmebaasi
        Scanner sc = new Scanner(System.in);
        System.out.println("Sisesta ülesande nimi: ");
        String nimi = sc.nextLine();

        System.out.println("Sisesta ülesande kirjeldus: ");
        String kirjeldus = sc.nextLine();

        System.out.println("Sisesta tähtaeg (YYYY-MM-DD): )");
        String kuuPäev = sc.nextLine();

        System.out.println("Kas ülesanne on tehtud? (jah/ei)");
        String staatus = sc.nextLine();


        //Kuvame sisestatud info:
        System.out.println("Sisestasid järgmise info: ");
        System.out.println("Nimi: " + nimi);
        System.out.println("Kirjeldus: " + kirjeldus);
        System.out.println("Tähtaeg: " + kuuPäev);

        boolean sqlstatus = false;
        switch (staatus) {
            case "jah":
                System.out.println("Sisestasid " + staatus);
                sqlstatus = true;
                break;
            case "ei":
                System.out.println("Sisestasid " + staatus);
                sqlstatus = false;
                break;
        }

        //Alustame sisestatu lisamist andmebaasi

        String sql = "INSERT INTO ylesanded (nimi, kirjeldus, tahtaeg, staatus) VALUES (?, ?, ?, ?)";

        try (Connection conn = yhilduAndmebaasi(); //Ühendame andmebaasi
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //Anname muutujale pstmt väärtuse

            //Määrame päringulause veergudele väärtused
            pstmt.setString(1, nimi);
            pstmt.setString(2, kirjeldus);
            pstmt.setDate(3, Date.valueOf(kuuPäev));
            pstmt.setBoolean(4, sqlstatus);

            pstmt.executeUpdate(); //SQL päringu käivitus ja andmebaasi lisamine
            System.out.println("Ülesanne lisatud!");

        } catch (SQLException e) {
            System.out.println("Viga ülesande lisamisel: " + e.getMessage());
        }

    }

    public void kõikÜlesanded() { //tõmbame ülesanded andmebaasist programmi

    }
    public void kustutaÜlesanne() { //eemaldame ülesande andmebaasist

    }
}