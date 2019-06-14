import java.io.*;
import java.util.*;

class TestTribune implements Serializable{

  public static Tribune[] lesFraFil(String filnavn){
    try {
        FileInputStream fis = new FileInputStream(filnavn);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Tribune[] liste = (Tribune[])ois.readObject();
        ois.close();

        int teller = 0;
        for(int i = 0; i < liste.length; i++){
            teller++;
        }
        if(teller == 0) return null;
        return liste;
    } catch(Exception e){
        System.out.println("Her gikk det galt" + e.getMessage());
    }
    return null;
}

public static void skrivTilFil(String filnavn, Tribune[] tribune){
  try {
      FileOutputStream fos = new FileOutputStream(filnavn);
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      for (int i = 0; i < tribune.length; i++) {
        oos.writeObject(tribune[i]);
      }
      oos.close();
  } catch(Exception e){
      System.out.println("Her gikk det galt" + e.getMessage());
  }
}

  public static void main(String[] args) {
    Staa staa1 = new Staa("staa1", 100, 50, 50);
    Staa staa2 = new Staa("staa2", 200, 50, 100);
    Sitte sitte = new Sitte("sitte", 500, 100, 5);
    VIP vip = new VIP("vip", 100, 300, 10);

    Tribune[] liste = {staa1, staa2, sitte, vip};
    int antallBilletter = 10;
    String[] navn = {"Ian", "Nikolai", "Zaim", "Juni", "Abbi"};

    Billett[] billetter1 = staa1.kjøpBilletter(antallBilletter);
    //System.out.println(staa1.toString());

    Billett[] billetter2 = staa2.kjøpBilletter(navn);
    //System.out.println(staa2.toString());*/

    Billett[] billetter3 = sitte.kjøpBilletter(antallBilletter);
    //System.out.println(sitte.toString());

    Billett[] billetter4 = sitte.kjøpBilletter(navn);
    //System.out.println(sitte.toString());

    Billett[] billetter5 = vip.kjøpBilletter(antallBilletter);
    //System.out.println(vip.toString());

    Billett[] billetter6 = vip.kjøpBilletter(navn);
    //System.out.println(vip.toString());

    /*for (int i = 0; i < navn.length; i++) {
      System.out.println(billetter6[i].toString());
    }*/
    String filnavn = "test.ser";
    skrivTilFil(filnavn, liste);
    Arrays.sort(liste, new SortByInntekt());
    for(Tribune t : liste){
      System.out.println(t.toString());
    }
  }
}

class SortByInntekt implements Comparator<Tribune> {
  public int compare(Tribune a, Tribune b){
    if(a.finnInntekt() < b.finnInntekt()) return -1;
    else if(a.finnInntekt() == b.finnInntekt()) return 0;
    else return 1;
  }
}
