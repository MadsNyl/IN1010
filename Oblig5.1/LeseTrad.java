import java.util.HashMap;

public class LeseTrad implements Runnable {
    private final String FILNAVN;
    private SubsekvensRegister monitor;

    
    // konstruktør
    public LeseTrad(String filnavn, SubsekvensRegister monitor) {
        this.FILNAVN = filnavn;
        this.monitor = monitor;
    }

    // returner monitor
    public SubsekvensRegister hentMonitor() { return monitor; }


    @Override
    public void run() {
        HashMap<String, Subsekvens> kart = Monitor1.lesFil(FILNAVN);
        monitor.settInn(kart);
    } 
}