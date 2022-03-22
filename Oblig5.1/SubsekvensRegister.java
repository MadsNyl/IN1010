import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.security.auth.Subject;

import java.io.*;

public class SubsekvensRegister {
    
    private static ArrayList<HashMap<String, Subsekvens>> register = new ArrayList<>();

    // hent register
    public ArrayList<HashMap<String, Subsekvens>> hentRegister() { return register; }

    // hent antall kart
    public int hentAntall() { return register.size(); }

    // sett inn et subsekvenskart
    public void settInn(HashMap<String, Subsekvens> kart) { register.add(kart); }

    // fjern et subsekvenskart
    public void fjern() { register.remove(0); }

    // les fil og lagrer data om en persons immunrepertoar i et subsekvenskart
    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
        try {   
            HashMap<String, Subsekvens> kart = new HashMap<>();
            Scanner sc = new Scanner(new File(filnavn));
            while (sc.hasNextLine()) {
                String sekvens = sc.nextLine();
                Reseptor reseptor = new Reseptor(sekvens);
                reseptor.opprettSubsekvenser();
                HashSet<String> subsekvenser = reseptor.hentSubsekvenser();
                for (String data : subsekvenser) {
                    Subsekvens subsekvens = new Subsekvens(data, 1);
                    kart.put(data, subsekvens);
                }
            }
            register.add(kart);
            return kart;

        } catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet.");
            e.printStackTrace();
        }

        return null;
    }

    // slår sammen to kart til ett, og fjerner duplikater av objekter og øker antall forekomster av dette objektet
    public static HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> kart1, HashMap<String, Subsekvens> kart2) {
        HashMap<String, Subsekvens> nyttKart = new HashMap<>();

        for (String nokkel1 : kart1.keySet()) {
            int teller = 1;
            for (String nokkel2 : kart2.keySet()) {
                if (nokkel1.equals(nokkel2)) {
                    teller++;
                }
                System.out.println(nokkel1 + " - " + teller);
            }
            // System.out.println(nokkel1 +  " - " + teller);
        }
        
        return null;
    }

    // vis alle subsekvenskart (ikke testet)
    public void visAlleSubsekvensKart() {
        for (HashMap<String, Subsekvens> kart : register) {
            System.out.println(kart);
        }
    }

}
