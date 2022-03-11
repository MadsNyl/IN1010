public class PResept extends HvitResept {
    
    public static final int RABATT = 108;

    // henter type resept
    @Override
    public String hentType() {
        return "P-Resept";
    }

    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    // returnerer rabatt på 108 kroner på prisen som pasient må betale
    public int prisAaBetale(float pris) {
        // hvis prisen er mindre eller lik 108 kroner, så returneres prisen på 0.
        if (pris <= RABATT) {
            return 0;
        }
        
        return Math.round(pris - RABATT);
    }


}
