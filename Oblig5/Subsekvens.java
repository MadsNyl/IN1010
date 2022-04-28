public class Subsekvens {
    /*
        Subsekvens bestaar av en streng av en subsekvens, og et antall som er 1.
    */

    private String subsekvens;
    private int antall = 1;

    public Subsekvens (String subsekvens) {
        this.subsekvens = subsekvens;
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return subsekvens + " antall: " + antall;
    }
}
