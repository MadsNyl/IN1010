import java.io.FileNotFoundException;

public class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        String fil = "labyrinter/3.in";

        Labyrint labyrint = new Labyrint(fil);

        System.out.println(labyrint.toString());

        Rute rute = labyrint.hentRute(11, 11);
        rute.gaa();
    }
}
