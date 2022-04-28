import java.util.HashSet;

public class Reseptor {
    // splitter opp sekvens til subsekvenser
    public static HashSet<String> opprettSubsekvenser(String sekvens) {
        HashSet<String> subsekvenser = new HashSet<>();
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

        return subsekvenser;
    }

}
