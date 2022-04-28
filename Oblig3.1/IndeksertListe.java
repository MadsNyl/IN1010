public class IndeksertListe<T> extends Lenkeliste<T> {

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

    // henter element i gitt posisjon
    public T hent(int pos) throws UgyldigListeindeks {
        Node node = start;

        if (pos < 0 || pos > stoerrelse - 1) throw new UgyldigListeindeks(pos);

        // hvis pos er start, returneres første node
        if (pos == 0) return node.data;

        // finner noden i gitt posisjon
        for (int i = 0; i < pos; i++) node = node.neste;

        return node.data;
    }

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
