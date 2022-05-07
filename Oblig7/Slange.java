public class Slange extends Rute {
    private boolean erHode;

    public Slange(int rad, int kolonne, boolean erHode) {
        super(rad, kolonne);
        this.erHode = erHode;
    }

    public Koe<Slange> beveg(String retning, Koe<Slange> slange) {
        switch (retning) {
            case "opp":
                // if (rad - 1 < 0) return false;
                System.out.println("rad: " + rad);
                for (int i = 0; i < slange.storrelse(); i++) {
                    if (i != slange.storrelse() - 1) {
                        slange.hent(i).endreRad(slange.hent(i + 1).hentRad());
                        slange.hent(i).endreKolonne(slange.hent(i + 1).hentKolonne());
                    }
                }
                rad--;
                Koe<Slange> retur = slange;
                return retur;
            case "hoyre":
                // if (kolonne + 1 > GRID - 1) return false;
                kolonne++;
                return null;
            case "ned":
                // if (rad + 1 > GRID - 1) return false;
                rad++;
                return null;
            case "venstre":
                // if (kolonne - 1 < 0) return false;
                kolonne--;
                return null;
            default:
                return null;
        }
    }

    @Override
    public String toString() { return "(" + rad + "," + kolonne + ")"; }
}
