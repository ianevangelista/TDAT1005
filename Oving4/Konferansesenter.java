import java.util.*;
class Konferansesenter {
  private ArrayList<Rom> romTabell = new ArrayList<Rom>();
  private int antRom;
  private int romReservert;

  public Konferansesenter(){
    antRom = 0;
  }

  public int getAntRom(){
    return antRom;
  }

  public int getRomNummerReservert(){
    return romReservert;
  }

  public boolean regRom(Object obj){
    if(!(obj instanceof Rom))return false; //klassetilhørighet
    if(obj == null) return false;

    Rom nyttRom = (Rom) obj; //cast
    if(antRom == 0){
      romTabell.add(nyttRom); //legger til hvis ingen rom registrert
      antRom++;
      return true;
    }
    for(Rom rom : romTabell){ //sjekker alle rom
      if(rom.getRomNummer() == nyttRom.getRomNummer()){
        return false;
      }
      else{
        romTabell.add(nyttRom);
        antRom++;
        return true;
      }
    }
    return false;
  }

  public boolean reserverRom(Object obj, int antPers){
    if(!(obj instanceof Reservasjon))return false; //klassetilhørighet
    if(obj == null)return false;
    if(antPers < 1) return false; //reservasjon for minst 1 person

    Reservasjon nyReservasjon = (Reservasjon) obj; //cast
    for(Rom rom : romTabell){ //sjekker alle rom for reservasjon
      if(rom.getMaksKapasitet() >= antPers) { //ikke overskrider kapasitet
        boolean ok = rom.reserver(nyReservasjon, antPers);
        if(ok){
          romReservert = rom.getRomNummer();
          return true;
        }
        else return false;
      }
    }
    return false;
  }

  public String finnRom(int romnummer){
    if(romnummer < 0)throw new IllegalArgumentException("Ikke gyldig"); //kaster

    for(Rom rom : romTabell){ //sjekker romnummer
      if(rom.getRomNummer() == romnummer){
        return rom.toString();
      }
    }
    return null;
  }

  public String finnRomIndeks(int indeks){
    if(antRom <= indeks)return null;
    return romTabell.get(indeks).toString();
  }

  public String toString(){
    String utskrift = "Alle rom:\n";
    for(Rom rom : romTabell){
      utskrift += rom.toString();
    }
    return utskrift;
  }

  public static void main(String[] args) {
    Konferansesenter konferansesenter = new Konferansesenter();
    Rom rom1 = new Rom(1, 10);
    Rom rom2 = new Rom(2, 11);

    boolean romRegistrert1 = konferansesenter.regRom(rom1); //nytt rom
    boolean romRegistrert2 = konferansesenter.regRom(rom2); //nytt rom

    if(romRegistrert1)System.out.println("Rom registrert"); //sjekker
    else System.out.println("Rom ble ikke registrert");

    System.out.println(konferansesenter.finnRomIndeks(0));

    if(romRegistrert2)System.out.println("Rom registrert"); //sjekker
    else System.out.println("Rom ble ikke registrert");

    Kunde k = new Kunde("Anne Hansen", "12345678"); //ny kunde
    Reservasjon r1 = new Reservasjon(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k); //to reservasjoner
    Reservasjon r2 = new Reservasjon(new Tidspunkt(200302010900L), new Tidspunkt(200302011000L), k);

    boolean romReservert1 = konferansesenter.reserverRom(r1, 5); //reservasjon
    if(romReservert1)System.out.println("Rom reservert");
    else System.out.println("Rom ble ikke reservert");

    boolean romReservert2 = konferansesenter.reserverRom(r2, 11); //ny reservasjon
    if(romReservert2)System.out.println("Rom reservert");
    else System.out.println("Rom ble ikke reservert");

    System.out.println(konferansesenter.finnRom(2));

  }
}
