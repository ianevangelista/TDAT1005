class Staa extends Tribune {
  private int antSolgteBilletter;

  public Staa(String navn, int kapasitet, int pris, int antSolgteBilletter){
    super(navn, kapasitet, pris);
    this.antSolgteBilletter = antSolgteBilletter;
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

  @Override
  public int finnAntallSolgteBilletter(){
    return antSolgteBilletter;
  }

  @Override
  public int finnInntekt(){
    return (finnAntallSolgteBilletter()*getPris());
  }

  @Override
  public Billett[] kjøpBilletter(int antBilletter){
    if(antBilletter <= 0 )throw new IllegalArgumentException("Ikke gyldig");
    if(antBilletter > getKapasitet() || antBilletter > (getKapasitet()-finnAntallSolgteBilletter())) return null;
    Billett[] billetter = new StaaplassBillett[antBilletter];
    for (int i = 0; i < antBilletter; i++) {
      Billett nyBillett = new StaaplassBillett(getNavn(), getPris());
      billetter[i] = nyBillett;
    }
    antSolgteBilletter += antBilletter;
    return billetter;
  }
  
  @Override
  public Billett[] kjøpBilletter(String[] navn){
    for (int i = 0; i < navn.length; i++) {
      if(navn[i].equals(null)) return null;
    }
    Billett[] billetter = new StaaplassBillett[navn.length];
    for (int i = 0; i < navn.length; i++) {
      Billett nyBillett = new StaaplassBillett(getNavn(), getPris());
      billetter[i] = nyBillett;
    }
    antSolgteBilletter += navn.length;
    return billetter;
  }
}
