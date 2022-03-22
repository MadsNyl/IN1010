import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Testprogram {
    private static final String FILVEI = "TestData/";
    private static final String METAFIL = "metadata.csv";
    private static SubsekvensRegister register = new SubsekvensRegister();
    
    public static void main(String[] args) {
        lastInnData();
        System.out.println(register.hentAntall());
        System.out.println("--------------------");
        slaaSammenAlleKart();
        register.visAlleSubsekvensKart();
        System.out.println("--------------------");
        System.out.println(register.hentAntall());
    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData() {
        try { 
            Scanner sc = new Scanner(new File(FILVEI + METAFIL));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().strip().split(",");
                HashMap<String, Subsekvens> kart = SubsekvensRegister.lesFil(FILVEI + linje[0]);
                register.settInn(kart);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
            e.printStackTrace();
        }
    }

    // slår sammen alle subsekvenskartene til det gjenstår ett kart igjen
    private static void slaaSammenAlleKart() {
        for (int i = 0; i < register.hentAntall(); i++) {
            if (i + 1 < register.hentAntall()) {
                HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(register.hentKart(i), register.hentKart(i + 1));
                register.fjern();
                register.fjern();
                register.settInn(nytt_kart);
            }
        }
    }

}
