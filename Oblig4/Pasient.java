public class Pasient {
    
    private String navn, fodselsnummer;
    private int ID, IDteller = 0;
    private IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        // oppretter in ID for hver pasient
        this.ID = IDteller; 
        this.IDteller++;
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

}
