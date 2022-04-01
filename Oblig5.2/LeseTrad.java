import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private final String FILNAVN;
    private Monitor2 monitor;
    private CountDownLatch barriere;

    public LeseTrad(String filnavn, Monitor2 monitor, CountDownLatch barriere) {
        this.FILNAVN = filnavn;
        this.monitor = monitor;
        this.barriere = barriere;
    }

    // overskrider run metode
    @Override
    public void run() {
        HashMap<String, Subsekvens> kart = Monitor2.konverterTilSubsekvenser(FILNAVN);
        monitor.settInn(kart);
        barriere.countDown();
    }
}
