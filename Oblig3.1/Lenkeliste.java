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

    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse = 0;

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

        if (stoerrelse == 0) return null;

        for (int i = 0; i < stoerrelse - 1; i++) {
            svarstreng += peker.toString();
            svarstreng += " ";
            peker = peker.neste;
        }

        return svarstreng;
    }

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
}
