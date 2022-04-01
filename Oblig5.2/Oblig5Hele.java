import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Hele {
    // setter opp monitorer
    private static Monitor2 syke = new Monitor2(); 
    private static Monitor2 friske = new Monitor2();

    // setter opp data om antall filer i mappe
    private static File mappe;
    private static int antallFiler;

    // setter opp data om antall filer per monitor
    private static int antall_har_hatt_sykdom = 0;
    private static int antall_har_ikke_hatt_sykdom = 0;

    // setter opp barrierer
    private static CountDownLatch barriere1;
    private static CountDownLatch barriere2;
    public static void main(String[] args) throws InterruptedException {
        final String DATAMAPPE = args[0];
        final String METAFIL = args[1];

        // setter opp data
        setup(DATAMAPPE, METAFIL);
        // initialiserer og kjorer traader
        init(DATAMAPPE, METAFIL);
        // venter på at traader skal bli ferdige
        await();
        // sammenligner forekomster av subsekvenser
        sammenlignForekomsterData();
    }

    // setter opp data
    private static void setup(String datamappe, String metafil) {
        // henter antall filer i mappe
        mappe = new File(datamappe);
        antallFiler = mappe.list().length - 1;

        // finner antall filer for hver monitor
        try {
            Scanner sc = new Scanner(new File(datamappe + "/" + metafil));
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().strip().split(",");
                if (linje[1].equals("True")) antall_har_hatt_sykdom++;
                else if (linje[1].equals("False")) antall_har_ikke_hatt_sykdom++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // finner ut hvor mange flettetraader som trengs
        final int ANTALL_FLETTE_TRAADER;

        if (antall_har_hatt_sykdom > 1 && antall_har_ikke_hatt_sykdom > 1) ANTALL_FLETTE_TRAADER = antallFiler - 2;
        else ANTALL_FLETTE_TRAADER = antallFiler - 1;

        // setter opp barrierer
        barriere1 = new CountDownLatch(antallFiler);
        barriere2 = new CountDownLatch(ANTALL_FLETTE_TRAADER);
    }

    // initialiserer traader
    private static void init(String datamappe, String metafil) {
        // setter opp LeseTraader og kjorer de
        try {
            Scanner sc = new Scanner(new File(datamappe + "/" + metafil));
            while (sc.hasNextLine()) {
                String[] linje = sc.nextLine().split(",");
                
                if (linje[1].equals("True")) {
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], syke, barriere1);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                } else {
                    Runnable leseTrad = new LeseTrad(mappe + "/" + linje[0], friske, barriere1);
                    Thread traad = new Thread(leseTrad);
                    traad.start();
                }                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Flettetraader for monitoren til de syke
        if (antall_har_hatt_sykdom > 1) {
            for (int i = 0; i < antall_har_hatt_sykdom - 1; i++) {
                Runnable fletteTraad = new FletteTrad(syke, barriere2, antall_har_hatt_sykdom);
                Thread traad = new Thread(fletteTraad);
                traad.start();
            }
        } else {
            Runnable fletteTraad = new FletteTrad(syke, barriere2, antall_har_hatt_sykdom);
            Thread traad = new Thread(fletteTraad);
            traad.start();
        }

        // Flettetraader for monitoren til de friske
        if (antall_har_ikke_hatt_sykdom > 1) {
            for (int i = 0; i < antall_har_ikke_hatt_sykdom - 1; i++) {
                Runnable fletteTraad = new FletteTrad(friske, barriere2, antall_har_ikke_hatt_sykdom);
                Thread traad = new Thread(fletteTraad);
                traad.start();
            }
        } else {
            Runnable fletteTraad = new FletteTrad(friske, barriere2, antall_har_ikke_hatt_sykdom);
            Thread traad = new Thread(fletteTraad);
            traad.start();
        }
    }

    // venter paa at traader skal bli ferdige
    private static void await() throws InterruptedException {
        barriere1.await();
        System.out.println("Lesetraader er ferdige med aa konvertere og sette inn kart.");
        barriere2.await();
        System.out.println("Alle traader er ferdige, og program er synkronisert.");
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

    // finner subsekvenser som forekommer mye oftere hos syke enn hos friske
    private static void sammenlignForekomster(Monitor2 sykeMonitor, Monitor2 friskeMonitor) {
        System.out.println("Sammenligner forekomster hos de som har sykdommen med de som ikke har hatt sykdommen: \n");
        if (sykeMonitor.hentAntall() == 1) {
            HashMap<String, Subsekvens> syke = sykeMonitor.hentUt();
            HashMap<String, Subsekvens> friske = friskeMonitor.hentUt();
            ArrayList<Subsekvens> oftest = new ArrayList<>();
            int antall = 0;
            for (Subsekvens subsekvens : syke.values()) if (subsekvens.hentAntall() > antall) antall = subsekvens.hentAntall();
            for (Subsekvens subsekvens : syke.values()) if (subsekvens.hentAntall() == antall) oftest.add(subsekvens);

            ArrayList<Subsekvens> fjern = new ArrayList<>();

            for (Subsekvens element : oftest) {
                for (Subsekvens subsekvens : friske.values()) {
                    if (element.hentSubsekvens().equals(subsekvens.hentSubsekvens())) {
                        fjern.add(element);
                    }
                }
            }

            oftest.removeAll(fjern);

            for (Subsekvens subsekvens : oftest) System.out.println(subsekvens);
        }
    }

    /* finner subsekvenser hos de som har hatt sykdommen, 
    med antall forekomster 7 flere ganger hos de som ikke har hatt sykdommen
    */
    private static void sammenlignForekomsterData() {
        System.out.println("Sammenligner forekomster hos de som har sykdommen med de som ikke har hatt sykdommen: \n");
        System.out.println(syke.hentAntall());
        System.out.println(friske.hentAntall());
        if (syke.hentAntall() == 1) {
            HashMap<String, Subsekvens> kart_for_syke = syke.hentUt();
            HashMap<String, Subsekvens> kart_for_friske = friske.hentUt();
            ArrayList<Subsekvens> forekomster = new ArrayList<>();
            final int GRENSE = 7;

            for (Subsekvens sykSubsekvens : kart_for_syke.values()) {
                for (Subsekvens friskSubsekvens : kart_for_friske.values()) {
                    if (sykSubsekvens.hentSubsekvens().equals(friskSubsekvens.hentSubsekvens())) {
                        if (sykSubsekvens.hentAntall() - friskSubsekvens.hentAntall() >= GRENSE) {
                            forekomster.add(sykSubsekvens);
                        }
                    }
                }
            }

            System.out.println("Subsekvenser som forekommer 7 eller flere ganger hos pasienter som har hatt sykdommen, enn hos pasienter som ikke har hatt sykdommen: \n");
            for (Subsekvens subsekvens : forekomster) System.out.println(subsekvens);
        }
    }
}
