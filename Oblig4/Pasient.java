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

    // hent reseptlist
    public IndeksertListe<Resept> hentReseptListe() { return reseptListe; }

    // legg til resept i liste
    public void leggTilResept(Resept resept) { reseptListe.leggTil(resept); }

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
        return "\n - ID: " + hentID() + "\n - navn: " + hentNavn() + "\n - fødselsnummer: " + hentFodselsnummer();
    }

}
