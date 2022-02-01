public class Vanedannende extends Legemiddel {

    private int styrke;

    // tar imot kosntruktør fra parent og extender med egne parametere    
    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

}
