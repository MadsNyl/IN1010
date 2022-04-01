import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Monitor2 extends SubsekvensRegister{
    private static Lock laas = new ReentrantLock(true);
    private Condition ikkeToFilerKlare = laas.newCondition();
    // private volatile int antallKlare = 0;
    private final int KRAV = 2;

    // public Monitor2() {
    //     laas = new ReentrantLock(true);
    //     ikkeToFilerKlare = laas.newCondition();
    // }


    // oversskrider sett inn metode med en lås for trådene
    @Override
    public void settInn(HashMap<String, Subsekvens> kart) {
        laas.lock();
        try { 
            beholder.add(kart);
            if (hentAntall() >= KRAV) {
                ikkeToFilerKlare.signalAll();
            }
            System.out.println("Kart satt inn av lesetraad.");
        } finally { laas.unlock(); }
    }

    // setter inn flettet kart
    public void settInnFlettet(HashMap<String, Subsekvens> kart) {
        laas.lock();
        try {
            beholder.add(kart);
            // System.out.println("Flettetråd setter inn kart. Antall er nå: " + hentAntall());
            if (hentAntall() >= KRAV) {
                ikkeToFilerKlare.signalAll();
            }
            System.out.println("Flettet kart satt inn av flettetraad.");
        } finally { laas.unlock(); }
    }

    // henter ut to hashmaper fra monitor
    public Returverdi<HashMap<String, Subsekvens>> hentUtToKart() throws InterruptedException {
        laas.lock();
        try {
            // tråder venter på at det skal være to tilgjengelige filer
            while (hentAntall() < KRAV) ikkeToFilerKlare.await();
            System.out.println("To kart hentet ut av flettetraad.");
            // returnerer to filer
            return new Returverdi<HashMap<String,Subsekvens>>(hentUt(), hentUt());

        } finally { laas.unlock(); }
    }

    // legger til lås på metode for å slå sammen to hashmaps
    public void slaaSammenToKart(HashMap<String, Subsekvens> kart1, HashMap<String, Subsekvens> kart2) {
        laas.lock();
        try {
            HashMap<String, Subsekvens> nytt_kart = new HashMap<>();

            // setter først inn elementer fra ett kart
            for (String nokkel : kart2.keySet()) nytt_kart.put(nokkel, new Subsekvens(1, nokkel)); 

            // ser deretter etter duplikater i de to kartene og slår sammen antall
            for (String nokkel1 : kart1.keySet()) {
                Subsekvens subsekvens1 = kart1.get(nokkel1);
                int teller = subsekvens1.hentAntall();
                for (String nokkel2 : kart2.keySet()) {
                    Subsekvens subsekvens2 = kart2.get(nokkel2);
                    int antall = subsekvens2.hentAntall();

                    if (nokkel1.equals(nokkel2)) {
                        teller += antall;
                    } 

                    nytt_kart.put(nokkel1, new Subsekvens(teller, nokkel1));
                }
            }
            System.out.println("To kart slaat sammen av lesetraad.");
            settInnFlettet(nytt_kart);
        } finally { laas.unlock(); }
    }

    // legger til lås på metode for å konverte sekvens til subsekvenser
    public static HashMap<String, Subsekvens> konverterTilSubsekvenser(String filnavn) {
        laas.lock();
        try { 
            HashMap<String, Subsekvens> subsekvenser = new HashMap<>();

            try {
                Scanner input = new Scanner(new File(filnavn));
                subsekvenser = Reseptor.lagSubsekvenser(input);
            } catch (FileNotFoundException e) { System.out.println("Fant ikke fil."); }
            System.out.println("konvertert til subsekvenser.");
            return subsekvenser;
        } 
        finally { laas.unlock();}
    }
}
