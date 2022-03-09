public class Spesialist extends Lege implements Godkjenningsfritak {
    
    // extender Lege og implementerer godkjenningsfritak

    private String kontrollID;

    public Spesialist (String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    // henter kontrollID (bruker godkjenninsfritak interface)
    public String hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        return "Legenavn: " + navn + "; " + "KontrollID: " + kontrollID;
    }

}
