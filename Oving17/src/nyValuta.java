import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;

public class nyValuta {

    static TextField navn;
    static TextField kurs;
    static TextField enhet;
    static Button knapp;
    static Valuta valuta;

    public static Valuta display(String tittel, String melding){
        Stage vindu = new Stage();
        vindu.initModality(Modality.APPLICATION_MODAL);
        vindu.setTitle(tittel);
        vindu.setMinWidth(250);
        Label label = new Label();
        label.setText(melding);

        navn = new TextField();
        kurs = new TextField();
        enhet = new TextField();


        Button knapp = new Button("Legg til");
        knapp.setOnAction(event -> {
            try {
                valuta = nyValuta();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            vindu.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(navn, kurs, enhet,knapp);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 400);
        vindu.setScene(scene);
        vindu.showAndWait();
        return valuta;
    }

    private static Valuta nyValuta() throws IllegalAccessException {
        String valutaNavn = navn.getText();
        String kursMotNok_lest = kurs.getText();
        double kursMotNok = Double.parseDouble(kursMotNok_lest);
        String enhet_lest = enhet.getText();
        int enhet = Integer.parseInt(enhet_lest);

        if(valutaNavn == null || kursMotNok <= 0 || enhet <= 0) throw new IllegalAccessException("Ikke gydlig");
        Valuta nyValuta = new Valuta(valutaNavn, kursMotNok, enhet);
        return nyValuta;
    }

}
