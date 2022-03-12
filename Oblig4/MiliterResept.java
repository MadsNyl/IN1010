public class MiliterResept extends HvitResept {

    public static final int REIT = 3;

    public MiliterResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, REIT);
    }

    // henter type resept
    @Override
    public String hentType() {
        return "militaer";
    }

    // retunerer 100% rabatt på prisen pasienten må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris - pris);
    }   


}
