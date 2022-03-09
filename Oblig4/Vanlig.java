public class Vanlig extends Legemiddel {
    
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legemiddel: " + navn + "; Pris: " + pris + "; Virkestoff: " + virkestoff + ";";
    }   

}
