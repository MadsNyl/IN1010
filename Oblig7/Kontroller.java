public class Kontroller {
    private GUI gui;
    private Modell modell;

    private final int DELAY = 2000;

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
            gui.tegnBrettOmIgjen();
        }
    }

    // tegner brett
    public void tegnStartBrett() {
        gui.tegnStartBrett();
    }

    // henter slange
    public Koe<Slange> hentSlange() { return modell.hentSlange(); }

    // forlenger slange
    public void forlengSlange(Slange del) { modell.forlengSlange(del); }

    // avslutter spill
    public void avsluttSpill() { System.exit(0); }
}
