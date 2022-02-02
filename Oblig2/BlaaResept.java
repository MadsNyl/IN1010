public class BlaaResept extends Resept {
    

    public BlaaResept(Legemiddel legemiddel, int pasientId, int reit) {
        super(legemiddel, pasientId, reit);
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Blaa";
    }

    // retunerer 75% rabatt på prisen som pasient må betale
    public int prisAaBetale(int pris) {
        return pris / 4;
    }

    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + reit;
    }

}
