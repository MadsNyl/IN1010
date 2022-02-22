public class Hovedprogram {
    
    public static void main(String[] args) {
        Liste<String> minListe = new Lenkeliste<String>();

        System.out.println(minListe.stoerrelse());
        minListe.leggTil("node 1");
        System.out.println("-------");
        minListe.leggTil("node 2");
        System.out.println("-------");
        minListe.leggTil("node 3");
        System.out.println("-------");
        minListe.leggTil("node 4");
        System.out.println(minListe.stoerrelse());
        System.out.println();

        System.out.println();
        // minListe.leggTil(3, "Ny node");
        System.out.println(minListe.fjern(2));
        minListe.hent(2);

        // minListe.sett(3, "node 5");
        // System.out.println(minListe.stoerrelse());
        // System.out.println(minListe.fjern());
        // minListe.leggTil("node 4");
        // System.out.println(minListe.stoerrelse());
        // System.out.println(minListe.fjern());
        // System.out.println(minListe.stoerrelse());
        // System.out.println(minListe.hent(3));
        // System.out.println(minListe.hent(2));
        // System.out.println(minListe.hent(3));
        // minListe.sett(2, "node 5");
        // System.out.println(minListe.hent(1));
        

    }

}
