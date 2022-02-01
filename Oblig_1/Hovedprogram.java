public class Hovedprogram {
    
    public static void main(String[] args) {

        Dataklynge saga = new Dataklynge();

        for (int i = 0; i < 450; i++) {
            saga.settInnNodeIRack(new Node(4, 512));
        }


        for (int i = 0; i < 16; i++) {
            saga.settInnNodeIRack(new Node(8, 1024));
        }

        System.out.println("Noder med minst 128 GB: " + saga.noderMedNokMinne(128));
        System.out.println("Noder med minst 512 GB: " + saga.noderMedNokMinne(512));
        System.out.println("Noder med minst 1024 GB: " + saga.noderMedNokMinne(1024));
        System.out.println("Antall prosessorer: " + saga.antProsessorer());
        System.out.println("Antall racks: " + saga.antRacks());

    }

}
