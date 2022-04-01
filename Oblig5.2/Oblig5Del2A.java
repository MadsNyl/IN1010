import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Del2A {
    private static Monitor2 monitor = new Monitor2();

    public static void main(String[] args) throws InterruptedException {
        final String DATAMAPPE = args[0];
        final String METAFIL = args[1];

        // henter antall filer i angitt mappe
        File mappe = new File(DATAMAPPE);
        int antallFiler = mappe.list().length - 1;

        // oppretter barriere for å få oversikt over når trådene er ferdig
        CountDownLatch barriere = new CountDownLatch(antallFiler);

        lastInnData(DATAMAPPE, METAFIL, barriere);
        barriere.await();
        System.out.println("Synkronisering ferdig.");
        slaaSammenAlleKart();
        System.out.println("Subsekvens med flest forekomster:");
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

    // slår sammen alle subsekvenskartene til det gjenstår ett kart igjen
    private static void slaaSammenAlleKart() {
        if (monitor.hentAntall() > 1) {
            HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(monitor.hentUt(), monitor.hentUt());
            monitor.settInn(nytt_kart);
            slaaSammenAlleKart();
        }
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
