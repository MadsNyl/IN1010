import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Oblig5Del2B {
    private static final String FILVEI = "TestDataLitenLike/";
    private static final String METAFIL = "metadata.csv";
    private static Monitor2 monitor = new Monitor2();
    public static void main(String[] args) throws InterruptedException {
        lastInnData();
        for (int i = 1; i <= 3; i++){
            Runnable fletteTrad = new FletteTrad(FILVEI +  "fil" + i + ".csv", monitor);
            Thread traad = new Thread(fletteTrad);
            traad.start();
        }
    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData() throws InterruptedException {
        try { 
            Scanner sc = new Scanner(new File(FILVEI + METAFIL));
            while (sc.hasNextLine()) {
                String linje = sc.nextLine().strip();
                Runnable leseTrad = new LeseTrad(FILVEI + linje, monitor);
                Thread traad = new Thread(leseTrad);
                traad.start();
                // traad.join();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
            e.printStackTrace();
        }
    }

    // slår sammen alle subsekvenskartene til det gjenstår ett kart igjen
    private static void slaaSammenAlleKart() {
        if (monitor.hentAntall() > 1) {
            HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(monitor.hentKart(0), monitor.hentKart(1));
            monitor.fjern();
            monitor.fjern();
            monitor.settInn(nytt_kart);
            slaaSammenAlleKart();
        }
    }
}
