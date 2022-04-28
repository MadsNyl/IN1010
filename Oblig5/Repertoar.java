import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Repertoar {
    /* 
        Repetoar bestaar av mange reseptorer. Repetoar tar imot en datafil med sekvenser.
        Det opprettes objekter av Reseptor med subsekvenser. Repetoar vil konvertere
        alle mengder med subsekvenser til et HashMap med subsekvenser som nøkkel, 
        og et Subsekvens objekt som verdi.
    */

    private String filnavn;
    private ArrayList<Reseptor> reseptorer = new ArrayList<>();
    private HashMap<String, Subsekvens> subsekvenser = new HashMap<>();

    public Repertoar (String filnavn) {
        this.filnavn = filnavn;
    }

    // hent subsekvenser
    public HashMap<String, Subsekvens> hentSubsekvenser() { return subsekvenser; }

    // hent filnavn
    public String hentFilnavn() { return filnavn; }

    // les fil og opprett objekter av Reseptor for hver linje med en sekvens
    public void lesInnSekvenser() {
        try {
            Scanner scanner = new Scanner(new File(hentFilnavn()));
            scanner.nextLine();
 
            while (scanner.hasNextLine()) {
                String sekvens = scanner.nextLine();
                Reseptor reseptor = new Reseptor(sekvens);
                reseptor.opprettSubsekvenser();
                reseptorer.add(reseptor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // konverter fra HashSet til HashMap
    public void konverter() {
        for (Reseptor reseptor : reseptorer) {
            for (String data : reseptor.hentSubsekvenser()) {
                Subsekvens subsekvens = new Subsekvens(data);
                subsekvenser.put(data, subsekvens);
            }
        }
    }
}
