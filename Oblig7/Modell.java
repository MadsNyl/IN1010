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

    // legger til Slange objekt i slange
    public void forlengSlange(Slange del) { slange.leggTil(del); }

    // fjerner Slange objekt fra slange
    public void forkortSlange() { slange.fjern(); }

    // avslutt spill
    public void avsluttSpill() { kjor = false; }

    // sjekk om spill kjorer
    public boolean spillKjorer() { return kjor; }
}
