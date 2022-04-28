import java.util.HashMap;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 extends SubsekvensRegister{
    private static Lock laas = new ReentrantLock();


    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
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
                    }
                }
                return kart;
    
            } catch (FileNotFoundException e) {
                System.out.println("Fil ikke funnet.");
                e.printStackTrace();
            }
    
            return null;
        } finally {
            laas.unlock();
        }

    }
}
