import java.util.*;

class Rom {
  private int romnummer;
  private int maksKapasitet;
  private ArrayList<Reservasjon> reservasjonsTabell = new ArrayList<Reservasjon>();
  private int antReservasjoner;

  public Rom(int romnummer, int maksKapasitet){
    if(romnummer < 0)throw new IllegalArgumentException("Ikke gyldig"); //kaster
    if(maksKapasitet < 0)throw new IllegalArgumentException("Ikke gyldig");

    this.romnummer = romnummer;
    this.maksKapasitet = maksKapasitet;
    antReservasjoner = 0;
  }

  public int getRomNummer(){
    return romnummer;
  }

  public int getMaksKapasitet(){
    return maksKapasitet;
  }


  public boolean reserver(Object obj, int antPers){
    if(!(obj instanceof Reservasjon))return false; //klassetilhørighet
    if(obj == null) return false;
    if(antPers > getMaksKapasitet())return false; //false hvis kapasitet er for liten

    Reservasjon nyReservasjon = (Reservasjon) obj; //cast
    if(antReservasjoner == 0){ //hvis rommet ikke har noen reservasjon
      reservasjonsTabell.add(nyReservasjon);
      antReservasjoner++;
      return true;
    }

    for (Reservasjon resRom : reservasjonsTabell) { //sjekker alle reservasjonene
      if(!(resRom.overlapp(nyReservasjon.getFraTid(), nyReservasjon.getTilTid()))){
        reservasjonsTabell.add(nyReservasjon);
        antReservasjoner++;
        return true;
      }
      else return false;
    }

    return false;
  }

  public String toString() {
    String utskrift = "\nRomnummer : " + romnummer + "\nKapasitet: " + maksKapasitet + "\nReservert for:\n";
    for (Reservasjon resRom : reservasjonsTabell) {
      utskrift += resRom.toString();
    }
    return utskrift;
  }

  public static void main(String[] args) {
    Kunde k = new Kunde("Anne Hansen", "12345678");
    Reservasjon r1 = new Reservasjon(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k);
    Reservasjon r2 = new Reservasjon(new Tidspunkt(200302010900L), new Tidspunkt(200302011200L), k);

    Rom rom = new Rom(1, 10);

    if(rom.reserver(r1, 10)) System.out.println("Reservasjon 1 fullført");
    else System.out.println("Reservasjon 1 ikke fullført");

    if(rom.reserver(r2, 10)) System.out.println("Reservasjon 2 fullført");
    else System.out.println("Reservasjon 2 ikke fullført");

    System.out.println(rom.toString());

  }
}
