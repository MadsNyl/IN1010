import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Hele {
    public static void main(String[] args) throws InterruptedException {
        final String DATAMAPPE = "TestData";
        final String METAFIL = "metadata.csv";

        Monitor2 syke = new Monitor2(); 
        Monitor2 friske = new Monitor2();

        // henter antall filer i mappe
        File mappe = new File(DATAMAPPE);
        int antallFiler = mappe.list().length - 1;

        // må opprette funksjon for å sjekke om det skal være en eller to mindre tråder.
        final int ANTALL_FLETTE_TRAADER = antallFiler - 2;

        // oppretter barriere for å få oversikt over når trådene er ferdig
        CountDownLatch barriere1 = new CountDownLatch(antallFiler);
        CountDownLatch barriere2 = new CountDownLatch(ANTALL_FLETTE_TRAADER);

        // antall syke og friske
        int antallSyke = 0;
        int antallFriske = 0;

        // lastInnData(DATAMAPPE, METAFIL, barriere1, syke, friske);

        try {
            Scanner sc = new Scanner(new File(DATAMAPPE + "/" + METAFIL));
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().split(",");
                
                if (linje[1].equals("True")) {
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], syke, barriere1);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                    antallSyke++;
                } else {
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], friske, barriere1);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                    antallFriske++;
                }                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // flettetråder for monitoren til de syke
        if (antallSyke > 1) {
            for (int i = 0; i < antallSyke - 1; i++) {
                Runnable fletteTraad = new FletteTrad(syke, barriere2, antallSyke);
                Thread traad = new Thread(fletteTraad);
                traad.start();
            }
        } else {
            Runnable fletteTraad = new FletteTrad(syke, barriere2, antallSyke);
            Thread traad = new Thread(fletteTraad);
            traad.start();
        }
        

        System.out.println("Antall syke: " + antallSyke);
        System.out.println("Antall friske: " + antallFriske);

        // flettetråder for monitoren til de friske
        if (antallFriske > 1) {
            for (int i = 0; i < antallFriske - 1; i++) {
                Runnable fletteTraad = new FletteTrad(friske, barriere2, antallFriske);
                Thread traad = new Thread(fletteTraad);
                traad.start();
            }
        } else {
            Runnable fletteTraad = new FletteTrad(friske, barriere2, antallFriske);
            Thread traad = new Thread(fletteTraad);
            traad.start();
        }


        barriere1.await();
        System.out.println("Lesetraader er ferdigge");
        barriere2.await();

        System.out.println("Alle tråder er ferdig, og synkronisert.");
        System.out.println("Subsekvenser med flest forekomster hos de syke er følgende: ");
        skrivUtSubsekvensMedFlestAntallForekomster(syke);
        System.out.println("\nSubsekvenser med flest forekomster hos de syke er følgende: ");
        skrivUtSubsekvensMedFlestAntallForekomster(friske);

    }

    // leser filer opplistet i metadata.csv
    private static void lastInnData(String mappe, String metafil, CountDownLatch barriere, Monitor2 syke, Monitor2 friske) throws InterruptedException {
        try { 
            Scanner sc = new Scanner(new File(mappe + "/" + metafil));
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().split(",");
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
