import java.io.*;
import java.util.ArrayList;

public class Hovedprogram extends Thread{

    private static final String DATAMAPPE = "Data";
    private static int teller = 0;

    public static void main(String[] args) {  
        int antallFiler = hentAntallFiler();
        for (int i = 0; i < antallFiler; i++) {
            Hovedprogram traad = new Hovedprogram();
            traad.start();
            // try {
            //     Thread.sleep(2000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
    }

    // overskrider run metode fra Thread
    @Override
    public void run()
    {
        lesInnData();
    }

    // hent datamappe
    private static String hentDatamappe() { return DATAMAPPE; }

    // henter antall filer i mappen
    private static int hentAntallFiler() {
        File mappe = new File(hentDatamappe());
        int teller = 0;

        for (File fil : mappe.listFiles()) if (!fil.getName().contains("metadata.csv")) teller++;

        return teller;
    }

    /*
        Henter datamappen, og deretter itererer gjennom mappen.
        Aapner hver fil og leser inn data i objekter av Repertoar.
    */
    private static void lesInnData() {
        File mappe = new File(hentDatamappe());

        for (File fil : mappe.listFiles()) {
            String filnavn = fil.getName();
            if (!filnavn.contains("metadata.csv")) {
                Repertoar repetoar = new Repertoar(hentDatamappe() + "/" + filnavn);
                repetoar.lesInnSekvenser();
                repetoar.konverter();
                teller++;
                System.out.println("Ferdig konvertert. Teller: " + teller);
            }
        }
    }  
}
