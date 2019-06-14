import java.time.*;
class BasicMedlem extends BonusMedlem{

  public BasicMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato){
    super(medlNr, pers, innmeldtDato, 0);
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
    return super.registrerPoeng(nyPoeng);
  }
}
