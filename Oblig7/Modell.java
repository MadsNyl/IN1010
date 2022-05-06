public class Modell {
    private GUI gui;
    private Kontroller kontroller;
    private boolean kjor;
    private Koe<Slange> slange = new Koe<>();

    public Modell(GUI gui, Kontroller kontroller) {
        this.gui = gui;
        this.kontroller = kontroller;
    }

    // start spill
    public void startSpill() { 
        kjor = true;
        kontroller.tegnStartBrett();
    }

    // henter slange
    public Koe<Slange> hentSlange() { return slange; }

    // henter hode
    public Slange hentHode() { return slange.hent(slange.storrelse() - 1); }

    // legger til Slange objekt i slange
    public void forlengSlange(Slange del) { slange.leggTil(del); }

    // fjerner Slange objekt fra slange
    public void forkortSlange() { slange.fjern(); }

    // avslutt spill
    public void avsluttSpill() { kjor = false; }

    // sjekk om spill kjorer
    public boolean spillKjorer() { return kjor; }

    // beveg slange
    public void beveg(String retning) {
        switch (retning) {
            case "opp":
                // for (int i = 0; i < slange.storrelse(); i++) {
                //     if (i != slange.storrelse() - 1) {
                //         slange.hent(i).endreRad(slange.hent(i + 1).hentRad());
                //         slange.hent(i).endreKolonne(slange.hent(i + 1).hentKolonne());
                //     }
                // }
                hentHode().beveg("opp");
                // slange.leggTil(new Slange(hentHode().hentRad(), hentHode().hentKolonne(), true));                   
                // slange.fjern();
                break;
            case "hoyre":
                hentHode().beveg("hoyre");
                break;
            case "ned":
                hentHode().beveg("ned");
                break;
            case "venstre":
                hentHode().beveg("venstre");
                break;
        }
    }
}
