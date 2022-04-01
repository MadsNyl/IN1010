import java.util.HashMap;
import java.util.Scanner;

abstract class Reseptor {
    // konverterer sekvens til subsekvenser
    public static HashMap<String, Subsekvens> lagSubsekvenser(Scanner input) {
        HashMap<String, Subsekvens> subsekvenser = new HashMap<>();
        int teller = 0;

        while (input.hasNextLine()) {
            String sekvens = input.nextLine();
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
                    subsekvenser.put(subsekvens, new Subsekvens(1, subsekvens));
                }
            }
            teller = 0;
        }

        return subsekvenser;
    }
}
