public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
        super(legemiddel, lege, pasientId, reit);
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Hvit";
    }

    // returnerer pris som pasient må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris);
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + reit;
    }

}   
