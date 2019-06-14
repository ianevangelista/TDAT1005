/*
 * VisDatabaseClient.java
 *
 * Programmet kopler seg til databasetjener, og henter ut innholdet i tabellen Person.
 *
 */
import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.sql.*;
class VisDatabaseClient {
  public static void main(String[] args) throws Exception {
    String brukernavn = "iaevange";
    String passord = "53BJMtne";

    String databasedriver = "com.mysql.cj.jdbc.Driver";
    Class.forName(databasedriver);  // laster inn driverklassen

    String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;
    Connection forbindelse = DriverManager.getConnection(databasenavn);

    Statement setning = forbindelse.createStatement();

    ResultSet res = setning.executeQuery("select * from person");
    while (res.next()) {
      int persNr = res.getInt("persnr");
      String fornavn = res.getString("fornavn");
      String etternavn = res.getString("etternavn");
      System.out.println(persNr + ": " + fornavn + " " + etternavn);
    }
    res.close();
    setning.close();
    forbindelse.close();
  }
}

class DataLeser {
  /**
   * Leser passord fra brukeren.
   * Teksten "trimmes" før den returneres til klienten.
   */
  public static String lesPassord() {
    JLabel jPassword = new JLabel("Passord: "); // forenklet: http://www.asjava.com/swing/joptionpane-showinputdialog-with-password/
    JTextField password = new JPasswordField();
    Object[] obj = {jPassword, password};
    showConfirmDialog(null, obj, "Please input password for JOptionPane showConfirmDialog", OK_CANCEL_OPTION);
     return password.getText().trim();
  }

  /**
   * Leser en tekst fra brukeren. Blank tekst godtas ikke.
   * Teksten "trimmes" før den returneres til klienten.
   */
  public static String lesTekst(String ledetekst) {
    String tekst = showInputDialog(ledetekst);
    while (tekst == null || tekst.trim().equals("")) {
      showMessageDialog(null, "Du må oppgi data.");
      tekst = showInputDialog(ledetekst);
    }
    return tekst.trim();
  }
}



/* Utskrift fra programmet:
100: Ole Hansen
101: Anne Grethe Ås
102: Jonny Hansen
*/
