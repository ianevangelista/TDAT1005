abstract class Tribune {
  private final String tribunenavn;
  private final int kapasitet;
  private final int pris;

  public Tribune(String tribunenavn, int kapasitet, int pris){
    this.tribunenavn = tribunenavn;
    this.kapasitet = kapasitet;
    this.pris = pris;
  }

  public String getNavn(){
    return this.tribunenavn;
  }

  public int getKapasitet(){
    return this.kapasitet;
  }

  public int getPris(){
    return this.pris;
  }

  public abstract int finnAntallSolgteBilletter();

  public abstract int finnInntekt();

  public abstract Billett[] kjøpBilletter(int antBilletter);

  public abstract Billett[] kjøpBilletter(String[] navn);

  public String toString(){
    return "Navn: " + this.tribunenavn + "\nKapasitet: " + this.kapasitet + "\nPris: " + getPris() + "\nAntall solgte billletter: " + finnAntallSolgteBilletter() + "\nInntekt: " + finnInntekt();
  }

}
