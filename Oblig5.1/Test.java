public class Test {
    public static void main(String[] args) {
        Monitor1 monitor = new Monitor1();
        Runnable leseTrad = new LeseTrad("TestDataLitenLike/fil1.csv", monitor);
        Thread traad = new Thread(leseTrad);
        traad.start();

        int antall = monitor.hentAntall();
        System.out.println(antall);

    }
}
