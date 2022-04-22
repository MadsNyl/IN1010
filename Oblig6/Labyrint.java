import java.io.*;
import java.util.Scanner;

public class Labyrint {
    private Rute[][] kart;
    private int kolonner;
    private int rader;

    public Labyrint(String fil) throws FileNotFoundException {
        // fyller inn kart med Rute objekter og deres kordinater
        Scanner input = new Scanner(new File(fil));
        String[] stoerrelse = input.nextLine().strip().split(" ");
        // setter opp teller for å ha oversikt over Y kordinat
        int y = 0;
        rader = Integer.parseInt(stoerrelse[0]);
        kolonner = Integer.parseInt(stoerrelse[1]);
        kart = new Rute[rader][kolonner];
        // setter opp teller for linjenummer
        int linje_nummer = 1;
        while (input.hasNextLine()) {
            String[] linje = input.nextLine().strip().split("");
            for (int i = 0; i < linje.length; i++) {
                Rute rute = null;
                // sjekker forste og siste linje for aapninger
                if (linje_nummer == 1 || linje_nummer == rader + 1) {
                    if (linje[i].equals(".")) rute = new Aapning(i, y, this);
                    else if (linje[i].equals("#")) rute = new SortRute(i, y, this); 
                }

                else {
                    if (linje[i].equals(".") && (i == linje.length - 1 || i == 0)) rute = new Aapning(i, y, this);
                    else if (linje[i].equals(".")) rute = new HvitRute(i, y, this);
                    else if (linje[i].equals("#")) rute = new SortRute(i, y, this);
                }

                kart[y][i] = rute;
            }
            linje_nummer++;
            y++;
        }

        // finner naboer til alle ruter
        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                Rute nord = null;
                Rute oest = null;
                Rute syd = null;
                Rute vest = null;
                // sjekker nabo nord for ruten
                if (i - 1 >= 0) {
                    nord = kart[i - 1][j];
                }

                // sjekker nabo oest for ruten
                if (j + 1 <= kolonner - 1) {
                    oest = kart[i][j + 1];
                }

                // sjekker nabo syd for ruten
                if (i + 1 <= rader - 1) {
                    syd = kart[i + 1][j];
                }

                // sjekker nabo vest for rute
                if (j - 1 >= 0) {
                    vest = kart[i][j - 1];
                }

                kart[i][j].fyllInnNaboer(nord, oest, syd, vest);
            }
        }
    }

    // henter rute
    public Rute hentRute(int y, int x) {
        return kart[y][x];
    }


    // overskrider toString metode
    @Override
    public String toString() {
        String utskrift = "";
        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                char representasjon = kart[i][j].tilTegn();
                utskrift += Character.toString(representasjon);
            }
            if (i < rader - 1) utskrift += "\n";
        }

        return utskrift;
    }
}
