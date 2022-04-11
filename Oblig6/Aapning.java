public class Aapning extends HvitRute {

    public Aapning(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // overskrider gaa metode
    @Override
    public void gaa() {
        System.out.println("Funnet aapning.");
        System.exit(0);
    }
    
}
