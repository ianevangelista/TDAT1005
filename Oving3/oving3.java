import static javax.swing.JOptionPane.*;
class Student {
  private final String navn;
  private int antOppg;

  public Student(String navn, int antOppg){
    if(navn == null || antOppg < 0) throw new IllegalArgumentException("Ikke gyldig");
    this.navn = navn;
    this.antOppg = antOppg;
  }

  public String getNavn(){
    return navn;
  }

  public int getAntOppg(){
    return antOppg;
  }

  public void setAntOppg(int nyAntOppg){
    if(nyAntOppg < 0) throw new IllegalArgumentException("Ikke gyldig");
    this.antOppg = nyAntOppg;
  }

  public String toString(){
    return "Navn: " + navn + ", antall oppgaver løst: " + antOppg;
  }
}

class OppgaveOversikt {
  private Student[] studenter = new Student[5];
  private int antStud = 0;

  public boolean regNyStudent(String studentNavn){
    if(studentNavn == null)return false;
    for (int i = 0; i < antStud; i++) {
      if(studentNavn.toLowerCase().equals(studenter[i].getNavn().toLowerCase())){
        return false;
      }
    }
    if(antStud == studenter.length) utvidTabell(); //utvider hvis full
    Student nyStudent = new Student(studentNavn, 0); //nytt objekt
    studenter[antStud] = nyStudent; //siste element lik nytt objekt
    antStud++; //øker
    return true;
  }

  private void utvidTabell(){ //privat hjelpe-metode
    Student[] nyTabell = new Student[studenter.length + 5];
    for (int i = 0; i < nyTabell.length; i++) {
      nyTabell[i] = studenter[i];
    }
    studenter = nyTabell;
  }

  public int finnAntStud(){
    return antStud;
  }

  public int finnAntOppgaver(String studentNavn){
    if(studentNavn == null)return -1;
    for (int i = 0; i < antStud; i++) {
      if(studentNavn.toLowerCase().equals(studenter[i].getNavn().toLowerCase())){ //leter gjennom tabell
        return studenter[i].getAntOppg();
      }
    }
    return -1;
  }

  public boolean okAntOppg(String studentNavn, int okning){ //øker antall oppgaver
    int oppgGodkjent = 0;
    int nyOppgSum = 0;
    if(studentNavn == null || okning < 1) return false;
    for (int i = 0; i < antStud; i++) {
      if(studentNavn.toLowerCase().equals(studenter[i].getNavn().toLowerCase())){
        oppgGodkjent = studenter[i].getAntOppg(); //henter gammelt antall
        nyOppgSum = studenter[i].getAntOppg() + okning; //legger til gammelt + nytt antall
        studenter[i].setAntOppg(nyOppgSum); //setter nytt antall
        return true;
      }
    }
    return false;
  }

  public String[] finnAlleNavn() {
    String[] alleStudenter = new String[antStud]; //tabell med alle navn
    for (int i = 0; i < antStud; i++) {
      alleStudenter[i] = studenter[i].getNavn(); //kopierer over navn
    }
    return alleStudenter;
  }

  public String toString(){
    String utskrift = "";
    for (int i = 0; i < antStud; i++) {
      utskrift += studenter[i].toString() + "\n";
    }
    return utskrift;
  }
}

class GodkjenningBGS {
  public final String NY_STUDENT = "Ny student";
  public final String AVSLUTT = "Avslutt";
  private String[] muligeValg = {NY_STUDENT, AVSLUTT};  // første gang, ingen studenter registrert

  private OppgaveOversikt oversikt;
  public GodkjenningBGS(OppgaveOversikt oversikt) {
    this.oversikt = oversikt;
  }

  /**
   *
   * Metoden leser inn valget som en streng, og returnerer den.
   * Valget skal være argument til metoden utførValgtOppgave().
   * Hvis programmet skal avsluttes, returneres null.
   */
  public String lesValg() {
    int antStud = oversikt.finnAntStud();
    String valg = (String) showInputDialog(null, "Velg fra listen, " + antStud + " studenter:",  "Godkjente oppgaver",
             DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
    if (AVSLUTT.equals(valg)) {
      valg = null;
    }
    return valg;
  }

  /**
   *
   * Metode som sørger for at ønsket valg blir utført.
   */
  public void utførValgtOppgave(String valg) {
    if (valg != null && !valg.equals(AVSLUTT)) {
      if (valg.equals(NY_STUDENT)) {
        registrerNyStudent();
      } else {
        registrerOppgaver(valg);  // valg er navnet til studenten
      }
    }
  }

  /**
   *
   * Metoden registrere ny student.
   * Hvis student med dette navnet allerede eksisterer, skjer ingen registrering.
   * Resultatet av operasjonen skrives ut til brukeren.
   */
  private void registrerNyStudent() {
    String navnNyStud = null;
    do {
      navnNyStud = showInputDialog("Oppgi navn: ");
    } while (navnNyStud == null);

    navnNyStud = navnNyStud.trim();
    if (oversikt.regNyStudent(navnNyStud)) {
      showMessageDialog(null, navnNyStud + " er registrert.");
      String[] alleNavn = oversikt.finnAlleNavn();
      String[] nyMuligeValg = new String[alleNavn.length + 2];
      for (int i = 0; i < alleNavn.length; i++) {
        nyMuligeValg[i] = alleNavn[i];
      }
      nyMuligeValg[alleNavn.length] = NY_STUDENT;
      nyMuligeValg[alleNavn.length + 1] = AVSLUTT;
      muligeValg = nyMuligeValg;
      } else  {
        showMessageDialog(null, navnNyStud + " er allerede registrert.");
      }
    }

    /**
     *
     * Metoden registrerer oppgaver for en navngitt student.
     * Brukerinput kontrolleres ved at det må kunne tolkes som et tall.
     * Registreringsmetoden (i klassen Student) kan kaste unntaksobjekt IllegalArgumentException.
     * Dette fanges også opp. I begge tilfeller må brukeren gjenta inntasting inntil ok data.
     * Endelig skrives det ut en melding om antall oppgaver studenten nå har registrert.
     */
    private void registrerOppgaver(String studNavn) {
      String melding = "Oppgi antall nye oppgaver som skal godkjennes for " + studNavn +": ";
      int antOppgØkning = 0;
      boolean registrert = false;
      do { // gjentar inntil registrering aksepteres av objektet oversikt
        try {
          antOppgØkning = lesHeltall(melding);
          oversikt.okAntOppg(studNavn, antOppgØkning);  // kan ikke returnere false, pga navn alltid gyldig
          registrert = true; // kommer hit bare dersom exception ikke blir kastet
        } catch (IllegalArgumentException e) {  // kommer hit hvis studenter får negativt antall oppgaver
          melding = "Du skrev " + antOppgØkning + ". \nIkke godkjent økning for " + studNavn + ". Prøv igjen: ";
        }
      } while (!registrert);

      melding = "Totalt antall oppgaver registrert på " + studNavn + " er " + oversikt.finnAntOppgaver(studNavn) + ".";
      showMessageDialog(null, melding);
    }

    /* Hjelpemetode som går i løkke inntil brukeren skriver et heltall. */
    private int lesHeltall(String melding) {
      int tall = 0;
      boolean ok = false;
      do {  // gjentar inntil brukerinput kan tolkes som tall
        String tallLest = showInputDialog(melding);
        try {
          tall = Integer.parseInt(tallLest);
          ok = true;
        } catch (Exception e) {
          showMessageDialog(null, "Kan ikke tolke det du skrev som tall. Prøv igjen. ");
        }
      } while (!ok);
      return tall;
    }
}


  /**
   * Hovedprogrammet. Går i løkke og lar brukeren gjøre valg.
   */
class oving3 {
    public static void main(String[] args) {

    OppgaveOversikt oversikt = new OppgaveOversikt();
    GodkjenningBGS bgs = new GodkjenningBGS(oversikt);

    String valg = bgs.lesValg();
    while (valg != null) {
      bgs.utførValgtOppgave(valg);
      valg = bgs.lesValg();
    }

    /* Prøver toString() */
    System.out.println("\nHer kommer informasjon om alle studentene: ");
    System.out.println(oversikt);
  }
}
