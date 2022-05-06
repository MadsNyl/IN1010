public class Rute {
    protected int rad, kolonne;
    protected final int GRID = 12;

    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne = kolonne;
    }

    public int hentRad() { return rad; }

    public int hentKolonne() { return kolonne; }

}
