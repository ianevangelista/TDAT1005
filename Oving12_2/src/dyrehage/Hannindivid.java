package dyrehage;

public class Hannindivid extends Individ{

    public Hannindivid(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, String fDato, String hanndyr, boolean farlig) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, hanndyr, farlig, 0);
    }

    @Override
    public int getAntKull() {
        return 0;
    }

    @Override
    public void leggTilKull(int antall) {
        super.leggTilKull(0);
    }

    @Override
    public void leggTilNyttKull() { }
}

