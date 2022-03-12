abstract class Resept {
    
    private int id;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;
    private static int id_nummer = 1;
    
    public Resept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.pasient = pasient;
        this.reit = reit;

        id = id_nummer;
        id_nummer++;
    }

    // henter type resept
    public String hentType() { return "Resept"; }

    // henter id
    public int hentId() { return id; }

    // henter legemiddel
    public Legemiddel hentLegemiddel() { return legemiddel; }

    // henter lege
    public Lege hentLege() { return lege; }

    // henter pasient id
    public Pasient hentPasient() { return pasient; }

    // henter reit
    public int hentReit() { return reit; }

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
    abstract public int prisAaBetale(float pris);

    // oversskriver toString metode
    @Override
    public String toString() { return "Type: " + hentType() + "\nLegemiddel: \n" + legemiddel + "\nPasient: " + pasient + "\nAntall reit: " + reit; }

}
