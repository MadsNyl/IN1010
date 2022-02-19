public class Lenkeliste<T> implements Liste<T> {
    
    // implementerer indre klasse Node
    class Node {
        Node neste = null; // peker til neste node
        Node forrige = null; // peker til forrige node
        T data;
        Node (T data) {
            this.data = data;
        }
    }

    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse = 0;

    // returnerer størrelse
    @Override
    public int stoerrelse() {
        return stoerrelse;
    }

    // hent element fra gitt posisjon
    @Override
    public T hent(int pos) {
        Node peker = start;
        System.out.println("Peker på start" + peker.data);
        // pos - 1 for å få en mer brukervennlig aksessering
        // til listen med 1 som første posisjon (istedenfor 0)
        System.out.println("Neste etter peker: " + start.neste.data);

        for (int i = 0; i < pos - 1; i++) {
            peker = peker.neste;
        }

        return peker.data;
    }

    // legger til element på slutten av liste
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);
        if (start == null) {
            start = ny;
            slutt = ny;
        } else {
            slutt.neste = ny;
            ny.forrige = slutt;
            slutt = ny;
            // System.out.println("Forrige node til slutt: " + slutt.forrige.data);
        }


        // System.out.println("Ny node: " + ny.data);
        // System.out.println("Start node: " + start.data);
        // System.out.println("Slutt node: " + slutt.data);

        stoerrelse++;
    }

    // legger til element på gitt posisjon
    public void leggTil(int pos, T x) {}

    // fjerner og retunerer første element
    @Override
    public T fjern() {
        T returverdi = start.data;
        if (stoerrelse == 1) {
            start = null;
            slutt = null;
        } else {
            start.neste.forrige = null;
            start = start.neste;
        }

        stoerrelse--;
        return returverdi;
    }

    // sett inn og overskrid element på gitt posisjon
    public void sett(int pos, T x) {
        Node ny = new Node(x);
        Node peker = start;

        if (stoerrelse == 1) {
            start = ny;
            slutt = ny;
        } else {
            for (int i = 0; i < pos - 1; i++) {
                peker = peker.neste;
            }

            System.out.println("Node til peker: " + peker.data);
            System.out.println("Node før peker: " + peker.forrige.data);
            System.out.println("Node etter peker: " + peker.neste.data);

            peker = ny;
        }



    }



    // fjern element fra gitt posisjon
    public T fjern(int pos) {return null;}

}
