public class Pasient {
    
    private String navn, fodselsnummer;
    private int ID;
    private static int IDteller = 1;
    private IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        // oppretter in ID for hver pasient
        this.ID = IDteller; 
        IDteller++;
    }

    public int hentID() {
        return ID;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnummer() {
        return fodselsnummer;
    }

    // overkjører toString metode
    @Override
    public String toString() {
        return "ID: " + hentID() + "; navn: " + hentNavn() + "; fødselsnummer: " + hentFodselsnummer();
    }

}
