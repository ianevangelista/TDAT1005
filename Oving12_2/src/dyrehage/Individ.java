package dyrehage;

import java.util.Calendar;

abstract public class Individ extends Dyr implements SkandinaviskeRovdyr{
  private final String navn;
  private final String fDato;
  private final String hanndyr;
  private final boolean farlig;
  private int antKull;


  public Individ(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, String fDato, String hanndyr, boolean farlig, int antKull){
    super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
    this.navn = navn;
    this.fDato = fDato;
    this.hanndyr = hanndyr;
    this.farlig = farlig;
    this.antKull = antKull;
  }

  public String getNavn(){
    return this.navn;
  }

  public int getFdato(){
    return Integer.parseInt(this.fDato);
  }

  public String getHanndyr(){
    return this.hanndyr;
  }

  public String getFarlig(){
    if(this.farlig == true) return "Farlig";
    else return "Ikke farlig";
  }

  public int getAlder(){
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int arFodt = Integer.parseInt(this.fDato.substring(0,4));
    return year - arFodt;
  }

  public String getAdresse(){
    return super.getAdresse();
  }

  public void flytt(String nyAdresse) {
    super.setAdresse(nyAdresse);
  }

  @Override
  public String skrivUtInfo() {
    return super.toString() + "\nNavn: " + this.navn + "\nFødselsdato: " + this.fDato + "\nHanndyr: " + this.hanndyr + "\nFarlig?: " + getFarlig() + "\nAntall kull: " + this.antKull;
  }

  public int getAntKull() {
    return antKull;
  }
  public void leggTilKull(int antall) {
    this.antKull += antall;
  }
  public void leggTilNyttKull() {
    this.antKull += 1;
  }
}
