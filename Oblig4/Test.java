public class Test {
    public static void main(String[] args) throws UlovligUtskrift {
        Lege lege = new Lege("Dr.Nylund");
        Lege spesialist = new Spesialist("Dr.Jensen", "ETG54");
        Pasient pasient = new Pasient("Mads", "20010090521");

        Legemiddel vanlig = new Vanlig("vanlig", 250, 45);
        Legemiddel narkotisk = new Narkotisk("narkotisk", 500, 75, 20);

        IndeksertListe<Resept> liste = lege.hentUtskrevneResepter();

        lege.skrivHvitResept(vanlig, pasient, 2);
        lege.skrivMilResept(vanlig, pasient);
        lege.skrivPResept(vanlig, pasient, 1);
        lege.skrivBlaaResept(vanlig, pasient, 1);
        spesialist.skrivBlaaResept(narkotisk, pasient, 2);

        System.out.println("Listestørrelse: " + liste.stoerrelse());

        for (int i = 0; i < liste.stoerrelse(); i++) {
            Resept resept = liste.hent();

            System.out.println(resept.toString());
        }



    }
}
