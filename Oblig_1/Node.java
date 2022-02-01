public class Node {
    
    private int antallProsessorer;
    private int minnestørrelse;


    public Node(int antall, int minne) {

        this.antallProsessorer = antall;
        this.minnestørrelse = minne;

    }

    public int hentAntProsessorer() {
        return antallProsessorer;
    }

    public int hentMinnestørrelse() {
        return minnestørrelse;
    }


}
