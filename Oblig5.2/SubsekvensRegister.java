import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class SubsekvensRegister {
    protected ArrayList<HashMap<String, Subsekvens>> beholder = new ArrayList<>();
    private Lock laas = new ReentrantLock();

    // setter inn hashmap
    public void settInn(HashMap<String, Subsekvens> kart) { beholder.add(kart); }

    // henter ut første hashmap i beholder
    public HashMap<String, Subsekvens> hentUt() { 
        HashMap<String, Subsekvens> kart = beholder.size() > 0 ? beholder.get(0) : null; 
        beholder.remove(0);
        return kart;
    }

    // henter ut antall i beholder
    public int hentAntall() { 
        laas.lock();
        try {
            return beholder.size(); 
        } finally { laas.unlock(); }
    }

    // leser fil med èn persons immunrepertoar og lager en HashMap av subsekvensene i filen, og returnerer HashMapen.
    public static HashMap<String, Subsekvens> konverterTilSubsekvenser(String filnavn) {
        // oppretter nytt HashMap for subsekvensene
        HashMap<String, Subsekvens> subsekvenser = new HashMap<>();

        try {
            Scanner input = new Scanner(new File(filnavn));
            subsekvenser = Reseptor.lagSubsekvenser(input);
        } catch (FileNotFoundException e) { System.out.println("Fil ikke funnet."); }

        return subsekvenser;
    }

    // slår sammen to HashMaps til et HashMap
    public static HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> kart1, HashMap<String, Subsekvens> kart2) {
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

        return nytt_kart;
    }
}
