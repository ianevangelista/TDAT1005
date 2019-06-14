import dyrehage.*;
import java.util.Calendar;

class RovdyrFabrikk {

  public RovdyrFabrikk(){
  }

  public SkandinaviskeRovdyr nyBinne(int ankommetDato, String navn, int fDato, String adresse) {
    Individ binne = new Individ("Brunbjørn", "Ursus Arctos", "Ursidae", ankommetDato, adresse, navn, fDato, "Nei", true);
    return binne;
  }
  public SkandinaviskeRovdyr nyHannbjørn(int ankommetDato, String navn, int fDato, String adresse) {
    Individ hannBjørn = new Individ("Brunbjørn", "Ursus Arctos", "Ursidae", ankommetDato, adresse, navn, fDato, "Ja", true);
    return hannBjørn;
  }
  public SkandinaviskeRovdyr nyUlvetispe(int ankommetDato, String navn, int fDato, String adresse) {
    Individ ulveTispe = new Individ("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, "Nei", true);
    return ulveTispe;
  }
  public SkandinaviskeRovdyr nyUlvehann(int ankommetDato, String navn, int fDato, String adresse) {
    Individ uleveHann = new Individ("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, "Ja", true);
    return uleveHann;
  }
}
