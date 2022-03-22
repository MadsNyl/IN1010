import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();

        String test1 = "TestData/test1.csv";
        String test2 = "TestData/test2.csv";

        HashMap<String, Subsekvens> kart1 = SubsekvensRegister.lesFil(test1);
        HashMap<String, Subsekvens> kart2 = SubsekvensRegister.lesFil(test2);
        HashMap<String, Subsekvens> kart3 = SubsekvensRegister.slaaSammen(kart1, kart2);

        System.out.println(kart3);
    }
}
