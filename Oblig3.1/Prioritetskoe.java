public class Prioritetskoe<T extends Comparable<T> > extends Lenkeliste<T> {
    

    @Override
    public void leggTil(T x) {
        // hvis listen er tom, så legges det til en node på vanlig måte
        if (stoerrelse() == 0) {
            super.leggTil(x);
            // stopper funksjen og returnerer
            return;
        }

        Node node = start;
        for (int i = 0; i < stoerrelse; i++) {
            if (i != 0) {
                node = node.neste;
            }
            // hvis noden i listen er større enn ny node, så legges ny node inn i den posisjonen
            if (node.data.compareTo(x) > 0) {
                super.leggTil(i, x);
                // stopper funksjen og returnerer
                return;
            }
        }

        super.leggTil(x);
    }

}
