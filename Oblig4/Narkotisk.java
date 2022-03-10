public class Narkotisk extends Legemiddel {

    private int styrke;
    
    // tar imot kosntruktør fra parent og extender med egne parametere    
    public Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return "ID: " + id + "; Navn: " + navn + "; Pris: " + pris + "; Virkestoff: " + virkestoff + "; Styrke: " + styrke + ";" ;
    }   

}
