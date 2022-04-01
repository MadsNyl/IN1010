import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable{
    private Monitor2 monitor;
    private CountDownLatch barriere;
    private int antallFiler;

    public FletteTrad(Monitor2 monitor, CountDownLatch barriere, int antallFiler) {
        this.monitor = monitor;
        this.barriere = barriere;
        this.antallFiler = antallFiler;
    }

    // overskrider run metode
    @Override
    public void run() {
        try {
            if (antallFiler > 1) {
                Returverdi<HashMap<String, Subsekvens>> retur_verdi = monitor.hentUtToKart();
                System.out.println("Hentet ut to kart. antall er naa: " + monitor.hentAntall());
                monitor.slaaSammenToKart(retur_verdi.hentVerdi1(), retur_verdi.hentVerdi2());
                System.out.println("Slått sammen to kart. antall er naa: " + monitor.hentAntall());
            }
            barriere.countDown();
            System.out.println("Flettetraad teller er naa: " + barriere.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
