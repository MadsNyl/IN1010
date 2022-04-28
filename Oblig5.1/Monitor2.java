import java.util.concurrent.locks.ReentrantLock;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.io.*;

final class ReturVerdi {
    private final HashMap<String, Subsekvens> KART1;
    private final HashMap<String, Subsekvens> KART2;

    public ReturVerdi(HashMap<String, Subsekvens> kart1, HashMap<String, Subsekvens> kart2) {
        this.KART1 = kart1;
        this.KART2 = kart2;
    }

    public HashMap<String, Subsekvens> hentKart1() { return KART1; }

    public HashMap<String, Subsekvens> hentKart2() { return KART2; }

}

public class Monitor2 extends SubsekvensRegister {
    private static Lock laas = new ReentrantLock();
    private Condition ikkeKlareFiler = laas.newCondition();
    public volatile int antallKlare = 0;
    private final int KRAVMENGDE = 2;

    public ReturVerdi hentUt() throws InterruptedException {
        laas.lock();
        try {
            while (antallKlare < KRAVMENGDE) ikkeKlareFiler.await();

            HashMap<String, Subsekvens> kart1 = hentKart(0);
            HashMap<String, Subsekvens> kart2 = hentKart(1);

            antallKlare--;

            return new ReturVerdi(kart1, kart2);
            
        } finally {
            laas.unlock();
        }
    }

    public void settInn(String filnavn) throws InterruptedException {
        laas.lock();
        try {
            try {
                HashMap<String, Subsekvens> kart = new HashMap<>();
                Scanner sc = new Scanner(new File(filnavn));

                while (sc.hasNextLine()) {
                    String sekvens = sc.nextLine();
                    HashSet<String> subsekvenser = Reseptor.opprettSubsekvenser(sekvens);

                    for (String data : subsekvenser) {
                        Subsekvens subsekvens = new Subsekvens(data, 1);
                        kart.put(data, subsekvens);
                        super.settInn(kart);
                    }
                    antallKlare++;

                    if (antallKlare >= KRAVMENGDE) ikkeKlareFiler.signalAll();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fil ikke funnet.");
                e.printStackTrace();
            }
        } finally {
            laas.unlock();
        }
    }
    
}
