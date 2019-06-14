package dyrehage;

class Dyregruppe extends Dyr{
  private final String gruppenavn;
  private int antIndivider;

  public Dyregruppe(String norskNavn, String latNavn, String latFamilie,
             int ankommetDato, String adresse, String gruppenavn, int antIndivider){
    super(norskNavn,latNavn, latFamilie, ankommetDato, adresse);
    this.gruppenavn = gruppenavn;
    this.antIndivider = antIndivider;
  }

  public String getGruppenavn(){
    return this.gruppenavn;
  }

  public int getAntIndivider(){
    return this.antIndivider;
  }

  public void setAntIndivider(int nyAntall){
    this.antIndivider = nyAntall;
  }

  @Override
  public String getNorskNavn() {
    return "Gruppe av " + this.gruppenavn;
  }


}
