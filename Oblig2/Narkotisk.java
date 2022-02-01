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

}
