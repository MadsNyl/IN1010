public class Skatt {
    private GUI gui;
    private int rad, kol;

    public Skatt(int rad, int kol) {
        this.rad = rad;
        this.kol = kol;
    }

    public int hentRad() { return rad; }

    public int hentKolonne() { return kol; }

    static int trekk(int min, int max) {
        return (int)(Math.random()*(max - min + 1)) + min;
    } 

    @Override
    public String toString() { return "(" + rad + "," + kol + ")"; }
}
