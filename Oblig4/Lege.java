public class Lege implements Comparable<Lege> {
    
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>(); 

    public Lege(String navn) {
        this.navn = navn;
    }

    // returnerer navn til lege
    public String hentNavn() {
        return navn;
    }

    // henter antall resepter
    public int hentAntallResepter() {
        int antall = 0;
        for (Resept resept : hentUtskrevneResepter()) {
            antall++;
        }

        return antall;
    }

    // henter liste av resepter
    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    // oppretter et resept-objekt av hvit resept som legges til i listen over utskrevne resepter
    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        // hvis legemiddelet er narkotisk så kastet en ulovligUtskrift feil
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        System.out.println(legemiddel.hentType());

        // oppretter et objekt av hvit resept
        HvitResept resept = new HvitResept(legemiddel, this, pasient, reit);

        // legger respeten til i liste over utskrevne resepter
        utskrevneResepter.leggTil(resept);

        // returnerer resepten
        return resept;

    }

    // oppretter et resept-objekt av militær resept som legges til i listen over utskrevne resepter
    public MiliterResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        // hvis legemiddelet er narkotisk så kastet en ulovligUtskrift feil
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);

        // oppretter et objekt av militær resept
        MiliterResept resept = new MiliterResept(legemiddel, this, pasient);

        // legger respeten til i liste over utskrevne resepter
        utskrevneResepter.leggTil(resept);

        // returnerer resepten
        return resept;
    }

    // oppretter et resept-objekt av P resept som legges til i listen over utskrevne resepter
    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        // hvis legemiddelet er narkotisk så kastet en ulovligUtskrift feil
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);

        // oppretter et objekt av P resept
        PResept resept = new PResept(legemiddel, this, pasient, reit);

        // legger respeten til i liste over utskrevne resepter
        utskrevneResepter.leggTil(resept);

        // returnerer resepten
        return resept;
    }

    // oppretter et resept-objekt av blåå resept som legges til i listen over utskrevne resepter
    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        // hvis legemiddelet er narkotisk så kastet en ulovligUtskrift feil
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);

        // oppretter et objekt av blå resept
        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);

        // legger respeten til i liste over utskrevne resepter
        utskrevneResepter.leggTil(resept);

        // returnerer resepten
        return resept;
    }


    // overskriver metoden compareTo
    // -1 = lege er bak sammenlignet lege i alfabetet
    // 1 = lege er forran sammenlignet lege i alfabetet
    // 0 = legenes navn er like
    @Override
    public int compareTo(Lege lege) {
        int teller = 0;
        String legeNavn = lege.hentNavn();
        legeNavn = legeNavn.toLowerCase();
        String navn = hentNavn();
        navn = navn.toLowerCase();

        // itererer gjennon navnet på legen
        for (int i = 0; i < navn.length(); i++) {
            // omgjør hver enkelt bokstav til en karakter slik at det kan sammenlignes
            char bokstav = navn.charAt(i);

            // hvis bokstaven er før sammenlignets lege sin bokstav returnes 1
            if (bokstav < legeNavn.charAt(teller)) return 1;
            // hvis bokstaven er etter sammenlignets lege sin bokstav returneres -1
            else if (bokstav > legeNavn.charAt(teller)) return -1;

            // hvis bokstaven til lege er lik bokstaven til sammenlignet lege på samme plass
            // vil man gå videre til neste bokstav
            teller++;
        }

        // hvis navnene er like helt frem til legens navn er ferdig, men
        // sammenlignets lege sitt navn er lengre, vil det returneres 1
        if (teller > navn.length()) return 1;

        return 0;
    }

    // overskrider toString metode
    @Override
    public String toString() {
        return "Legenavn: " + navn;
    }
}
