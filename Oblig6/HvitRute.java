public class HvitRute extends Rute {
    

    public HvitRute(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // overskrider gaa metode
    @Override
    public void gaa() {
        System.out.println("Hvit rute. Sjekker naboer.");
        super.gaa();
    }

    // implementerer tilTegn metode
    public char tilTegn() {
        return '.';
    }
}
