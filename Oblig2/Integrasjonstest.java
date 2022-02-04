public class Integrasjonstest {

    public static void main(String[] args) {

        // oppretter instanser av legemidler
        Legemiddel vanlig = new Vanlig("vanlig", 200, 1);
        Legemiddel vane = new Vanedannende("vane", 300, 10, 5);
        Legemiddel narkotisk = new Narkotisk("narkotisk", 500, 40, 50);

        // oppretter instans av lege og spesialist
        Lege lege = new Lege("Mads");
        Lege spesialist = new Spesialist("Mads", "1");

        // tester ut toString metode
        System.out.println(lege.toString());
        System.out.println(spesialist.toString());

        // tester ut toString metodene
        System.out.println(vanlig.toString());
        System.out.println(vane.toString());
        System.out.println(narkotisk.toString());

        // oppretter instanser av resepter
        Resept hvit = new HvitResept(vanlig, lege, 1, 2);
        Resept pResept = new PResept(vanlig, lege, 2);
        Resept miliResept = new MiliterResept(vanlig, lege, 3, 1);
    }

}
