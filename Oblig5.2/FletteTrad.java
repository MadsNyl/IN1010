import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable{
    private Monitor2 monitor;
    private CountDownLatch barriere;

    public FletteTrad(Monitor2 monitor, CountDownLatch barriere) {
        this.monitor = monitor;
        this.barriere = barriere;
    }

    // overskrider run metode
    @Override
    public void run() {
        // System.out.println("Flettetråd er startet.");
        try { Thread.sleep(500); }
        catch (InterruptedException e) { e.printStackTrace(); }
        try {
            if (monitor.hentAntall() > 1) {
                Returverdi<HashMap<String, Subsekvens>> retur_verdi = monitor.hentUtToKart();
                monitor.slaaSammenToKart(retur_verdi.hentVerdi1(), retur_verdi.hentVerdi2());
                run();
            }
            barriere.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
