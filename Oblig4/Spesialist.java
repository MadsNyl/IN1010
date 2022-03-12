public class Spesialist extends Lege implements Godkjenningsfritak {
    
    // extender Lege og implementerer godkjenningsfritak

    private String kontrollID;

    public Spesialist (String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    // henter kontrollID (bruker godkjenninsfritak interface)
    public String hentKontrollID() { return kontrollID; }

    // overkjører metoden for å skrive ut blå resept for å la en spesialt få skrive ut narkotisk legemiddel
    @Override
    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) {
        // oppretter et objekt av blå resept
        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);

        // legger respeten til i liste over utskrevne resepter
        utskrevneResepter.leggTil(resept);

        // returnerer resepten
        return resept;
    }

    @Override
    public String toString() {
        return "Legenavn: " + navn + "; \n" + "KontrollID: " + kontrollID;
    }

}
