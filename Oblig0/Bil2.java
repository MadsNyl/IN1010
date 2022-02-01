public class Bil2 extends Bil1 {
    
    private String bilnummer;

    // oppretter konstruktør 
    public Bil2(String bilnummer) {
        this.bilnummer = bilnummer;
    }

    // overkjører den arvede metoden fra Bil1
    @Override
    public void skrivUt() {

        System.out.println(bilnummer);

    }


}
