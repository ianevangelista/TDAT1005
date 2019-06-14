import dyrehage.*;

class TestKlient {
  public static <SkandinaviskeRovdyr> void main(String[] args) {

    RovdyrFabrikk fabrikk = new RovdyrFabrikk();
    Hunnindivid binne = (Hunnindivid) fabrikk.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);

    System.out.println(binne.skrivUtInfo());
    binne.flytt("Bur 11");
    System.out.println(binne.skrivUtInfo());
    binne.leggTilKull(10);
    binne.leggTilNyttKull();
    System.out.println(binne.skrivUtInfo());


    /*Hannindivid hannbjørn = (Hannindivid) fabrikk.nyHannbjørn(20191002, "kasper", "20190902", "Bur");
    System.out.println(hannbjørn.skrivUtInfo());

    Hunnindivid tispe = (Hunnindivid) fabrikk.nyUlvetispe(20191002, "helene", "20190902", "Bur");
    System.out.println(tispe.skrivUtInfo());

    Hannindivid ulvehann = (Hannindivid) fabrikk.nyUlvehann(20191002, "zaim", "20190902", "Bur");
    System.out.println(ulvehann.skrivUtInfo());*/



  }
}
