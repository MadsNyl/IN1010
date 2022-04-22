abstract class Rute {
    // kolonne
    private int x;
    // rad
    private int y;
    private Labyrint labyrint;
    protected Rute[] naboer = new Rute[4];

    public Rute(int x, int y, Labyrint labyrint) {
        this.x = x;
        this.y = y;
        this.labyrint = labyrint;
    }

    // fyller inn naboer
    public void fyllInnNaboer(Rute nord, Rute oest, Rute syd, Rute vest) {
        naboer[0] = nord;
        naboer[1] = oest;
        naboer[2] = syd;
        naboer[3] = vest;
    }

    // henter kordinater
    public Tupple hentKordinater() { return new Tupple(y, x); }

    // sjekker naboer
    public void sjekkNabo() {
        for (Rute nabo : naboer) {
            final String utskrift = nabo != null
                ? Character.toString(nabo.tilTegn())
                : "null";
            System.out.println(utskrift);
        }
    }

    // finner rute
    public void finn(Rute fra) {
        if (fra == null) {
            
        } else {
            
        }
    }

    // finner vei ut
    public void finnUtveiFra(int rad, int kol) {

    }

    // returnerer rutens tegnrepresentasjon
    abstract char tilTegn();
}
