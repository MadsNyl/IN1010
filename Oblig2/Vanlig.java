public class Vanlig extends Legemiddel {
    
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    // overskriver toString metode
    @Override
    public String toString() {
        return navn + "; " + pris + "; " + virkestoff + ";";
    }   

}
