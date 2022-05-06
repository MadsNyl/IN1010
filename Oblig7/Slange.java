public class Slange extends Rute {
    private boolean erHode;

    public Slange(int rad, int kolonne, boolean erHode) {
        super(rad, kolonne);
        this.erHode = erHode;
    }

    public boolean beveg(String retning) {
        switch (retning) {
            case "opp":
                // if (rad - 1 < 0) return false;
                rad--;
                System.out.println("rad: " + rad);
                return true;
            case "hoyre":
                // if (kolonne + 1 > GRID - 1) return false;
                kolonne++;
                return true;
            case "ned":
                // if (rad + 1 > GRID - 1) return false;
                rad++;
                return true;
            case "venstre":
                // if (kolonne - 1 < 0) return false;
                kolonne--;
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() { return "(" + rad + "," + kolonne + ")"; }
}
