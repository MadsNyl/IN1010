public class Test {
    public static void main(String[] args) {
        String filnavn = "Data/Keck0000.csv";

        Repertoar repertoar = new Repertoar(filnavn);
        repertoar.lesInnSekvenser();
        repertoar.konverter();
    }
}