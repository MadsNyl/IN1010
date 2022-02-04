public class BlaaResept extends Resept {
    

    public BlaaResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
        super(legemiddel, lege, pasientId, reit);
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Blaa";
    }

    // retunerer 75% rabatt på prisen som pasient må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris / 4);
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + reit;
    }

}
