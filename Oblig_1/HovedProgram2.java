import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HovedProgram2 {
    
    public static void main(String[] args) throws Exception {

        Scanner fil = new Scanner(new File("dataklynge.txt"));
        int nummer = 0;

        while (fil.hasNextLine()) {
            nummer++;
            Dataklynge klynge = new Dataklynge();
            String[] linje = fil.nextLine().split(" ");
            int antallNoder = Integer.parseInt(linje[0]);
            int antallProsessorer = Integer.parseInt(linje[1]);
            int mengdeMinne = Integer.parseInt(linje[2]);
            for (int i = 0; i < antallNoder; i++) {
                klynge.settInnNodeIRack(new Node(antallProsessorer, mengdeMinne));
            }

            System.out.println("Info om klynge nummer " + nummer + ":");
            System.out.println("Noder med minst 128 GB: " + klynge.noderMedNokMinne(128));
            System.out.println("Noder med minst 512 GB: " + klynge.noderMedNokMinne(512));
            System.out.println("Noder med minst 1024 GB: " + klynge.noderMedNokMinne(1024));
            System.out.println("Antall prosessorer: " + klynge.antProsessorer());
            System.out.println("Antall racks: " + klynge.antRacks());
            System.out.println();
        }   

    }

}
