public class MiliterResept extends HvitResept {

    public MiliterResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    // retunerer 100% rabatt på prisen pasienten må betale
    public int prisAaBetale(float pris) {
        return Math.round(pris - pris);
    }   

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasient + "; " + "Antall reit: " + reit;
    }

}
