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
        Slange ny;
        int lavest_rad = 12;
        int lavest_kolonne = 12;
        int hoyest_kolonne = 0;
        int hoyest_rad = 0;

        switch (retning) {
            case "opp":

                // finn verdier
                for (Slange del : slange) {
                    if (del.hentRad() < lavest_rad && !slange.hent(slange.storrelse() - 1).equals(del)) lavest_rad = del.hentRad();
                    // if (del.hentKolonne() > hoyest_kolonne) hoyest_kolonne = del.hentKolonne();
                }

                ny = new Slange(lavest_rad - 1, slange.hent(0).hentKolonne(), true);
           
                
                slange.fjern();
                slange.leggTil(ny); 
                break;
            case "hoyre":
                // finn verdier
                for (Slange del : slange) {
                    if (del.hentKolonne() > hoyest_kolonne && !slange.hent(slange.storrelse() - 1).equals(del)) hoyest_kolonne = del.hentKolonne();
                }

                ny = new Slange(slange.hent(0).hentRad(), hoyest_kolonne + 1, true);

                slange.fjern();
                slange.leggTil(ny);
                break;
            case "ned":

                // finn verdier
                for (Slange del : slange) {
                    if (del.hentRad() > hoyest_rad && !slange.hent(slange.storrelse() - 1).equals(del)) hoyest_rad = del.hentRad();
                }

                ny = new Slange(hoyest_rad + 1, slange.hent(0).hentKolonne(), true);
        
                
                slange.fjern();
                slange.leggTil(ny);
                break;
            case "venstre":
                // finn verdier
                for (Slange del : slange) {
                    if (del.hentKolonne() < lavest_kolonne && !slange.hent(slange.storrelse() - 1).equals(del)) lavest_kolonne = del.hentKolonne();
                }

                ny = new Slange(slange.hent(0).hentRad(), lavest_kolonne - 1, true);

                slange.fjern();
                slange.leggTil(ny);
                break;
        }
    }
}
