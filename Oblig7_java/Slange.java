public class Slange {
    private GUI gui;
    private int rad, kol, ny_rad, ny_kol;
    private boolean erHode;

    public Slange(GUI gui, int rad, int kol, boolean erHode) {
        this.gui = gui;
        this.rad = rad;
        this.ny_rad = rad;
        this.kol = kol;
        this.ny_kol = kol;
        this.erHode = erHode;
    }

    public boolean erHode() { return erHode; }

    public int hentRad() { return rad; }

    public int hentKolonne() { return kol; }

    public void endreRad(int rad) { this.rad = rad; }

    public void endreKolonne(int kol) { this.kol = kol; }

    // tegner slange paa brettet
    public void tegn() {
        gui.viskUtSlange(rad, kol);
        gui.tegnSlange(ny_rad, ny_kol);
        rad = ny_rad;
        kol = ny_kol; 
    }

    // beveg oppover
    public boolean bevegOpp() { 
        if (rad - 1 < 0) return false;
        ny_rad = rad - 1; 
        return true;
    }

    // beveg til hoyre
    public boolean bevegHoyre() { 
        if (kol + 1 > 11) return false;
        ny_kol = kol + 1;
        return true; 
    }

    // beveg nedover
    public boolean bevegNed() {
        if (rad + 1 > 11) return false; 
        ny_rad = rad + 1; 
        return true;
    }

    // beveg til venstre
    public boolean bevegVenstre() { 
        if (kol - 1 < 0) return false;
        ny_kol = kol - 1; 
        return true;
    }

    @Override
    public String toString() { return "(" + rad + "," + kol + ")"; }
}
