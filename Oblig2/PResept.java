public class PResept extends HvitResept {
    
    public static final int REIT = 3;
    public static final int RABATT = 108;

    public PResept(Legemiddel legemiddel, int pasientId) {
        super(legemiddel, pasientId, REIT);
    }

    // returnerer rabatt på 108 kroner på prisen som pasient må betale
    public int prisAaBetale(int pris) {
        // hvis prisen er mindre eller lik 108 kroner, så returneres prisen på 0.
        if (pris <= RABATT) {
            return 0;
        }
        
        return pris - RABATT;
    }

    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + REIT;
    }

}
