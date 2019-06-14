import java.time.*;
class SoelvMedlem extends BonusMedlem{

  public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng){
    super(medlNr, pers, innmeldtDato, poeng);
  }

  public int getMedlnr(){
    return super.getMedlnr();
  }

  public Personalia getPersonalia(){
    return super.getPersonalia();
  }

  public LocalDate getInnmeldtDato(){
    return super.getInnmeldtDato();
  }

  public int getPoeng(){
    return super.getPoeng();
  }

  public int finnKvalPoeng(LocalDate dagensDato){
    return super.finnKvalPoeng(dagensDato);
  }

  public boolean okPassord(String passord){
    return super.okPassord(passord);
  }

  @Override
  public boolean registrerPoeng(int nyPoeng){
    return super.registrerPoeng((int)(nyPoeng*1.2));
  }
}
