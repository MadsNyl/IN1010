import javax.print.DocFlavor.STRING;

public class Subsekvens {
    private int antall;
    public final String SUBSEKVENS;

    public Subsekvens(int antall, String subsekvens) {
        this.antall = antall;
        this.SUBSEKVENS = subsekvens;
    }

    // henter antall
    public int hentAntall() { return antall; }

    // endrer antall
    public void endreAntall(int antall) { this.antall = antall; } 

    // overskrider toString metode
    @Override
    public String toString() { return "(" + SUBSEKVENS + "," + antall + ")"; }

}
