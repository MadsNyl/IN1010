public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, int pasientId, int reit) {
        super(legemiddel, pasientId, reit);
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Hvit";
    }

    // returnerer pris som pasient må betale
    public int prisAaBetale(int pris) {
        return pris;
    }

    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + reit;
    }

}   
