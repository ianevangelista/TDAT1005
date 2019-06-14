package dyrehage;

class Fiskestim extends Dyregruppe {
  private final int gjennomsnittligLengde;
  private final boolean kanDeleAkvarium;

  public Fiskestim(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider, int gjennomsnittligLengde, boolean kanDeleAkvarium){
    super(norskNavn,latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
    this.gjennomsnittligLengde = gjennomsnittligLengde;
    this.kanDeleAkvarium = kanDeleAkvarium;
  }

  public int getGjennomsnittligLengde(){
    return this.gjennomsnittligLengde;
  }

  public String kanDeleAkvarium(){
    if(this.kanDeleAkvarium == true) return "Ja";
    else return "Nei";
  }
}
