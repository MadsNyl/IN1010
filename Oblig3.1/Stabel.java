public class Stabel<T> extends Lenkeliste<T> {
    
    // overskrider leggTil metode slike at noden legges først i listen
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);

        if (start == null) {
            // hvis listen er tom
            start = ny;
        } else {
            start.forrige = ny;
            ny.neste = start;
            start = ny;
        }

        stoerrelse++;
    }

}
