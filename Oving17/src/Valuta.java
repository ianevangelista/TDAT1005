class Valuta {
    private String valutanavn;
    private double kursMotNOK;
    private int enhet;

    public Valuta(String valutanavn, double kursMotNOK, int enhet){
        this.valutanavn = valutanavn;
        this.kursMotNOK = kursMotNOK;
        this.enhet = enhet;
    }

    public String getValutanavn(){
        return this.valutanavn;
    }

    public double getKurs(){
        return this.kursMotNOK;
    }

    public int getEnhet(){
        return this.enhet;
    }
}
