public class Rack {
    
    private int PLASSER = 12; 
    private Node[] nodeListe = new Node[PLASSER];

    public boolean erFull() {
        // iterer gjennom hvert element i arrayet for å se om alle elementer er fylt opp med Node objekter
        // for (int i = 0; i < nodeListe.length; i++) {
        //     if (nodeListe[i] == null) {
        //         return false;
        //     }
        // }

        if (nodeListe[nodeListe.length - 1] == null) {
            return false;
        } else {
            return true;
        }

    }

    public void hentNoder() {
        // henter noder i racket
        for (Node node : nodeListe) {
            System.out.println(node);
        }

    }

    public int antProsessorer() {
        // henter totalt antall prosessorer i racken
        int totalt = 0;
        for (Node node : nodeListe) {
            if (node != null) {
                totalt += node.hentAntProsessorer();
            }
        }

        return totalt;
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        // henter noder med nok minne
        int totalt = 0;

        for (Node node : nodeListe) {
            if (node != null && node.hentMinnestørrelse() >= paakrevdMinne) {
                totalt += 1;
            }
        }

        return totalt;

    }

    public Node[] hentRack() {
        return nodeListe;
    }

    public void leggTilNode(Node node) {
        // legger til node i racket der det er ledig plass
        for (int i = 0; i < PLASSER; i++) {
            if (nodeListe[i] == null) {
                nodeListe[i] = node;
                break;
            }   
        }

    }

}
