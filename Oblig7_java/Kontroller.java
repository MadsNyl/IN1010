public class Kontroller {
    private GUI gui;
    private Modell modell;
    private boolean gaarOpp, gaarHoyre, gaarNed, gaarVenstre;
    private final int FART = 2000;

    public Kontroller() {
        gui = new GUI(this);
        modell = new Modell(gui);
    }

    // starter spill
    public void startSpill() throws InterruptedException {
        modell.opprettStartElementer();
        gui.tegnSlange(6, 6);
        // game loop
        while (modell.erSpillFerdig()) {
            Thread.sleep(FART);
            bevegSlange();
            if (gaarOpp || gaarHoyre || gaarNed || gaarVenstre) modell.tegnSlange();
            truffetSkatt();
        }
    }

    // avslutter spill
    public void avsluttSpill() {
        System.exit(0);
    }

    // se om spill er ferdig
    public boolean erSpillFerdig() { return !modell.erSpillFerdig(); }

    // ser etter kollisjon med skatt
    public void truffetSkatt() {
        Slange hode = modell.hentHode();
        Skatt[] skatter = modell.hentSkatter();

        for (int i = 0; i < skatter.length; i++) {
            if (hode.hentRad() == skatter[i].hentRad() && hode.hentKolonne() == skatter[i].hentKolonne()) {
                modell.leggTilSlangeDel(hode.hentRad(), hode.hentKolonne());
                modell.oekSlangeLengde();
                gui.tegnSlangelengde();
                modell.fjernSkatt(skatter[i]);
                Skatt ny_skatt = gui.tegnSkatt();
                skatter[i] = ny_skatt;
            }
        }   
    }

    // ser etter kollisjon med vegger
    public void kollisjon() { modell.kollisjon(); }

    // oppdaterer bevegelsen til slange
    public void bevegSlange() {
        if (gaarOpp) { bevegOpp(); }

        if (gaarHoyre) { bevegHoyre(); }

        if (gaarNed) { bevegNed(); }

        if (gaarVenstre) { bevegVenstre(); }
    }

    // beveger slange oppover
    public void bevegOpp() {
        Slange hode = modell.hentHode();
        if (hode.bevegOpp()) hode.bevegOpp();
        else kollisjon();
        if (gaarNed) return;
        gaarOpp = true;
        gaarHoyre = false;
        gaarNed = false;
        gaarVenstre = false;
    }

    // beveger slange til hoyre
    public void bevegHoyre() {
        Slange hode = modell.hentHode();
        if (hode.bevegHoyre()) hode.bevegHoyre();
        else kollisjon();
        if (gaarVenstre) return;
        gaarOpp = false;
        gaarHoyre = true;
        gaarNed = false;
        gaarVenstre = false;
    }

    // beveger slange nedover
    public void bevegNed() {
        Slange hode = modell.hentHode();
        if (hode.bevegNed()) hode.bevegNed();
        else kollisjon();
        if (gaarOpp) return;
        gaarOpp = false;
        gaarHoyre = false;
        gaarNed = true;
        gaarVenstre = false;
    }

    // beveger slange til venstre
    public void bevegVenstre() {
        Slange hode = modell.hentHode();
        if (hode.bevegVenstre()) hode.bevegVenstre();
        else kollisjon();
        if (gaarHoyre) return;
        gaarOpp = false;
        gaarHoyre = false;
        gaarNed = false;
        gaarVenstre = true;
    }
}
