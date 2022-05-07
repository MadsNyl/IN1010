public class Kontroller {
    private GUI gui;
    private Modell modell;
    private boolean gaarOpp, gaarHoyre, gaarNed, gaarVenstre;

    private final int DELAY = 1000;
    private final int GRID = 12;

    public Kontroller() {
        gui = new GUI(this);
        modell = new Modell(gui, this);
    }

    // starter spill
    public void init() throws InterruptedException {
        modell.startSpill();
        gameLoop();
    }

    // gameloop
    public void gameLoop() throws InterruptedException {
        while (modell.spillKjorer()) {
            Thread.sleep(DELAY);
            oppdater();
            gui.tegnBrettOmIgjen();
        }
    }

    // tegner brett
    public void tegnStartBrett() {
        gui.tegnStartBrett();
    }

    // henter slange
    public Koe<Slange> hentSlange() { return modell.hentSlange(); }

    // henter halelengde
    public int hentHalelengde() { return modell.hentHalelengde(); }

    // henter skatter
    public Skatt[] hentSkatter() { return modell.hentSkatter(); }

    // legg til skatt
    public void leggTilSkatt(int pos, Skatt skatt) { modell.leggTilSkatt(pos, skatt); }

    // fjern skatt
    public void fjernSkatt(int pos) { modell.fjernSkatt(pos); }

    // forlenger slange
    public void forlengSlange(Slange del) { modell.forlengSlange(del); }

    // ser etter kollisjon med egen hale
    public void treffHale() { modell.treffHale(); }

    // avslutter spill
    public void avsluttSpill() { System.exit(0); }

    // oppdaterer elementer i spillet
    public void oppdater() {
        beveg();
        treffHale();
    }


    // beveger slangen
    public void beveg() {
        if (gaarOpp) modell.beveg("opp");
        if (gaarHoyre) modell.beveg("hoyre");
        if (gaarNed) modell.beveg("ned");
        if (gaarVenstre) modell.beveg("venstre");
    }

    // oppdaterer bevegelsen til slangen
    public void oppdaterBevegelse(String retning) {
        switch (retning) {
            case "opp":
                if (gaarNed) return;
                gaarOpp = true;
                gaarHoyre = false;
                gaarNed = false;
                gaarVenstre = false;
                break;
            case "hoyre":
                if (gaarVenstre) return;
                gaarOpp = false;
                gaarHoyre = true;
                gaarNed = false;
                gaarVenstre = false;
                break;
            case "ned":
                if (gaarOpp) return;
                gaarOpp = false;
                gaarHoyre = false;
                gaarNed = true;
                gaarVenstre = false;
                break;
            case "venstre":
                if (gaarHoyre) return;
                gaarOpp = false;
                gaarHoyre = false;
                gaarNed = false;
                gaarVenstre = true;
                break;
        }
    }
}
