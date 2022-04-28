public class Aapning extends HvitRute {

    public Aapning(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // overskrider finn metode
    @Override
    public void finn(Rute fra) {
        System.out.println("(" + y + "," + x + ")");
        return;
    }
    
}
