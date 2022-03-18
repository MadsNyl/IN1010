public class IndeksertListe<T> extends Lenkeliste<T> {
    
    // // legger til element i gitt posisjon
    // public void leggTil(int pos, T x) {
    //     Node ny = new Node(x);
    //     Node node = navigoer(pos);
        
    //     if (stoerrelse() == 0) {
    //         start = ny;
    //     } else if (pos == 0) {
    //         ny.neste = start;
    //         start.forrige = ny;
    //         start = ny;
    //     } else if (pos == stoerrelse() - 1) {
    //         ny.neste = slutt;
    //         ny.forrige = slutt.forrige;
    //         slutt.forrige.neste = ny;
    //         slutt.forrige = ny;
    //     } else {
    //         ny.neste = node;
    //         ny.forrige = node.forrige;
    //         node.forrige.neste = ny;
    //         node.forrige = ny;
    //     }

    //     stoerrelse++;
    // }

    // public void sett(int pos, T x) {
    //     Node node = this.navigoer(pos);
    //     Node ny = new Node(x);

        
    //     if (node == start) {
    //         // hvis noden er første node
    //         ny.neste = start.neste;
    //         start = ny;

    //     } else if (node == slutt) {
    //         // hvis node er siste node
    //         System.out.println(ny.data);
    //         ny.forrige = slutt.forrige;
    //         slutt.forrige.neste = ny;
    //     } else {
    //         // hvis noden er mellom start og slutt
    //         ny.neste = node.neste;
    //         ny.forrige = node.forrige;
    //         node.forrige.neste = ny;
    //         node.neste.forrige = ny;

    //     }
    // }

    // erstatter element i gitt posisjon med nytt element
    public void sett (int pos, T x) {
        Node ny = new Node(x);

        if (pos < 0 || pos > stoerrelse - 1) throw new UgyldigListeindeks(pos);
        
        if (pos == 0) {
            ny.neste = start.neste;
            ny.forrige = start.forrige;
            start = ny;
        }
        else {
            int teller = 0;
            Node sjekk = start;

            // finner noden i gitt posisjon
            while (teller != pos) {
                sjekk = sjekk.neste;
                teller++;
            }
            ny.neste = sjekk.neste;
            ny.forrige = sjekk.forrige;

            if (sjekk == slutt) slutt = ny;

            sjekk.neste.forrige = ny;
            sjekk.forrige.neste = ny;
        }
        
    }


    // // fjerner elementet i gitt posisjon og returnerer det
    // public T fjern(int pos) {
    //     Node node = navigoer(pos);

    //     // sjekk om listen er tom
    //     if (stoerrelse <= 0) {
    //         return null;
    //     }

    //     if (node == start) {
    //         // hvis node er første node
    //         start.neste.forrige = null;
    //         start = start.neste;
    //         return start.data;
    //     } else if (node == slutt) {
    //         // hvis node er siste node
    //         slutt = slutt.forrige;
    //         slutt.neste = null;
    //         return slutt.data;
    //     } else {
    //         // hvis node er mellom start og slutt
    //         node.neste.forrige = node.forrige;
    //         node.forrige.neste = node.neste;
    //     }

    //     stoerrelse--;
    //     return node.data;
    // }

    // fjerner element i gitt posisjon
    public T fjern (int pos) throws UgyldigListeindeks {
        if (pos < 0 || pos > stoerrelse - 1) throw new UgyldigListeindeks(pos);

        int teller = 0;
        Node sjekk = start;

        while (teller != pos){
            sjekk = sjekk.neste;
            teller++;
        }
        // hvis noden er start, fjernes første node
        if (sjekk == start) sjekk.neste.forrige = null;
        // hvis snoden er siste, fjernes siste node
        else if (sjekk == slutt) sjekk.forrige.neste = null;

        else {
            sjekk.forrige.neste = sjekk.neste;
            sjekk.neste.forrige = sjekk.forrige;
        }

        stoerrelse--;
        return sjekk.data;

    }



}
