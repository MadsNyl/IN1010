public class HvitRute extends Rute {
    

    public HvitRute(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // oversskrider finn metode
    @Override
    public void finn(Rute fra) {
        System.out.println("Hvit rute");
    }

    // implementerer tilTegn metode
    public char tilTegn() {
        return '.';
    }
}
