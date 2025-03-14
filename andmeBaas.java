import java.sql.*;
import java.util.Scanner;

public class andmeBaas {

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

        try (Connection conn = Ühenduvus.yhilduAndmebaasi(); //Ühendame andmebaasi
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

    public void kõikÜlesanded() { //tõmbame ülesanded andmebaasist programmi  TODO LIPPING

    }
    public void kustutaÜlesanne() { //eemaldame ülesande andmebaasist       TODO LIPPING

    }

    public void muuda(int valik) {
        Scanner sc = new Scanner(System.in);
        if (valik == 1) {

            System.out.println("Sisesta mis rea tähtaega soovid vahetada: ");
            int rida = sc.nextInt();
            sc.nextLine();

            String tahtaeg = null;

            String valitudRida = "SELECT tahtaeg FROM ylesanded WHERE id = ?";

            try (Connection conn = Ühenduvus.yhilduAndmebaasi();
                 PreparedStatement pstmt2 = conn.prepareStatement(valitudRida)) {

                pstmt2.setInt(1, rida);
                try (ResultSet rs = pstmt2.executeQuery()) {
                    if (rs.next()) {
                        tahtaeg = rs.getString("tahtaeg");
                        System.out.println("Praegune tahtaeg: " + tahtaeg);
                    } else {
                        System.out.println("Ülesannet ID-ga " + rida + "ei leitud.");
                        return;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Viga");
                return;
            }

            System.out.println("Sisesta uus tähtaeg: ");
            Date uusTahtaeg = Date.valueOf(sc.nextLine());

            String sql = "UPDATE ylesanded SET tahtaeg = ? WHERE id = ?";

            try (Connection conn = Ühenduvus.yhilduAndmebaasi(); //Ühendame andmebaasi
                 PreparedStatement pstmt = conn.prepareStatement(sql)) { //Anname muutujale pstmt väärtuse

                //Määrame päringulause veergudele väärtused
                pstmt.setDate(1, uusTahtaeg);
                pstmt.setInt(2, rida);

                int uuendatud = pstmt.executeUpdate(); //SQL päringu käivitus ja andmebaasi lisamine
                if (uuendatud > 0) {
                    System.out.println("Ülesande tähtaeg uuendatud!");
                } else {
                    System.out.println("Uuendamine ebaõnnestus, kontrolli ID-d.");
                }

            } catch (SQLException e) {
                System.out.println("Viga ülesande muutmisel: " + e.getMessage());
            }
        } else if (valik == 2) {

            System.out.println("Sisesta mis rea kirjeldust sa soovid muuta: ");
            int rida = sc.nextInt();
            sc.nextLine();

            String kirjeldus = null;

            String valitudRida = "SELECT kirjeldus FROM ylesanded WHERE id = ?";

            try (Connection conn = Ühenduvus.yhilduAndmebaasi();
                 PreparedStatement pstmt2 = conn.prepareStatement(valitudRida)) {

                pstmt2.setInt(1, rida);
                try (ResultSet rs = pstmt2.executeQuery()) {
                    if (rs.next()) {
                        kirjeldus = rs.getString("kirjeldus");
                        System.out.println("Praegune kirjeldus: " + kirjeldus);
                    } else {
                        System.out.println("Ülesannet ID-ga " + rida + "ei leitud.");
                        return;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Viga");
                return;
            }

            System.out.println("Sisesta uus kirjeldus: ");
            String uusKirjeldus = sc.nextLine();

            String sql = "UPDATE ylesanded SET kirjeldus = ? WHERE id = ?";

            try (Connection conn = Ühenduvus.yhilduAndmebaasi(); //Ühendame andmebaasi
                 PreparedStatement pstmt = conn.prepareStatement(sql)) { //Anname muutujale pstmt väärtuse

                //Määrame päringulause veergudele väärtused
                pstmt.setString(1, uusKirjeldus);
                pstmt.setInt(2, rida);

                int uuendatud = pstmt.executeUpdate(); //SQL päringu käivitus ja andmebaasi lisamine
                if (uuendatud > 0) {
                    System.out.println("Ülesande kirjeldus uuendatud!");
                } else {
                    System.out.println("Uuendamine ebaõnnestus, kontrolli ID-d.");
                }

            } catch (SQLException e) {
                System.out.println("Viga ülesande muutmisel: " + e.getMessage());
            }
        }
    }

    public void muudaKirjeldust() { //Muudame andmebaasis oleva ülesande kirjeldust TODO HENRI

    }
}