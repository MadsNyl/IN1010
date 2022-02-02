public class MiliterResept extends HvitResept {

    public MiliterResept(Legemiddel legemiddel, int pasientId, int reit) {
        super(legemiddel, pasientId, reit);
    }

    // retunerer 100% rabatt på prisen pasienten må betale
    public int prisAaBetale(int pris) {
        return pris - pris;
    }   

    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + "; " + "PasientId: " + pasientId + "; " + "Antall reit: " + reit;
    }

}
