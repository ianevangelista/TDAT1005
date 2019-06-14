package dyrehage;

class Fugleflokk extends Dyregruppe {
  private final int gjennomsnittligVekt;
  private final boolean svommer;

  public Fugleflokk(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider, int gjennomsnittligVekt, boolean svommer){
    super(norskNavn,latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
    this.gjennomsnittligVekt = gjennomsnittligVekt;
    this.svommer = svommer;
  }

  public int getGjennomsnittligVekt(){
    return this.gjennomsnittligVekt;
  }

  public String svommer(){
    if(this.svommer == true) return "Ja";
    else return "Nei";
  }

}
