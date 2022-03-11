import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws UlovligUtskrift {
        
        Scanner tast = new Scanner(System.in);
        int tall = tast.nextInt();

        // if (tall == 1) {
        //     System.out.println(1);
        // } else if (tall == 2) {
        //     System.out.println(2);
        // } else if (tall == 3) {
        //     System.out.println(3);
        // }

        switch (tall) {
            case 1:
                System.out.println(1);
                break;
        
            case 2:
                System.out.println(2);
                break;
        }

    }
}