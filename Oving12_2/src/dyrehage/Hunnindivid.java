package dyrehage;

public class Hunnindivid extends Individ{
    private int antKull;

    public Hunnindivid(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, String fDato, String hanndyr, boolean farlig, int antKull) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, hanndyr, farlig, antKull);
        this.antKull = antKull;
    }
}
