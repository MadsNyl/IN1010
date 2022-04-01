import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Del2B {
    private static Monitor2 monitor = new Monitor2();
    // private static final String DATAMAPPE = "TestDataLike";
    // private static final String METAFIL = "metadata.csv";
 
    public static void main(String[] args) throws InterruptedException {
        final String DATAMAPPE = args[0];
        final String METAFIL = args[1];

        // henter antall filer i mappe
        File mappe = new File(DATAMAPPE);
        int antallFiler = mappe.list().length - 1;

        final int ANTALL_FLETTE_TRAADER = antallFiler - 1;

        // oppretter barriere for å få oversikt over når trådene er ferdig
        CountDownLatch barriere1 = new CountDownLatch(antallFiler);
        CountDownLatch barriere2 = new CountDownLatch(ANTALL_FLETTE_TRAADER);


        lastInnData(DATAMAPPE, METAFIL, barriere1);

        for (int i = 0; i < ANTALL_FLETTE_TRAADER; i++) {
            Runnable fletteTrad = new FletteTrad(monitor, barriere2);
            Thread traad = new Thread(fletteTrad);
            traad.start();
        }


        barriere1.await();
        System.out.println("Alle lesetråder er ferdige.");
        barriere2.await();

        System.out.println("Alle tråder er ferdig, og synkronisert.");
        System.out.println("Antall kart i beholder: " + monitor.hentAntall());
        skrivUtSubsekvensMedFlestAntallForekomster();
    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData(String mappe, String metafil, CountDownLatch barriere) throws InterruptedException {
        try { 
            Scanner sc = new Scanner(new File(mappe + "/" + metafil));
            while (sc.hasNextLine()) {
                String linje = sc.nextLine().strip();
                Runnable leseTrad = new LeseTrad(mappe + "/" + linje, monitor, barriere);
                Thread traad = new Thread(leseTrad);
                traad.start();
            }
        } catch (FileNotFoundException e) { System.out.println("Fant ikke fil."); }
    }

    // skriver ut subsekvens med flest antall forekomster
    private static void skrivUtSubsekvensMedFlestAntallForekomster() {
        if (monitor.hentAntall() == 1) {
            HashMap<String, Subsekvens> kart = monitor.hentUt();
            int antall = 0;
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() > antall) antall = subsekvens.hentAntall();
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() == antall) System.out.println(subsekvens);
        }
    }

}
