import java.util.HashMap;

public class FletteTrad implements Runnable{
    private final String FILNAVN;
    private Monitor2 monitor;

    public FletteTrad(String filnavn, Monitor2 monitor) {
        this.FILNAVN = filnavn;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            monitor.settInn(FILNAVN);
            System.out.println(monitor.antallKlare);
            ReturVerdi kartRetur = monitor.hentUt();
            HashMap<String, Subsekvens> nytt_kart = SubsekvensRegister.slaaSammen(kartRetur.hentKart1(), kartRetur.hentKart2());
            monitor.fjern();
            monitor.fjern();
            monitor.settInn(nytt_kart);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
