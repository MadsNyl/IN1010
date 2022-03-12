public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    // henter type resept
    @Override
    public String hentType() {
        return "hvit";
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Hvit";
    }

    // returnerer pris som pasient må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris);
    }

}   
