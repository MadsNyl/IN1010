public class Modell {
    private GUI gui;
    private Kontroller kontroller;
    private boolean kjor;
    private Koe<Slange> slange = new Koe<>();
    private Skatt[] skatter = new Skatt[10];

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

    // henter halelengde
    public int hentHalelengde() { return slange.storrelse(); }

    // henter hode
    public Slange hentHode() {
        for (Slange del : slange) {
            if (del.erHode()) return del;
        }

        return null;
    }

    // henter skatter
    public Skatt[] hentSkatter() { return skatter; }

    // legg til skattt
    public void leggTilSkatt(int pos, Skatt skatt) { skatter[pos] = skatt; }

    // fjern skatt
    public void fjernSkatt(int pos) { skatter[pos] = null; }

    // legger til Slange objekt i slange
    public void forlengSlange(Slange del) { slange.leggTil(del); }

    // fjerner Slange objekt fra slange
    public void forkortSlange() { slange.fjern(); }

    // avslutt spill
    public void avsluttSpill() { kjor = false; }

    // sjekk om spill kjorer
    public boolean spillKjorer() { return kjor; }

    // sjekk om treff med egen hale
    public void treffHale() {
        if (slange.storrelse() > 1) {
            for (Slange del : slange) {
                if (hentHode().hentRad() == del.hentRad() && hentHode().hentKolonne() == del.hentKolonne() && !del.erHode()) avsluttSpill();
            }
        }
    }

    // legg til skatt
    public void leggTilSkatt(int pos) {
        int skatt_rad = 0;
        int skatt_kolonne = 0;
        boolean ingen_treff = false;

        while (true) {
            for (Slange del : slange) {
                for (Skatt skatt : skatter) {
                    if (skatt != null) {
                        skatt_rad = Skatt.trekk(0, 11);
                        skatt_kolonne = Skatt.trekk(0, 11);
                        if (skatt_rad != del.hentRad() && skatt_rad != skatt.hentRad() && skatt_kolonne != del.hentKolonne() && skatt_kolonne != skatt.hentKolonne()) ingen_treff = true;
                        else ingen_treff = false;
                    }
                }
            }

            if (ingen_treff) break;
        }

        skatter[pos] = new Skatt(skatt_rad, skatt_kolonne); 
    }

    // sjekk kollisjon mellom slange og skatt
    public boolean kollisjon() {
        for (int i = 0; i < skatter.length; i++) {
            for (Slange del : slange) {
                if (skatter[i] != null) {
                    if (skatter[i].hentRad() == del.hentRad() && skatter[i].hentKolonne() == del.hentKolonne()) {
                        fjernSkatt(i);
                        leggTilSkatt(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // beveg slange
    public void beveg(String retning) {
        Slange ny;

        switch (retning) {
            case "opp":
                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true);
                    if (!kollisjon()) slange.fjern();
                    for (Slange del : slange) del.fjernHode();
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true);
                for (Slange del : slange) del.fjernHode();
                if (!kollisjon()) slange.fjern();
                slange.leggTil(ny); 
                break;

            case "hoyre":
                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true);
                    if (!kollisjon()) slange.fjern();
                    for (Slange del : slange) del.fjernHode();
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true);
                for (Slange del : slange) del.fjernHode();
                if (!kollisjon()) slange.fjern();
                slange.leggTil(ny);
                break;

            case "ned":
                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true);
                    if (!kollisjon()) slange.fjern();
                    for (Slange del : slange) del.fjernHode();
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true); 
                for (Slange del : slange) del.fjernHode();
                if (!kollisjon()) slange.fjern();
                slange.leggTil(ny);
                break;
                
            case "venstre":
                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true);
                    if (!kollisjon()) slange.fjern();
                    for (Slange del : slange) del.fjernHode();
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true);
                for (Slange del : slange) del.fjernHode();
                if (!kollisjon()) slange.fjern();
                slange.leggTil(ny);
                break;
        }
    }
}
