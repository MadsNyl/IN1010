import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Testprogram {
    private static final String FILVEI = "TestDataLike/";
    private static final String METAFIL = "metadata.csv";
    private static SubsekvensRegister register = new SubsekvensRegister();
    
    public static void main(String[] args) {
        lastInnData();
        slaaSammenAlleKart();
        // register.visAlleSubsekvensKart();
    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData() {
        try { 
            Scanner sc = new Scanner(new File(FILVEI + METAFIL));
            while (sc.hasNextLine()) {
                String linje = sc.nextLine().strip();
                HashMap<String, Subsekvens> kart = SubsekvensRegister.lesFil(FILVEI + linje);
                register.settInn(kart);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
            e.printStackTrace();
        }
    }

    // slår sammen alle subsekvenskartene til det gjenstår ett kart igjen
    private static void slaaSammenAlleKart() {
        if (register.hentAntall() > 1) {
            HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(register.hentKart(0), register.hentKart(1));
            register.fjern();
            register.fjern();
            register.settInn(nytt_kart);
            slaaSammenAlleKart();
        }
    }

}
