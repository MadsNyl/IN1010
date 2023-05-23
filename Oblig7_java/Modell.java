public class Modell {
    private GUI gui;
    private String[][] rutenett = new String[12][12];
    private int slangelengde = 0;
    private boolean spillErFerdig = false;
    private Koe<Slange> slange = new Koe<>();
    private Skatt[] skatter = new Skatt[10];

    public Modell(GUI gui) {
        this.gui = gui;
        for (int rad = 0; rad < 12; rad++) {
            for (int kol = 0; kol < 12; kol++) {
                rutenett[rad][kol] = " ";
            }
        }
    }

    // fjern skatt fra array
    public void fjernSkatt(Skatt skatt) { for (int i = 0; i < skatter.length; i++) if (skatter[i].equals(skatt)) skatter[i] = null; }

    // hent antall skatter
    public int hentAntallSkatter() { return skatter.length; }


    // se om spill er ferdig
    public boolean erSpillFerdig() { return !spillErFerdig; }

    // kollisjon med vegger
    public void kollisjon() { spillErFerdig = true; }

    // returnerer slange
    public Koe<Slange> hentSlange() { return slange; }

    // returnerer hode til slange
    public Slange hentHode() { return slange.hent(slange.storrelse() - 1); }

    // returnerer skatter
    public Skatt[] hentSkatter() { return skatter; }

    // legger til nytt Slange objekt i slangen naar hode spiser skatt
    public void leggTilSlangeDel(int rad, int kol) {
        Slange del = new Slange(gui, rad, kol, false);
        slange.leggTil(del);
        slangelengde++;
    }

    // tegner slangen
    public void tegnSlange() {
        for (int i = 0; i < slange.storrelse(); i++) {
            if (i != slange.storrelse() - 1) {
                slange.hent(i).endreRad(slange.hent(i + 1).hentRad());
                slange.hent(i).endreKolonne(slange.hent(i + 1).hentKolonne());
                System.out.println(slange.hent(i) + " " + i);
                slange.hent(i).tegn();
            }
        }

        slange.fjern();
    }

    // oker teller for lengden til slangen
    public void oekSlangeLengde() {  gui.oekHalelengde(); }
 
    // oppretter hode til slangen
    public void opprettStartElementer() {
        // skatter = gui.tegnSkatter();
        Slange hode = new Slange(gui, 6, 6, true);
        Slange del1 = new Slange(gui, 7, 6, false);
        Slange del2 = new Slange(gui, 8, 6, false);
        slange.leggTil(hode);
        slange.leggTil(del1);
        slange.leggTil(del2);
    }

}