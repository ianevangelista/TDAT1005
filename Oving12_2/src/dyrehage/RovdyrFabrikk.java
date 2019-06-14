package dyrehage;

public class RovdyrFabrikk {

  public RovdyrFabrikk(){
  }

  public SkandinaviskeRovdyr nyBinne(int ankommetDato, String navn, String fDato, String adresse, int antKull) {
    return new Hunnindivid("Brunbjørn", "Ursus Arctos", "Ursidae", ankommetDato, adresse, navn, fDato, "Nei", true, antKull);
  }
  public SkandinaviskeRovdyr nyHannbjørn(int ankommetDato, String navn, String fDato, String adresse) {
    return new Hannindivid("Brunbjørn", "Ursus Arctos", "Ursidae", ankommetDato, adresse, navn, fDato, "Ja", true);
  }
  public SkandinaviskeRovdyr nyUlvetispe(int ankommetDato, String navn, String fDato, String adresse, int antKull) {
    return new Hunnindivid("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, "Nei", true, antKull);
  }
  public SkandinaviskeRovdyr nyUlvehann(int ankommetDato, String navn, String fDato, String adresse) {
    return new Hannindivid("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, "Ja", true);
  }
}
