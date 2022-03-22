public class Subsekvens {
    private final String SUBSEKVENS;
    private int antall;

    // konstruktør
    public Subsekvens(String SUBSEKVENS, int antall) {
        this.SUBSEKVENS = SUBSEKVENS;
        this.antall = antall;
    }

    // hent subsekvens
    public String hentSubsekvens() { return SUBSEKVENS; }

    // hent antall
    public int hentAntall() { return antall; }

    // endrer antall
    public void endreAntall(int antall) { if (antall > 0) this.antall = antall; }

    // øk antall med en
    public void okAntall() { antall++; }

    // reduser antall med en
    public void reduserAntall() {  if (antall > 0) antall--; }

    // overskrider toString metode
    @Override
    public String toString() { return "(" + SUBSEKVENS + "," + antall + ")"; }
}
