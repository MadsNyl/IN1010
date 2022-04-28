import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oblig6 {
    
    public static void main(String[] args) throws FileNotFoundException {
        String mappe = "labyrinter/";
        String fil = args[0];
        Labyrint labyrint = new Labyrint(mappe + fil);

        System.out.println(labyrint);

        Scanner input = new Scanner(System.in);

        boolean kjor = true;

        while (kjor) {
            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte).");

            String valg = input.nextLine();
            
            switch (valg) {
                case "-1":
                    kjor = false;
                    break;
                default:
                    String[] koordinater = valg.split(" ");
                    System.out.println("Aapninger:\n");
                    labyrint.finnUtveiFra(Integer.parseInt(koordinater[0]), Integer.parseInt(koordinater[1]));
                    break;
            }

            
        }
        
    }

}
