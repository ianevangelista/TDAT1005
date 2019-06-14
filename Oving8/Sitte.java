class Sitte extends Tribune{
  private int [] antOpptatt;  // tabellstørrelse: antall rader

  public Sitte(String navn, int kapasitet, int pris, int antRad){
    super(navn, kapasitet, pris);
    antOpptatt = new int[antRad];
  }

  public String getNavn(){
    return super.getNavn();
  }

  public int getKapasitet(){
    return super.getKapasitet();
  }

  public int getPris(){
    return super.getPris();
  }

  public int finnAntallSolgteBilletter(){
    int seterPrRad = (getKapasitet()/antOpptatt.length);
    int seterOpptatt = 0;
    for (int i = 0; i < antOpptatt.length; i++) {
      seterOpptatt += antOpptatt[i];
    }
    return seterOpptatt;
  }

  public int finnInntekt(){
    return (finnAntallSolgteBilletter()*getPris());
  }

  public Billett[] kjøpBilletter(int antBilletter){
    int maksPrRad = (getKapasitet()/antOpptatt.length);
    int[] plass = new int[antBilletter];
    int rad = 0;
    if(antBilletter <= 0 )throw new IllegalArgumentException("Ikke gyldig");
    if(antBilletter > getKapasitet() || antBilletter > (getKapasitet()-finnAntallSolgteBilletter()) || antBilletter > maksPrRad) return null;
    Billett[] billetter = new SitteplassBillett[antBilletter];

    for (int i = 0; i < antOpptatt.length; i++) {
      if(!(antOpptatt[i] >= maksPrRad)){
        for (int j = 0;  j < antBilletter; j++) {
          plass[j] = antOpptatt[i] + 1;
        }
        antOpptatt[i] += antBilletter;
        rad = i;
      }
      for (int k = 0; k < antBilletter; k++) {
        Billett nyBillett = new SitteplassBillett(getNavn(), getPris(), rad, plass[k]);
        billetter[k] = nyBillett;
      }
      return billetter;
    }
    return null;
  }

  public Billett[] kjøpBilletter(String[] navn){
    for (int i = 0; i < navn.length; i++) {
      if(navn[i].equals(null)) return null;
    }
    Billett[] billetter = new SitteplassBillett[navn.length];
    int maksPrRad = (getKapasitet()/antOpptatt.length);
    int[] plass = new int[navn.length];
    int rad = 0;

    for (int i = 0; i < antOpptatt.length; i++) {
      if(!(antOpptatt[i] >= maksPrRad)){
        for (int j = 0;  j < navn.length; j++) {
          plass[j] = antOpptatt[i] + 1;
        }
        antOpptatt[i] += navn.length;
        rad = i;
      }
      for (int k = 0; k < navn.length; k++) {
        Billett nyBillett = new SitteplassBillett(getNavn(), getPris(), rad, plass[k]);
        billetter[k] = nyBillett;
      }
      return billetter;
    }
    return null;
  }
}
