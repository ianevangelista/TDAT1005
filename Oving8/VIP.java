class VIP extends Sitte{
  private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

  public VIP(String navn, int kapasitet, int pris, int antRad){
    super(navn, kapasitet, pris, antRad);
    tilskuer = new String[antRad][(kapasitet/antRad)];
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
    int seterOpptatt = 0;
    for (int i = 0; i < tilskuer[0].length; i++) {
      for (int j = 0; j < tilskuer[1].length; j++) {
        if(tilskuer[i][j] != null) {
          seterOpptatt++;
        }
      }
    }
    return seterOpptatt;
  }

  public int finnInntekt(){
    return (finnAntallSolgteBilletter()*getPris());
  }

  public Billett[] kjøpBilletter(int antBilletter){
    return null;
  }

  public Billett[] kjøpBilletter(String[] navn){
    int antBilletter = navn.length;
    int antRad = tilskuer[0].length;
    int maksSeter = tilskuer[1].length;
    int antLedige = 0;
    int rad = 0;
    int indeks = 0;

    if((getKapasitet()-finnAntallSolgteBilletter()) < antBilletter) return null;
    for (int i = 0; i < antBilletter; i++) {
      if(navn[i] == null || antBilletter > maksSeter) return null;
    }
    Billett[] billetter = new SitteplassBillett[antBilletter];
    boolean ledigRad = false;

    while(!(ledigRad)){
      for (int j = 0; j < maksSeter; j++) {
        if(tilskuer[rad][j] == null){
          antLedige++;
        }
      }
      if(antLedige >= antBilletter){
        ledigRad = true;
      }else rad++;
    }

    int teller = 0;
    for (int k = 0; k < maksSeter; k++) {
      if(tilskuer[rad][k] == null){
        tilskuer[rad][k] = navn[indeks];
        billetter[teller]= new SitteplassBillett(getNavn(), getPris(), rad, k);
        teller++;
        indeks++;
        if(indeks >= antBilletter) break;
      }
    }
    return billetter;
  }
}
