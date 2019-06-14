import java.sql.*;

public class Database {
    private Connection forbindelse;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            forbindelse = DriverManager.getConnection("jdbc:mysql://mysql.stud.iie.ntnu.no:3306/junill?user=junill&password=zMtZq8dN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getForbindelse(){
        return forbindelse;
    }

    public void lukkForbindelse(Statement statement, ResultSet resultset, Connection connection){
        try {
            if (statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultset != null) resultset.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean regNyBok(Bok nyBok) throws IllegalAccessException {
        if(!(nyBok instanceof Bok)) throw new IllegalAccessException("Ikke en bok");
        String isbn = nyBok.getIsbn();
        String tittel = nyBok.getTittel();
        String forfatter = nyBok.getForfatter();
        String sql1 = "INSERT INTO boktittel(isbn, forfatter, tittel) VALUES(\"" + isbn + "\",  \"" + forfatter + "\",  \"" + tittel + "\")";
        String sql2 = "INSERT INTO eksemplar(isbn, eks_nr) VALUES(\"" + isbn + "\", " + 1 + ")";

        Database database = new Database();
        Connection forbindelse = database.getForbindelse();
        Statement statement = null;
        try {
            statement = forbindelse.createStatement();
            forbindelse.setAutoCommit(false);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            forbindelse.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                forbindelse.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                forbindelse.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            database.lukkForbindelse(statement, null, forbindelse);
        }
    }

    public static int regNyttEksemplar(String isbn){

        Database database = new Database();
        Connection forbindelse = database.getForbindelse();
        Statement statement = null;
        ResultSet rs = null;

        try {
            String hent = "SELECT max(eks_nr) FROM eksemplar WHERE isbn ='" + isbn + "';";

            statement = forbindelse.createStatement();
            forbindelse.setAutoCommit(false);
            rs = statement.executeQuery(hent);
            forbindelse.commit();
            rs.next();
            int eksnr = 0;
            eksnr = rs.getInt("max(eks_nr)");

            if(!(rs.next())) {
                eksnr++;
                String send = "INSERT INTO eksemplar(isbn, eks_nr) VALUES(\"" + isbn + "\", " + eksnr + ")";
                statement = forbindelse.createStatement();
                statement.executeUpdate(send);
                return eksnr;
            }else{
                return eksnr;
            }
        } catch (SQLException e) {
            try {
                forbindelse.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return 0;
        } finally {
            database.lukkForbindelse(statement, rs, forbindelse);
        }
    }

    public static boolean lånUtEksemplar(String isbn, String navn, int eksNr){
        Database database = new Database();
        Connection forbindelse = database.getForbindelse();
        Statement statement = null;

        String sql = "UPDATE eksemplar SET laant_av = \"" + navn + "\" WHERE isbn = \"" + isbn + "\" AND eks_nr = \"" + eksNr + "\"";
        try{
            statement = forbindelse.createStatement();
            statement.execute(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                forbindelse.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            database.lukkForbindelse(statement, null, forbindelse);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        Bok bok = new Bok("1111", "Min bok", "Ian");
        Boolean ok = regNyBok(bok);
        System.out.println(ok);

        int reg = regNyttEksemplar("12345");
        if(reg == 0){
            System.out.println("Funker ikke");
        }else {
            System.out.println(reg);
        }

        Boolean laan = lånUtEksemplar("1111", "Ian", 1);
        System.out.println(laan);


    }

}