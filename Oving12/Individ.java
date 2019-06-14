package dyrehage;
import java.util.Calendar;

public class Individ extends Dyr implements SkandinaviskeRovdyr{
  private final String navn;
  private final int fDato;
  private final String hanndyr;
  private final boolean farlig;

  public Individ(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, int fDato, String hanndyr, boolean farlig){
    super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
    this.navn = navn;
    this.fDato = fDato;
    this.hanndyr = hanndyr;
    this.farlig = farlig;
  }

  public String getNavn(){
    return this.navn;
  }

  public int getFdato(){
    return this.fDato;
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
    return year - this.fDato;
  }

  public String getAdresse(){
    return super.getAdresse();
  }

  public void flytt(String nyAdresse) {
    super.setAdresse(nyAdresse);
  }

  public String skrivUtInfo() {
    return super.toString();
  }
}
