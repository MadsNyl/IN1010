import java.io.FileNotFoundException;

public class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        String fil = "labyrinter/1.in";

        Labyrint labyrint = new Labyrint(fil);

        System.out.println(labyrint.toString());
    }
}
