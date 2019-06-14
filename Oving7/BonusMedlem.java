import java.time.*;
class BonusMedlem {
  private final int medlNr;
  private final Personalia pers;
  private final LocalDate innmeldtDato;
  private int poeng = 0;

  public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng){
    if(medlNr < 0)throw new IllegalArgumentException("Ikke gyldig");
    if(!(pers instanceof Personalia))throw new IllegalArgumentException("Ikke gyldig");
    this.medlNr = medlNr;
    this.pers = pers;
    this.innmeldtDato = innmeldtDato;
    this.poeng = poeng;
  }

  public int getMedlnr(){
    return medlNr;
  }

  public Personalia getPersonalia(){
    return pers;
  }

  public LocalDate getInnmeldtDato(){
    return innmeldtDato;
  }

  public int getPoeng(){
    return poeng;
  }

  public int finnKvalPoeng(LocalDate dagensDato){
    int mellomAr = Period.between(innmeldtDato, dagensDato).getYears();
    int dagerMellom = Period.between(innmeldtDato, dagensDato).getDays();
    dagerMellom += mellomAr*365;
    if(dagerMellom < 365){
      return poeng;
    }
    else return 0;
  }

  public boolean okPassord(String passord){
    if(passord == null) return false;
    return true;
  }

  public boolean registrerPoeng(int nyPoeng){
    if(nyPoeng < 0) return false;
    else{
      poeng += nyPoeng;
      return true;
    }
  }
}
