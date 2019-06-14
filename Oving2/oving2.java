import java.util.*;

class Bord {
  private String[] navn;
  private int antBord;

  public Bord(int antBord){
    if(antBord <= 0) throw new IllegalArgumentException("Ikke gyldig");
    this.antBord = antBord;
    this.navn = new String[antBord];
  }

  public int antBord(){
    return antBord;
  }

  public String getNavn(int i){
    return navn[i];
  }

  public int antLedige(){
    int antLedige = 0;
    for(int i = 0; i < navn.length; i++){
      if(navn[i] == null) antLedige++; //hvis element er null, er bordet ledig
    }
    return antLedige;
  }

  public int antOpptatte(){
    int antOpptatte = 0;
    for(int i = 0; i < navn.length; i++){
      if(navn[i] != null) antOpptatte++; //hvis element ikke null, er bordet opptatt
    }
    return antOpptatte;
  }

  public boolean reserver(String resNavn, int antResBord){
    if(resNavn == null || antResBord > antLedige()) return false;
    if(antBord() == antOpptatte()) return false;

    for(int i = 0; i < navn.length; i++){
      if(navn[i] == null){
        navn[i] = resNavn;
        antResBord--;
        if(antResBord == 0) return true;
      }
    }
    return false;
  }

  public int[] bordnummer(String resNavn){
    if(resNavn == null) throw new IllegalArgumentException("Ikke gyldig");
    int[] bordnummer = new int[navn.length]; //tabell med bordnummer
    int teller = 0;
    for (int i = 0; i < navn.length; i++) { //kopierer over alle nummer som matcher navnet
      if(navn[i] != null && navn[i].toLowerCase().equals(resNavn.toLowerCase())){
        bordnummer[teller] = i;
        teller++;
      }
    }
    if(teller == 0) throw new IllegalArgumentException("Ingen reservasjon med dette navnet");

    int[] kopi = new int[teller]; //kopi av tabell hvis original tabell ikke er full
    for(int k = 0; k < teller; k++) {
      kopi[k] = bordnummer[k];
    }
    return kopi;
  }

  public void frigiBord(int[] bordVasket){
    int bordnummer = 0;
    for (int i = 0; i < bordVasket.length; i++) {
      bordnummer = bordVasket[i];
      navn[bordnummer] = null;
    }
  }
}

class Restaurant {
  private String navn;
  private int etableringsar;
  private Bord bord;

  public Restaurant(String navn, int etableringsar, Bord bord) {
    this.navn = navn;
    this.etableringsar = etableringsar;
    this.bord = bord;
  }

  public String getNavn(){
    return navn;
  }

  public void setNavn(String nyttNavn) {
    this.navn = nyttNavn;
  }

  public int getEtablering() {
    return etableringsar;
  }

  public int getAlder(){
    int detteAr = Calendar.getInstance().get(Calendar.YEAR);
    int alder = detteAr - etableringsar;
    return alder;
  }

  public int antLedige(){
    int antLedige = bord.antLedige();
    return antLedige;
  }

  public int antOpptatte(){
    int antOpptatte = bord.antOpptatte();
    return antOpptatte;
  }

  public boolean reserverBord(String resNavn, int antResBord){
    boolean ledig = bord.reserver(resNavn, antResBord);
    return ledig;
  }

  public int[] bordnummer(String resNavn){
    int[] bordnummer = bord.bordnummer(resNavn);
    return bordnummer;
  }

  public void frigiBord(int[] bordVasket){
    bord.frigiBord(bordVasket);
  }
}

class oving2{
  public static void main(String[] args) {
    Bord bord = new Bord(10);
    Restaurant minRest = new Restaurant("MinRestaurant", 1999, bord);

    boolean reservert = minRest.reserverBord("Ian", 9);
    if(reservert) System.out.println("Reservering godkjent");
    else System.out.println("Reservering ikke godkjent");
    System.out.println("Antall opptatte bord: " + minRest.antOpptatte());
    //System.out.println(bord.getNavn(4));

    boolean reservert2 = minRest.reserverBord("Ian", 3);
    if(reservert2) System.out.println("Reservering godkjent");
    else System.out.println("Reservering ikke godkjent");

    int[] mineBord = minRest.bordnummer("ian");
    System.out.println("Dine bord: ");
    for (int i = 0; i < mineBord.length; i++) {
      System.out.println(mineBord[i]);
    }
    int[] bordVasket = {0,1,2,3,4};
    //minRest.frigiBord(bordVasket);
  }
}
