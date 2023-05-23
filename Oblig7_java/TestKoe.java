public class TestKoe {
    public static void main(String[] args) {
        Koe<Test> liste = new Koe<>();
        Test en = new Test(2, 8);
        Test to = new Test(2, 9);
        Test tre = new Test(2, 10);

        liste.leggTil(en);
        liste.leggTil(to);
        liste.leggTil(tre);

        System.out.println("Forste liste");
        for (Test e : liste) System.out.println(e);


        System.out.println("\nAndre liste");
        int teller = 0;

        while (teller < 5) {

            Test ny = new Test(liste.hent(liste.storrelse - 1).hentX(), liste.hent(liste.storrelse() - 1).hentY() - 1);

            for (int i = 0; i < liste.storrelse(); i++) {
                if (i != liste.storrelse() - 1) {
                    liste.hent(i).endreX(liste.hent(i + 1).hentX());
                    liste.hent(i).endreY(liste.hent(i + 1).hentY());
                }
            }
            liste.leggTil(ny);
            liste.fjern();
            for (Test e : liste) {
                System.out.println(e);
            }
            System.out.println("\n");
            teller++;
        }
        
        System.out.println("\nTredje liste");
        for (Test e : liste) {
            System.out.println(e);
        }
    }
}
