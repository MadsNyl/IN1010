public class BlaaResept extends Resept {
    

    public BlaaResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    // henter type resept
    @Override
    public String hentType() {
        return "blaa";
    }

    // retunerer hvit som farge på resept
    public String farge() {
        return "Blaa";
    }

    // retunerer 75% rabatt på prisen som pasient må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris / 4);
    }


}
