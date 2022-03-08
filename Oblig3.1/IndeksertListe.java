public class IndeksertListe<T> extends Lenkeliste<T> {
    
    // legger til element i gitt posisjon
    public void leggTil(int pos, T x) {
        Node ny = new Node(x);
        Node node = navigoer(pos);
        
        if (stoerrelse() == 0) {
            start = ny;
        } else if (node == start) {
            // hvis pos er start
            ny.neste = start;
            start.forrige = ny;
            start = ny;
        } else if (node == slutt) {
            // hvis pos er siste element
            ny.forrige = slutt.forrige;
            ny.neste = slutt;
            slutt.forrige.neste = ny;
            slutt = ny;
        } else {
            // hvis noden er mellom start og slutt
            ny.neste = node;
            ny.forrige = node.forrige;
            node.forrige.neste = ny;
            node.forrige = ny;
        }

        stoerrelse++;
    }

    // erstatter element i gitt posisjon med nytt element
    public void sett(int pos, T x) {
        Node node = this.navigoer(pos);
        Node ny = new Node(x);

        
        if (node == start) {
            // hvis noden er første node
            ny.neste = start.neste;
            start = ny;

        } else if (node == slutt) {
            // hvis node er siste node
            System.out.println(ny.data);
            ny.forrige = slutt.forrige;
            slutt.forrige.neste = ny;
        } else {
            // hvis noden er mellom start og slutt
            ny.neste = node.neste;
            ny.forrige = node.forrige;
            node.forrige.neste = ny;
            node.neste.forrige = ny;

        }
    }

    // henter element i gitt posisjon
    public T hent(int pos) {
        Node node = start;

        if (pos < 0 || pos > stoerrelse - 1) {
            throw new UgyldigListeindeks(pos);
        }

        if (pos == 0) {
            return node.data;
        }
        // pos - 1 for å få en mer brukervennlig aksessering
        // til listen med 1 som første posisjon (istedenfor 0)
        for (int i = 0; i < pos; i++) {
            node = node.neste;
        }

        return node.data;
    }

    // fjerner elementet i gitt posisjon og returnerer det
    public T fjern(int pos) {
        Node node = navigoer(pos);

        // sjekk om listen er tom
        if (stoerrelse <= 0) {
            return null;
        }

        if (node == start) {
            // hvis node er første node
            start.neste.forrige = null;
            start = start.neste;
            return start.data;
        } else if (node == slutt) {
            // hvis node er siste node
            slutt = slutt.forrige;
            slutt.neste = null;
            return slutt.data;
        } else {
            // hvis node er mellom start og slutt
            node.neste.forrige = node.forrige;
            node.forrige.neste = node.neste;
        }

        stoerrelse--;
        return node.data;
    }



}
