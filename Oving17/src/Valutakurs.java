import com.sun.codemodel.internal.JOp;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class Valutakurs extends Application {
    Stage vindu;
    Scene scene;
    Button knapp;
    Button add;
    ListView<String> listView1;
    ListView<String> listView2;
    TextField tall;

    public static void main(String[] args) {
        launch(args);
    }

    public static Valuta[] valutaliste = {
            new Valuta("Euro", 8.10, 1), new Valuta("US Dollar", 6.23, 1),
            new Valuta("Britiske pund", 12.27, 1), new Valuta("Svenske kroner", 88.96, 100),
            new Valuta("Danske kroner", 108.75, 100), new Valuta("Yen", 5.14, 100),
            new Valuta("Islandske kroner", 9.16, 100), new Valuta("Norske kroner", 100, 100)
    };

    public void start(Stage primaryStage) throws Exception {
        vindu = primaryStage;
        vindu.setTitle("Valutkalkulator");
        knapp = new Button("Regn om");
        knapp.setOnAction(e -> regnValuta());

        add = new Button("Legg til ny valuta");
        add.setOnAction(e -> {
            Valuta result = nyValuta.display("Tittel", "Legg til");
            try {
                addValuta(result);
                listView1.getItems().add(valutaliste[valutaliste.length - 1].getValutanavn());
                listView2.getItems().add(valutaliste[valutaliste.length - 1].getValutanavn());
                listView1.refresh();
                listView2.refresh();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            System.out.println(result.getValutanavn());
            System.out.println(valutaliste[8].getValutanavn());
        });

        listView1 = new ListView<>();
        listView2 = new ListView<>();
        for (int i = 0; i < valutaliste.length; i++){
            listView1.getItems().add(valutaliste[i].getValutanavn());
            listView2.getItems().add(valutaliste[i].getValutanavn());
        }

        tall = new TextField();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView1, listView2, tall, knapp, add);

        scene = new Scene(layout, 600, 400);
        vindu.setScene(scene);
        vindu.show();
    }

    private void regnValuta(){
        try{
            double total = 0;
            int hjelp = 0;
            int antall = Integer.parseInt(tall.getText());
            if(antall == 0) total = 0;
            ObservableList<String> valutaFra;
            ObservableList<String> valutaTil;
            valutaFra = listView1.getSelectionModel().getSelectedItems();
            valutaTil = listView2.getSelectionModel().getSelectedItems();
            double kurs1 = 0;
            double kurs2 = 0;
            int enhet1 = 0;
            int enhet2 = 0;
            String navn1 = "";
            String navn2 = "";
            String melding = "";
            for(String m : valutaFra){
                melding = m;
                for (int i = 0; i < valutaliste.length; i++){
                    if(melding.equals(valutaliste[i].getValutanavn())){
                        kurs1 = valutaliste[i].getKurs();
                        enhet1 = valutaliste[i].getEnhet();
                        navn1 = valutaliste[i].getValutanavn();
                    }
                }
            }
            for(String m : valutaTil){
                melding = m;
                for (int j = 0; j < valutaliste.length; j++){
                    if(melding.equals(valutaliste[j].getValutanavn())){
                        kurs2 = valutaliste[j].getKurs();
                        enhet2 = valutaliste[j].getEnhet();
                        navn2 = valutaliste[j].getValutanavn();
                    }
                }
            }

            hjelp = antall/enhet1;
            kurs1 *= hjelp;
            total = (kurs1*enhet2)/kurs2;
            System.out.println(antall + " enheter fra " + navn1 + " til " + navn2 + " blir " + total);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Valuta[] addValuta(Object valuta) throws IllegalAccessException {
        if(!(valuta instanceof Valuta)) throw new IllegalAccessException("Ikke en gyldig valuta");
        Valuta nyValuta = (Valuta) valuta;
        utvid();
        int sisteEl = (valutaliste.length - 1);
        valutaliste[sisteEl] = nyValuta;
        return valutaliste;
    }

    private void utvid(){
        Valuta[] nyListe = new Valuta[valutaliste.length + 1];
        for (int i = 0; i < valutaliste.length; i++){
            nyListe[i] = new Valuta(valutaliste[i].getValutanavn(), valutaliste[i].getKurs(), valutaliste[i].getEnhet());
        }
        valutaliste = nyListe;
    }
}
