abstract public class Legemiddel {
    
    private int id;
    private String navn;
    private int pris;
    private double virkestoff;
    private static int id_nummer = 0;

    // konstruktør
    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        // generer og holder oversikt over id
        this.id = id_nummer;
        id_nummer++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }
}
