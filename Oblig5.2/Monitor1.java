import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Monitor1 extends SubsekvensRegister {
    private static Lock laas;
    

    public Monitor1() {
        laas = new ReentrantLock(true);
    }

    // oversskrider sett inn metode med en lås for trådene
    @Override
    public void settInn(HashMap<String, Subsekvens> kart) {
        laas.lock();
        try { beholder.add(kart); } 
        finally { laas.unlock(); }
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

            return subsekvenser;
        } 
        finally { laas.unlock();}
    }
}
