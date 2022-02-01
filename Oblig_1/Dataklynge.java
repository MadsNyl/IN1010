import java.util.ArrayList;

public class Dataklynge {
    
    private ArrayList<Rack> klynge = new ArrayList<Rack>();

    public void initialiserKlynge() {
        // initaliserer klyngen med sin første rack
        if (klynge.size() == 0) {
            klynge.add(new Rack());
        }

    }


    public void settInnNodeIRack(Node node) {
        boolean sattInn = false;
        initialiserKlynge();

        for (Rack rack : klynge) {
            // sjekker om rack er full og legger enten til ny node i eksisterende rack, eller lager en ny rack med ledig plass.
            if (!rack.erFull()) {
                rack.leggTilNode(node);
                sattInn = true;
                break;
            }
        }

        if (!sattInn) {
            Rack nyRack = new Rack();
            nyRack.leggTilNode(node);
            klynge.add(nyRack);
        }

    }

    public int antRacks() {
        // henter antall racks
        return klynge.size();
    }

    public void hentRacks() {
        for (Rack rack : klynge) {
            rack.hentNoder();
            System.out.println("-----");

        }
    }

    public int antProsessorer() {
        // henter totalt antall prosessorer i dataklyngen
        int totalt = 0;

        for (Rack rack : klynge) {

            totalt += rack.antProsessorer();

        }
        
        return totalt;
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        // henter antall noder med nok minne
        int totalt = 0;

        for (Rack rack : klynge) {
            totalt += rack.noderMedNokMinne(paakrevdMinne);
        }

        return totalt;

    }

}
