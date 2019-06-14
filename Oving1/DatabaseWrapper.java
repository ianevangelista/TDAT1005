import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseWrapper {
//    getAllPersons(){
//       //putt inn kode for ? returnere alle personer fram databasen
//    }

  public static void main(String args[]) throws Exception{
    System.out.println("Starter opp..");

    Scanner s = new Scanner(System.in);
    String password = s.nextLine();
    s.close();

    String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/iaevange?user=iaevange&password=" + password;

    try(Connection con = DriverManager.getConnection(url);) {

      Statement stmt = con.createStatement();
      ResultSet res = stmt.executeQuery("SELECT * FROM person ORDER BY fornavn");

      while (res.next()) {
        System.out.println("Personnr = " + res.getInt("persnr"));
        System.out.println("Fornavn = " + res.getString("fornavn"));
        System.out.println("Etternavn = " + res.getString("etternavn"));
      }

    }catch(SQLException sq){
            System.out.println("SQL-feil " + sq);
      }
  }
}
