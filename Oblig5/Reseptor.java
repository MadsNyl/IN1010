import java.util.HashSet;

public class Reseptor {
    /* 
        Reseptor er et protein som gjenkjenner viruset. En reseptor vil ta imot en sekvens,
        som er av datatype streng. Klassen vil dele opp den gitte strengen i flere subsekvenser.
        En subsekvens er av lengden 3. Klassen vil returnere en mengde med subsekvenser av sekvensen.
    */

    private final int LENGDE = 3;
    private String sekvens;

    // oppretter en mengde som skal fungere som beholder for subsekvensene
    HashSet<String> subsekvenser = new HashSet<>();

    // konstruktoer
    public Reseptor(String sekvens) {
        this.sekvens = sekvens;
    }

    // hent sekvenslengde
    public int hentLengde() { return LENGDE; }

    // hent sekvens
    public String hentSekvens() { return sekvens; }

    // hent subsekvenser
    public HashSet<String> hentSubsekvenser() { return subsekvenser; }

    // henter lengde på subsekvens mengde
    public int hentSubsekvenserLengde() { return subsekvenser.size(); }

    // splitt opp sekvens til subsekvens og opprett mengde
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

    // overskrider toString metode
    @Override
    public String toString() {
        String data = "";
        for (String subsekvens : subsekvenser) {
            data += subsekvens + " ";
        }

        return data;
    }



}
