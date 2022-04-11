public class SortRute extends Rute {
    

    public SortRute(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
    }

    // overskrider gaa metode
    @Override
    public void gaa() {
        System.out.println("Sort rute, kan ikke gaa denne veien.");
    }

    // implementerer tilTegn metode
    public char tilTegn() {
        return '#';
    }
}
