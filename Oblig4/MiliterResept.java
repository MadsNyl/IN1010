public class MiliterResept extends HvitResept {

    public static final int REIT = 3;

    public MiliterResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, REIT);
    }

    // retunerer 100% rabatt på prisen pasienten må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris - pris);
    }   

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasient.toString() + "; " + "Antall reit: " + reit;
    }

}
