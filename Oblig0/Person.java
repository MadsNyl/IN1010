public class Person {
    
    private Bil3 bil;

    // kaller på objekt av Bil3 i konstruktør og oppretter metode for å skrive ut bilnummer
    public Person(Bil3 bil) {
        this.bil = bil;
    }

    public void mittBilNummer() {
        bil.hentNummer();
    }

}
