import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {
    private static SubsekvensRegister register = new SubsekvensRegister();

    public static void main(String[] args) {
        final String DATAMAPPE = args[0];
        final String METAFIL = args[1];

        lastInnData(DATAMAPPE, METAFIL);
        slaaSammenAlleKart();
        skrivUtSubsekvensMedFlestAntallForekomster();
    }

    // leser inn data fra metafil
    private static void lastInnData(String mappe, String metafil) {
        try {
            Scanner input = new Scanner(new File(mappe + "/" + metafil));
            while (input.hasNextLine()) {
                String linje = input.nextLine().strip();
                HashMap<String, Subsekvens> kart = SubsekvensRegister.konverterTilSubsekvenser(mappe + "/" + linje);
                register.settInn(kart);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }
    }

    // slår sammen alle subsekvenskartene til det gjenstår ett kart igjen
    private static void slaaSammenAlleKart() {
        if (register.hentAntall() > 1) {
            HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(register.hentUt(), register.hentUt());
            register.settInn(nytt_kart);
            slaaSammenAlleKart();
        }
    }

    // skriver ut subsekvens med flest antall forekomster
    private static void skrivUtSubsekvensMedFlestAntallForekomster() {
        if (register.hentAntall() == 1) {
            HashMap<String, Subsekvens> kart = register.hentUt();
            int antall = 0;
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() > antall) antall = subsekvens.hentAntall();
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() == antall) System.out.println(subsekvens);
        }
    }

}
