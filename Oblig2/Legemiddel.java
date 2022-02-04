abstract public class Legemiddel {
    
    int id;
    String navn;
    int pris;
    double virkestoff;
    private static int id_nummer = 1;

    // konstruktør
    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        // generer og holder oversikt over id
        this.id = id_nummer;
        id_nummer++;
    }

    // henter id
    public int hentId() {
        return id;
    }

    // henter navn
    public String hentNavn() {
        return navn;
    }

    // henter pris
    public int hentPris() {
        return pris;
    }

    // henter virkestoff
    public double hentVirkestoff() {
        return virkestoff;
    }

    // setter ny pris
    public void settNyPris(int nyPris) {
        pris = nyPris;
    }
}
