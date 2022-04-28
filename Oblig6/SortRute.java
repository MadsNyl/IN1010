public class SortRute extends Rute {
    

    public SortRute(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // oversskrider finn metode
    @Override
    public void finn(Rute fra) {
        return;
    }

    // implementerer tilTegn metode
    public char tilTegn() {
        return '#';
    }
}
