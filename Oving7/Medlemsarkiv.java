import java.util.*;
import java.util.Random;
import java.time.*;

class Medlemsarkiv {
  private ArrayList<BonusMedlem> medlemTab = new ArrayList<BonusMedlem>();

  public Medlemsarkiv(){
  }

  public int finnPoeng(int medlNr, String passord){
    if(medlNr < 0 || passord == null) return -1;
    for(BonusMedlem medlem : medlemTab){
      if(medlNr == medlem.getMedlnr() && medlem.getPersonalia().okPassord(passord)){
        return medlem.getPoeng();
      }
    }
    return -1;
  }

  public boolean registrerPoeng(int medlNr, int poeng){
    if(medlNr < 0) return false;
    for(BonusMedlem medlem : medlemTab){
      if(medlNr == medlem.getMedlnr()){
        medlem.registrerPoeng(poeng);
        return true;
      }
    }
    return false;
  }

  private int finnLedigNr(){
    ArrayList<Integer> medlemNr = new ArrayList<Integer>();
    for(int nummer : medlemNr){
      for(BonusMedlem medlem : medlemTab){
        medlemNr.add(medlem.getMedlnr());
      }
    }
    Random rand = new Random();
    int randNr = rand.nextInt(1000) + 1;

    while(randNr != -1){
      for(BonusMedlem medlem : medlemTab){
        if(randNr == medlem.getMedlnr()){
          return -1;
        }
      }
      return randNr;
    }
    return -1;
  }

  public int nyMedlem(Personalia pers, LocalDate innmeldt){
    int medlNr = finnLedigNr();
    BasicMedlem nyMedlem = new BasicMedlem(medlNr, pers, innmeldt);
    medlemTab.add(nyMedlem);
    return medlNr;
  }

  public boolean sjekkMedlemmer(LocalDate test){
    int teller = 0;
    for(BonusMedlem medlem : medlemTab){
      int antPoeng = medlem.finnKvalPoeng(test);
      if(medlem instanceof BasicMedlem){
        if(antPoeng > 25000 && antPoeng < 75000){
          int nyMedlnr = medlem.getMedlnr();
          Personalia nyPers = medlem.getPersonalia();
          LocalDate nyDato = medlem.getInnmeldtDato();
          int nyPoeng = medlem.getPoeng();
          SoelvMedlem solv = new SoelvMedlem(nyMedlnr, nyPers, nyDato, nyPoeng);
          medlemTab.set(teller, solv);
          teller++;
          return true;
        }
        else if(antPoeng > 75000){
          int nyMedlnr = medlem.getMedlnr();
          Personalia nyPers = medlem.getPersonalia();
          LocalDate nyDato = medlem.getInnmeldtDato();
          int nyPoeng = medlem.getPoeng();
          GullMedlem gull = new GullMedlem(nyMedlnr, nyPers, nyDato, nyPoeng);
          medlemTab.set(teller, gull);
          teller++;
          return true;
        }
      }
      else if(medlem instanceof SoelvMedlem){
        if(antPoeng > 75000){
          int nyMedlnr = medlem.getMedlnr();
          Personalia nyPers = medlem.getPersonalia();
          LocalDate nyDato = medlem.getInnmeldtDato();
          int nyPoeng = medlem.getPoeng();
          GullMedlem gull = new GullMedlem(nyMedlnr, nyPers, nyDato, nyPoeng);
          medlemTab.set(teller, gull);
          teller++;
          return true;
        }
      }
    }
    return false;
  }

  public BonusMedlem getMedlem(int indeks){
    return medlemTab.get(indeks);
  }
}
