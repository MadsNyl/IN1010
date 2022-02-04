import java.util.ArrayList;

public class TestResepter {
    
    public static void main(String[] args) {

        ArrayList<Resept> resepter = new ArrayList<Resept>();
        ArrayList<Legemiddel> legemidler = new ArrayList<Legemiddel>();

        // oppretter instans av lege
        Lege lege = new Lege("Mads");

        // oppretter legemiddel instanser og legger til i arrayList for hurtigere gjennomgang av tester
        Legemiddel legemiddel1 = new Vanlig("vanlig_medisin", 128, 10);
        legemidler.add(legemiddel1); 
        Legemiddel legemiddel2 = new Narkotisk("narkotisk_medisin", 100, 10, 2);
        legemidler.add(legemiddel2); 
        Legemiddel legemiddel3 = new Vanedannende("vanedannende_medisin", 50, 10, 2);
        legemidler.add(legemiddel3); 
        Legemiddel legemiddel4 = new Vanlig("p_medisin", 200, 10);
        legemidler.add(legemiddel4); 
        Legemiddel legemiddel5 = new Vanlig("p_medisin", 100, 10);
        legemidler.add(legemiddel5); 

        // oppretter resept instanser og legger til i arrayList for hurtigere gjennomgang av tester
        Resept hvitResept = new HvitResept(legemiddel1, lege, 1, 2); 
        resepter.add(hvitResept); 
        Resept blaaResept = new BlaaResept(legemiddel2, lege, 2, 0);
        resepter.add(blaaResept); 
        Resept militerResept = new MiliterResept(legemiddel3, lege, 3, 5);
        resepter.add(militerResept); 
        Resept pResept1 = new PResept(legemiddel4, lege, 4);
        resepter.add(pResept1); 
        Resept pResept2 = new PResept(legemiddel5, lege, 5);
        resepter.add(pResept2);

        // test av id
        System.out.println("Test av id:");
        for (int i = 0; i < resepter.size(); i++) {
            boolean resultat = testReseptId(resepter.get(i), i + 1);
            if (resultat) {
                System.out.println("Test korrekt.");
            } else {
                System.out.println("Test feil.");
            }
        }

        System.out.println("--------");

        // test av legemiddel
        System.out.println("Test av legemiddel:");
        for (int i = 0; i < resepter.size(); i++) {
            boolean resultat = testLegemiddel(resepter.get(i), legemidler.get(i));
            if (resultat) {
                System.out.println("Test korrekt.");
            } else {
                System.out.println("Test feil.");
            }
        }

        System.out.println("--------");

        // test av pasient id
        System.out.println("Test av id:");
        for (int i = 0; i < resepter.size(); i++) {
            boolean resultat = testPasientId(resepter.get(i), i + 1);
            if (resultat) {
                System.out.println("Test korrekt.");
            } else {
                System.out.println("Test feil.");
            }
        }

        System.out.println("--------");

        // test av antall reit
        System.out.println("Test av id:");
        System.out.println(testPasientReit(pResept1, 3));
        System.out.println(testPasientReit(blaaResept, 0));

        System.out.println("--------");

        // test av bruk av resept
        System.out.println("Test av bruk av resept:");
        for (Resept resept : resepter) {
            System.out.println(resept.toString());
            System.out.println(resept.bruk());
            System.out.println(resept.hentReit());
            System.out.println();
        }

        System.out.println("--------");

        // test av farge på resept
        System.out.println("Test av farge paa resept:");
        System.out.println(testFargePaaResept(blaaResept, "Blaa"));
        System.out.println(testFargePaaResept(hvitResept, "Hvit"));
        System.out.println(testFargePaaResept(pResept1, "Hvit"));
        System.out.println(testFargePaaResept(militerResept, "Hvit"));

        System.out.println("--------");

        // test av pris på resept
        System.out.println("Test av pris paa resept:");

        System.out.println(testPrisPaaResept(blaaResept, 207, 52));
        System.out.println(testPrisPaaResept(hvitResept, 200, 200));
        System.out.println(testPrisPaaResept(militerResept, 200, 0));
        System.out.println(testPrisPaaResept(pResept1, 108, 0));
        System.out.println(testPrisPaaResept(pResept1, 200, 92));
        


    }

    // tester om id er korrekt
    public static boolean testReseptId(Resept resept, int forventetId) {
        int faktisk = resept.hentId();
        int forventet = forventetId;

        return faktisk == forventet;
    }

    // tester om legemiddel er korrekt
    public static boolean testLegemiddel(Resept resept, Legemiddel legemiddel) {
        Legemiddel faktisk = resept.hentLegemiddel();
        Legemiddel forventet = legemiddel;

        return faktisk == forventet;
    }

    // tester om pasient id er korrekt
    public static boolean testPasientId(Resept resept, int forventetId) {
        int faktisk = resept.hentPasientId();
        int forventet = forventetId;

        return faktisk == forventet;
    }

    // tester om antall reit er korrekt
    public static boolean testPasientReit(Resept resept, int forventetAntallReit) {
        int faktisk = resept.hentReit();
        int forventet = forventetAntallReit;

        return faktisk == forventet;
    }

    // tester bruk av resept
    public static boolean testBrukAvResept(Resept resept) {
        return resept.bruk();
    }

    // tester farge på resept
    public static boolean testFargePaaResept(Resept resept, String forventetFarge) {
        String faktisk = resept.farge();
        String forventet = forventetFarge;
        
        return faktisk.equals(forventet);
    }

    // tester pris på resept
    public static boolean testPrisPaaResept(Resept resept, int pris, int forventetPris) {
        int faktisk = resept.prisAaBetale(pris);
        int forventet = forventetPris;

        return faktisk == forventet;
    }
}
