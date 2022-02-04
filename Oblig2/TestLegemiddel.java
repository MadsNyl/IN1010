public class TestLegemiddel {
    
    public static void main(String[] args) throws Exception {

        Narkotisk narkotisk = new Narkotisk("narkotisk", 200, 5, 2);
        Vanedannende vane = new Vanedannende("vanedannende", 150, 3, 1);
        Vanlig vanlig = new Vanlig("vanlig", 4000, 0);

    }

    // metode for å teste legemiddelId
    public static boolean testLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        int faktisk = legemiddel.hentId();
        int forventet = forventetLegemiddelId;

        return faktisk == forventet;
    }

    // metode for å teste navn
    public static boolean testLegemiddelNavn(Legemiddel legemiddel, String forventetLegemiddelNavn) {
        String faktisk = legemiddel.hentNavn();
        String forventet = forventetLegemiddelNavn;

        return faktisk.equals(forventet);
    }

    // metode for å teste pris
    public static boolean testLegemiddelPris(Legemiddel legemiddel, int forventetLegemiddelPris) {
        int faktisk = legemiddel.hentPris();
        int forventet = forventetLegemiddelPris;

        return faktisk == forventet;
    }

    // metode for å teste virkestoff
    public static boolean testLegemiddelVirkestoff(Legemiddel legemiddel, double forventetLegemiddelVirkestoff) {
        double faktisk = legemiddel.hentVirkestoff();
        double forventet = forventetLegemiddelVirkestoff;

        return faktisk == forventet;
    }

}
