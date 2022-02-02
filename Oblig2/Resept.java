abstract class Resept {
    
    int id;
    Legemiddel legemiddel;
    int pasientId;
    int reit;
    private static int id_nummer = 1;
    
    public Resept(Legemiddel legemiddel, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.pasientId = pasientId;
        this.reit = reit;

        id = id_nummer;
        id_nummer++;
    }

    // henter id
    public int hentId() {
        return id;
    }

    // henter legemiddel
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    // henter pasient id
    public int hentPasientId() {
        return pasientId;
    }

    // henter reit
    public int hentReit() {
        return reit;
    }

    // bruk resept én gang
    public boolean bruk() {
        // hvis reit er over 0 så trekkes reit med en, og resepten er brukt en gang.
        if (reit > 0) {
            reit--;
            return true;
        }

        return false;
    }

    // retunerer farge på resepten
    abstract public String farge();

    // returnerer pris som pasient må betale
    abstract public int prisAaBetale(int pris);

}
