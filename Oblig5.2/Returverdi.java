public class Returverdi<T> {
    private final T VERDI1;
    private final T VERDI2;

    public Returverdi(T verdi1, T verdi2) {
        this.VERDI1 = verdi1;
        this.VERDI2 = verdi2;
    }

    // hent verdi 1
    public T hentVerdi1() { return VERDI1; }

    // hent verdi 2
    public T hentVerdi2() { return VERDI2; }    
}