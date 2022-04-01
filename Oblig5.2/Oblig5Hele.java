import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Hele {
    public static void main(String[] args) throws InterruptedException {
        final String DATAMAPPE = "TestDataLiten";
        final String METAFIL = "metadata.csv";

        Monitor2 syke = new Monitor2(); 
        Monitor2 friske = new Monitor2();

        // henter antall filer i mappe
        File mappe = new File(DATAMAPPE);
        int antallFiler = mappe.list().length - 1;

        final int ANTALL_FLETTE_TRAADER = antallFiler - 1;

        // oppretter barriere for å få oversikt over når trådene er ferdig
        CountDownLatch barriere1 = new CountDownLatch(antallFiler);
        CountDownLatch barriere2 = new CountDownLatch(ANTALL_FLETTE_TRAADER);

        lastInnData(DATAMAPPE, METAFIL, barriere1, syke, friske);

        // // flettetråder for monitoren til de friske
        // for (int i = 0; i < ANTALL_FLETTE_TRAADER; i++) {
        //     Runnable fletteTrad = new FletteTrad(syke, barriere2);
        //     Thread traad = new Thread(fletteTrad);
        //     traad.start();
        // }
        // // flettetråder for monitoren til de syke
        // for (int i = 0; i < ANTALL_FLETTE_TRAADER; i++) {
        //     Runnable fletteTrad = new FletteTrad(friske, barriere2);
        //     Thread traad = new Thread(fletteTrad);
        //     traad.start();
        // }

        barriere1.await();
        System.out.println("Alle lesetråder er ferdige.");
        // barriere2.await();

        System.out.println("Alle tråder er ferdig, og synkronisert.");
        System.out.println("Antall kart i beholder med syke: " + syke.hentAntall());
        skrivUtSubsekvensMedFlestAntallForekomster(syke);

        System.out.println("Antall kart i beholder med friske: " + friske.hentAntall());
        skrivUtSubsekvensMedFlestAntallForekomster(friske);

    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData(String mappe, String metafil, CountDownLatch barriere, Monitor2 syke, Monitor2 friske) throws InterruptedException {
        try { 
            Scanner sc = new Scanner(new File(mappe + "/" + metafil));
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().strip().split(",");
                // sjekker linjen om tråden skal legge filen inn i monitoren for syke eller friske
                if (linje[1].equals("True")) { 
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], syke, barriere);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                }
                else if (linje[1].equals("False")) { 
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], friske, barriere);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                }
                
            }
        } catch (FileNotFoundException e) { System.out.println("Fant ikke fil."); }
    }

    // skriver ut subsekvens med flest antall forekomster
    private static void skrivUtSubsekvensMedFlestAntallForekomster(Monitor2 monitor) {
        if (monitor.hentAntall() == 1) {
            HashMap<String, Subsekvens> kart = monitor.hentUt();
            int antall = 0;
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() > antall) antall = subsekvens.hentAntall();
            for (Subsekvens subsekvens : kart.values()) if (subsekvens.hentAntall() == antall) System.out.println(subsekvens);
        }
    }
}
