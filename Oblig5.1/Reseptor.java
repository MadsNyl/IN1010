import java.util.HashSet;

public class Reseptor {
 
    private final int LENGDE = 3;
    private String sekvens;
    // beholder for subsekvensene
    HashSet<String> subsekvenser = new HashSet<>();

    // konstruktør
    public Reseptor(String sekvens) {
        this.sekvens = sekvens;
    }

    // henter lengde
    public int hentLengde() { return LENGDE; }

    // henter sekvens
    public String hentSekvens() { return sekvens; }

    // henter subsekvenser
    public HashSet<String> hentSubsekvenser() { return subsekvenser; }

    // splitter opp sekvens til subsekvenser
    public void opprettSubsekvenser() {
        int teller = 0;
        for (int i = 0; i < sekvens.length(); i++) {
            teller++;
            if (teller <= sekvens.length() - 2) {
                String subsekvens = "";
                char del1 = sekvens.charAt(i);
                subsekvens += del1;
                char del2 = sekvens.charAt(i + 1);
                subsekvens += del2;
                char del3 = sekvens.charAt(i + 2);
                subsekvens += del3;
                subsekvenser.add(subsekvens);
            }
        }
    }

}
