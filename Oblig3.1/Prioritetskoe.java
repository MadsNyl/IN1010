public class Prioritetskoe<T extends Comparable<T> > extends Lenkeliste<T> {
    

    @Override
    public void leggTil(T x) {
        // hvis listen er tom, så legges det til en node på vanlig måte.
        if (stoerrelse == 0) {
            super.leggTil(x);
            // stopper funksjen og returnerer.
            return;
        }

        Node node = start;
        for (int i = 0; i < stoerrelse; i++) {
            if (i != 0) {
                node = node.neste;
            }
            // hvis noden i listen er større enn ny node, så legges ny node inn i den posisjonen,
            if (node.data.compareTo(x) > 0) {
                this.leggTil(i, x);
                // stopper funksjen og returnerer.
                return;
            }
        }

        super.leggTil(x);
    }


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

}
