import java.util.Iterator;

abstract class Lenkeliste<T> implements Liste<T> {
    // indre klasse Node
    class Node{
        public Node neste = null;
        public Node forrige = null;
        T data;
        Node (T data) {
            this.data = data;
        } 

        // toString metode for node
        @Override
        public String toString() {
            return data.toString();
        }
    }

    // indre klasse Iterator
    class LenkelisteIterator implements Iterator<T> {
        private int pos = 0;
        // overkjører next metode
        @Override
        public T next() { 
            pos++;
            return hent(pos - 1);
        }

        // overkjører hasNext metode
        @Override
        public boolean hasNext() { return pos < stoerrelse(); }
    }

    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse = 0;

    // overkjører iterator metode for å returnere et objekt av
    // LenkelisteIterator
    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }

    // retunerer størrelse på liste
    public int stoerrelse() {
        return stoerrelse;
    }

    // legg til node på slutten av listen
    public void leggTil(T x) {
        Node ny = new Node(x);
        
        if (start == null) {
            // hvis listen er tom
            start = ny;
        } else {
            ny.forrige = slutt;
            slutt.neste = ny;
        }

        slutt = ny;

        stoerrelse++;
    }

    // returnerer første element i listen
    public T hent() {
        return start.data;
    }

    // henter element i gitt posisjon
    public T hent(int pos) {
        Node node = start;

        if (pos < 0 || pos > stoerrelse - 1) {
            throw new UgyldigListeindeks(pos);
        }

        for (int i = 0; i < pos; i++) {
            node = node.neste;
        }

        return node.data;
    }

    // fjerner første element i listen og returnerer det
    public T fjern() throws UgyldigListeindeks {
        Node retur;
        
        // hvis listen er tom, returner ingenting
        if (stoerrelse == 0) throw new UgyldigListeindeks(0);
        // hvis det kun er ett element i listen
        else if (stoerrelse == 1) {
            retur = start;
            start = null;
            slutt = null;
        } else {
            // returnerer fjernet element, og flytter listen en plass frem
            retur = start;
            start.neste.forrige = null;
            start = start.neste;
        }
        stoerrelse--;
        return retur.data;
    }

    // toString metode
    @Override
    public String toString() {
        String svarstreng = "";
        Node peker = start;

        if (stoerrelse == 0) {
            return null;
        }

        for (int i = 0; i < stoerrelse - 1; i++) {
            svarstreng += peker.toString();
            svarstreng += " ";
            peker = peker.neste;
        }

        return svarstreng;
    }

    // legger til node på gitt posisjon
    public void leggTil (int pos, T x) throws UgyldigListeindeks{
        Node ny = new Node(x);

        if (pos < 0 || pos > stoerrelse) throw new UgyldigListeindeks(pos);
        
        else if (pos == stoerrelse){
            this.leggTil(x);
            return;
        }
        else {
            if (pos == 0) {
                start.forrige = ny;
                ny.neste = start;
                start = ny;
                stoerrelse++;
            }
            else {
                int teller = 0;
                Node sjekk = start;
                while (teller != pos) {
                    sjekk = sjekk.neste;
                    teller++;
                }
                ny.forrige = sjekk.forrige;
                ny.neste = sjekk; 
                sjekk.forrige.neste = ny;
                sjekk.forrige = ny;
                stoerrelse++;
            }

        }
    }


    // // itererer gjennom listen og returnerer element til gitt posisjon
    // public Node navigoer(int pos) {
    //     Node peker = start;

    //     // hvis pos er utenfor rekkevidde
    //     if (pos < 0 || pos > stoerrelse()) {
    //         throw new UgyldigListeindeks(pos);
    //     } else if (pos == 0) {
    //         // hvis posisjon er start
    //         peker = start;
    //     } else if (pos == stoerrelse() - 1) {
    //         // hvis posisjon er slutt
    //         peker = slutt;
    //     } else {
    //         // hvis posisjon er mellom start og slutt
    //         for (int i = 0; i < pos; i++) {
    //             peker = peker.neste;
    //         }
    //     }

    //     return peker;
    // }
}
