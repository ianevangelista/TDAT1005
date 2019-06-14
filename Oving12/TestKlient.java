import dyrehage.*;

class TestKlient {
  public static void main(String[] args) {

    RovdyrFabrikk fabrikk = new RovdyrFabrikk();
    SkandinaviskeRovdyr binne = fabrikk.nyBinne(20191002, "hei", 20190902, "Bur");
    System.out.println(binne.skrivUtInfo());
  }
}
