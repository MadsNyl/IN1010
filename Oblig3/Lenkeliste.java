public class Lenkeliste<T> implements Liste<T> {
    
    // implementerer indre klasse Node
    class Node {
        public Node neste = null; // peker til neste node
        public Node forrige = null; // peker til forrige node
        T data; // oppretter data for noden
        Node (T data) {
            this.data = data;
        }

        // to string metode for Node
        @Override
        public String toString()
        {
            return data.toString();
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
        Node node = start;

        if (pos == 0 || pos > stoerrelse) {
            System.out.println("Posisjon er ute av index.");
            node = null;
        }

        // pos - 1 for å få en mer brukervennlig aksessering
        // til listen med 1 som første posisjon (istedenfor 0)
        for (int i = 0; i < pos - 1; i++) {
            node = node.neste;
        }

        if (node.forrige != null) {
            System.out.println("Forrige node: " + node.forrige.data);
        }

        if (node.neste != null) {
            System.out.println("Neste node: " + node.neste.data);
        }
        System.out.println("Denne node: " + node.data);
        return node.data;
    }

    // legger til element på slutten av listen
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
        }

        stoerrelse++;
    }

    // legger til element på gitt posisjon
    public void leggTil(int pos, T x) {
        Node node = this.navigoer(pos);
        Node ny = new Node(x);

        if (node == start) {
            // hvis node er første node
            ny.neste = start;
            start.forrige = ny;
            start = ny;
        } else if (node == slutt) {
            // hvis node er siste node
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

    // fjern element fra gitt posisjon
    public T fjern(int pos) {
        Node node = navigoer(pos);

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

    // finner posisjon til node
    public Node navigoer(int pos) {
        Node peker = start;

        // hvis pos er utenfor rekkevidde
        if (pos <= 0 || pos > stoerrelse) {
            System.out.println("Index utenfor rekkevidde.");
            peker = null;
        } else if (pos == 1) {
            // hvis index er på starten
            peker = start;
        } else if (pos == stoerrelse) {
            // hvis index er på slutten
            peker = slutt;
        } else {
            // hvis pos er mellom start og slutt
           for (int i = 0; i < pos - 1; i++) {
                peker = peker.neste;
                // peker.forrige = peker.forrige
           } 
           
        }

        return peker;
    }

    // overkjører toString metode
    @Override
    public String toString()
    {
        String streng = "";
        Node peker = start;

        // iterer gjennom listen med noder
        for (int i = 0; i < stoerrelse; i++) {
            streng += peker.toString();
            streng += " ";
            peker = peker.neste;
        }

        return streng;
    }
}
