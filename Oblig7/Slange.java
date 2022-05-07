public class Slange extends Rute {
    private boolean erHode;

    public Slange(int rad, int kolonne, boolean erHode) {
        super(rad, kolonne);
        this.erHode = erHode;
    }

    public void settHode() { erHode = true; }

    public void fjernHode() { erHode = false; }

    public boolean erHode() { return erHode; }
}
