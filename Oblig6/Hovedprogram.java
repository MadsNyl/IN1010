import java.io.FileNotFoundException;

public class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        String fil = "labyrinter/7.in";

        Labyrint labyrint = new Labyrint(fil);

        System.out.println(labyrint.toString());

        labyrint.finnUtveiFra(1, 3);

    }
}
