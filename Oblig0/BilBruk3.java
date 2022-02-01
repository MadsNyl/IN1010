public class BilBruk3 {
    
    public static void main(String[] args) {

        // oppretter et objekt av vil og et objekt av person.
        Bil3 bil = new Bil3("BS34767");

        Person person = new Person(bil);
        // kaller på metode fra person objekt
        person.mittBilNummer();

    }

}
