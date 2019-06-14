import static javax.swing.JOptionPane.*;
class Klientprogram {
  private static final String[] VALG = {"Registrer rom", "Reserver rom", "Alle rom", "Finn rom ved romnummer","Finn rom ved indeks", "Avslutt"};
  private static final int REG = 0;
	private static final int RES = 1;
	private static final int ALLE = 2;
	private static final int FINN = 3;
  private static final int INDEKS = 4;
  private static final int AVSLUTT = 5;

  public static void main(String[] args) {
    Konferansesenter konferansesenter = new Konferansesenter();

    while(true){
      int inputValg = showOptionDialog(null, "Velg ønsket operasjon", "Endre data", 0, PLAIN_MESSAGE, null, VALG, VALG[0]);
			if(inputValg == 5 || inputValg == -1)break;
			switch(inputValg){

        case REG:
        String romnummer = showInputDialog("Skriv inn romnummer: ");
        int romnummerLest = Integer.parseInt(romnummer);
        String kapasitet = showInputDialog("Skriv inn maks kapasitet: ");
        int kapasitetLest = Integer.parseInt(kapasitet);

        Rom nyttRom = new Rom(romnummerLest, kapasitetLest);
        boolean romRegistrert = konferansesenter.regRom(nyttRom);
        if(romRegistrert) showMessageDialog(null, "Rommet ble registrert!");
        else showMessageDialog(null, "Rommet ble ikke registrert");
        break;

        case RES:
        String navn = showInputDialog("Skriv inn fullt navn: ");
        String tlf = showInputDialog("Skriv inn telefonnummer: ");
        Kunde kunde = new Kunde(navn, tlf);

        String tidFra = showInputDialog("Skriv inn dato og tid slik: ÅÅÅÅMMDDTTMM reservasjonen starter");
        long tidFraLest = Long.valueOf(tidFra);
        Tidspunkt fra = new Tidspunkt(tidFraLest);

        String tidTil = showInputDialog("Skriv inn dato og tid slik: ÅÅÅÅMMDDTTMM reservasjonen slutter ");
        long tidTilLest = Long.valueOf(tidTil);
        Tidspunkt til = new Tidspunkt(tidTilLest);
        Reservasjon reservasjon = new Reservasjon(fra, til, kunde);

        String antPers = showInputDialog("Skriv inn antall personer: ");
        int antPersLest = Integer.parseInt(antPers);

        boolean romReservert = konferansesenter.reserverRom(reservasjon, antPersLest);
        if(romReservert) showMessageDialog(null, "Rommet som er reservert er rom: " + konferansesenter.getRomNummerReservert());
        else showMessageDialog(null, "Rommet ble ikke reservert");
        break;

        case ALLE:
        showMessageDialog(null, konferansesenter.toString());
        break;

        case FINN:
        String finnRom = showInputDialog("Skriv inn romnummer du ønsker info om: ");
        int finnRomLest = Integer.parseInt(finnRom);
        String utskrift = konferansesenter.finnRom(finnRomLest);
        if(utskrift != null) showMessageDialog(null, utskrift);
        else showMessageDialog(null, "Rommet finnes ikke");
        break;

        case INDEKS:
        String finnRomIndeks = showInputDialog("Skriv inn indeks du ønsker info om: ");
        int finnRomLestIndeks = Integer.parseInt(finnRomIndeks);
        String indeksUtskrift = konferansesenter.finnRomIndeks(finnRomLestIndeks);
        if(indeksUtskrift != null) showMessageDialog(null, indeksUtskrift);
        else showMessageDialog(null, "Rommet finnes ikke");

        case AVSLUTT:
        break;
        default:
      }
    }
  }
}
