import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Legesystem {
    
    // oppretter lenkelister for de ulike objektene
    private static IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    private static IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    private static Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
    private static IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public static void main(String[] args) throws UlovligUtskrift {
        kjorProgram();
    }

    // leser inn data fra fil
    private static void lesFil(String filnavn) throws UlovligUtskrift {

        try {
            File fil = new File(filnavn);
            Scanner leser = new Scanner(fil);
            int hashtagTeller = 0;
            
            // leser gjennom hver linje
            while (leser.hasNextLine()) {
                // leser hver linje og splitter opp til array
                String data = leser.nextLine();
                String[] linje = data.split("");

                // sjekker om første bokstav er #
                if (linje[0].contains("#")) {
                    // øker hashtagTeller med en
                    hashtagTeller++;
                }

                // sjekker hastagTeller for å se i hvilken seksjon i filen vi er i
                if (hashtagTeller == 1) {
                    // hvis linjen ikke inneholder # så leses linjen og det opprettes et Pasient objekt
                    if (!data.contains("#")) {
                        String[] pasientData  = data.split(",");
                        Pasient pasient = new Pasient(pasientData[0], pasientData[1]);
                        // System.out.println(pasient.toString());
                        // legger til objekt i pasientliste
                        pasientListe.leggTil(pasient);
                    }
                } else if (hashtagTeller == 2) {
                    // hvis linjen ikke inneholder # så leses linjen og det opprettes et Legemiddel objekt
                    if (!data.contains("#")) {
                        String[] legemiddelData = data.split(",");
                        // sjekker hva slags legemiddel det er og oppretter Legemiddel-objekter ut av det
                        if (legemiddelData[1].contains("vanlig")) {
                            String navn = legemiddelData[0];
                            int pris = Integer.parseInt(legemiddelData[2]);
                            double virkestoff = Double.parseDouble(legemiddelData[3]);
                            Legemiddel vanlig = new Vanlig(navn, pris, virkestoff);
                            // System.out.println(vanlig.toString());
                            // legger til objekt i legemiddelliste
                            legemiddelListe.leggTil(vanlig);
                        } else if (legemiddelData[1].contains("vanedannende")) {
                            // hvis legemiddeltypen er vanedannende
                            String navn = legemiddelData[0];
                            int pris = Integer.parseInt(legemiddelData[2]);
                            double virkestoff = Double.parseDouble(legemiddelData[3]);
                            int styrke = Integer.parseInt(legemiddelData[4]);
                            Legemiddel vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                            // System.out.println(vanedannende.toString());
                            // legger til objekt i legemiddelliste
                            legemiddelListe.leggTil(vanedannende);
                        } else if (legemiddelData[1].contains("narkotisk")) {
                            // hvis legemiddeltypen er narkotisk
                            String navn = legemiddelData[0];
                            int pris = Integer.parseInt(legemiddelData[2]);
                            double virkestoff = Double.parseDouble(legemiddelData[3]);
                            int styrke = Integer.parseInt(legemiddelData[4]);
                            Legemiddel narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                            // System.out.println(narkotisk.toString());
                            // legger til objekt i legemiddelliste
                            legemiddelListe.leggTil(narkotisk);
                        }
                    }
                } else if (hashtagTeller == 3) {
                    // hvis linjen ikke starter med # så leses linjen og det opprettes et Lege objekt
                    if (!data.contains("#")) {
                        String[] legeData = data.split(",");
                        // sjekker hva slags lege det er og oppretter Lege-objekter ut av det
                        if (legeData[1].length() <= 1 & legeData[1].startsWith("0")) {
                            String navn = legeData[0];
                            Lege lege = new Lege(navn);
                            // System.out.println(lege.toString());
                            // legger til objekt i legeliste
                            legeListe.leggTil(lege);
                        } else {
                            String navn = legeData[0];
                            String kontrollID = legeData[1];
                            Lege spesialist = new Spesialist(navn, kontrollID);
                            // System.out.println(spesialist.toString());
                            // legger til objekt i legeliste
                            legeListe.leggTil(spesialist);
                        }
                    }
                } else if (hashtagTeller == 4) {
                    String[] reseptData = data.split(",");
                    String legeNavn = null;


                    // itererer gjennom legelisten for å finne riktig lege
                    for (Lege lege : legeListe) {
                        legeNavn = lege.hentNavn();

                        // hvis legenavnet i resepten er lik en av legene i listen, så er det match
                        if (legeNavn != null & legeNavn.equals(reseptData[1])) {
                            // får tak i lege-objektet
                            // legeobjekter oppretter en resept med data fra reseptData
                            if (reseptData[3].contains("hvit")) {
                                // itererer gjennom legemiddelListe for å finne legemiddel med riktig ID
                                for (Legemiddel legemiddel : legemiddelListe) {
                                    // ser etter match i ID
                                    int sjekkLegemiddelId = Integer.parseInt(reseptData[0]);
                                    int legemiddelID = legemiddel.hentId();

                                    if (sjekkLegemiddelId == legemiddelID) { 
                                        // iterere pasientliste for å finne riktig pasient
                                        for (Pasient pasient : pasientListe) {
                                            int sjekkPasientId = Integer.parseInt(reseptData[2]);
                                            int pasientID = pasient.hentID(); 

                                            if (sjekkPasientId == pasientID) {
                                                int reit = Integer.parseInt(reseptData[4]);
                                                // oppretter en hvit resept med legen
                                                Resept resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                                                pasient.leggTilResept(resept);
                                                reseptListe.leggTil(resept);
                                            }
                                        }
                                    }
                                }
                            } else if (reseptData[3].contains("blaa")) {
                                // itererer gjennom legemiddelListe for å finne legemiddel med riktig ID
                                for (Legemiddel legemiddel : legemiddelListe) {
                                    // ser etter match i ID
                                    int sjekkLegemiddelId = Integer.parseInt(reseptData[0]);
                                    int legemiddelID = legemiddel.hentId();

                                    if (sjekkLegemiddelId == legemiddelID) { 
                                        // iterere pasientliste for å finne riktig pasient
                                        for (Pasient pasient : pasientListe) {
                                            int sjekkPasientId = Integer.parseInt(reseptData[2]);
                                            int pasientID = pasient.hentID(); 

                                            if (sjekkPasientId == pasientID) {
                                                int reit = Integer.parseInt(reseptData[4]);
                                                // oppretter en blå resept med legen
                                                Resept resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                                                pasient.leggTilResept(resept);
                                                reseptListe.leggTil(resept);
                                            }
                                        }
                                    }
                                }
                            } else if (reseptData[3].contains("p")) {
                                // itererer gjennom legemiddelListe for å finne legemiddel med riktig ID
                                for (Legemiddel legemiddel : legemiddelListe) {
                                    // ser etter match i ID
                                    int sjekkLegemiddelId = Integer.parseInt(reseptData[0]);
                                    int legemiddelID = legemiddel.hentId();

                                    if (sjekkLegemiddelId == legemiddelID) { 
                                        // iterere pasientliste for å finne riktig pasient
                                        for (Pasient pasient : pasientListe) {
                                            int sjekkPasientId = Integer.parseInt(reseptData[2]);
                                            int pasientID = pasient.hentID(); 

                                            if (sjekkPasientId == pasientID) {
                                                int reit = Integer.parseInt(reseptData[4]);
                                                // oppretter en P resept med legen
                                                Resept resept = lege.skrivPResept(legemiddel, pasient, reit);
                                                pasient.leggTilResept(resept);
                                                reseptListe.leggTil(resept);
                                            }
                                        }
                                    }
                                }
                            } else if (reseptData[3].contains("militaer")) {
                                // itererer gjennom legemiddelListe for å finne legemiddel med riktig ID
                                for (Legemiddel legemiddel : legemiddelListe) {
                                    // ser etter match i ID
                                    int sjekkLegemiddelId = Integer.parseInt(reseptData[0]);
                                    int legemiddelID = legemiddel.hentId();

                                    if (sjekkLegemiddelId == legemiddelID) { 
                                        // iterere pasientliste for å finne riktig pasient
                                        for (Pasient pasient : pasientListe) {
                                            int sjekkPasientId = Integer.parseInt(reseptData[2]);
                                            int pasientID = pasient.hentID(); 

                                            if (sjekkPasientId == pasientID) {
                                                // oppretter en militær resept med legen
                                                Resept resept = lege.skrivMilResept(legemiddel, pasient);
                                                pasient.leggTilResept(resept);
                                                reseptListe.leggTil(resept);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
                

            }
            System.out.println("Fil er lest inn, og legesystemet er oppdatert.\n");

            leser.close();
        } catch (FileNotFoundException e) {
            System.out.println("En feil oppsto. Kunne ikke finne filen.");
            e.printStackTrace();
            System.out.println("\nIngen data ble skrevet inn.");
            System.out.println("\nStarter opp system.");
        }

    }
    
    // kjører program
    private static void kjorProgram() throws UlovligUtskrift { 
        // starter opp program
        System.out.println("----------  LEGESYSTEM  ----------\n");
        
        // henter .txt filer
        String mappeSti = Paths.get(".").toAbsolutePath().normalize().toString();
        final File mappe = new File(mappeSti);
        hentDataFiler(mappe);

        // opretter en scanner
        Scanner tastatur = new Scanner(System.in);
        String filnavn = tastatur.nextLine();
        lesFil(filnavn);
        kommandoMeny();
        kommandoValg(tastatur);
        tastatur.close();
    }

    // henter filer fra mappe som har .txt format
    private static void hentDataFiler(final File mappe) {
        System.out.println("\nDet ble funnet følgende datafil(er), tilgjengelig for deg, for innlesing:");

        for (final File fil : mappe.listFiles()) {
            if (fil.isDirectory()) {
                hentDataFiler(fil);
            } else {
                String filnavn = fil.toString();
                if (filnavn.contains(".txt")) System.out.println("\n - " + fil.getName());
            }
        }

        System.out.println("\nSkriv inn din datafil for innlesing. Hvis din fil ikke kom opp, prøv likevel aa skrive inn filnavn:\n");
    }

    // printer ut meny
    private static void kommandoMeny() {
        System.out.println("\n----------  LEGESYSTEM STARTET  ----------\n");
        String utskrift = "";
        utskrift += "Du har folgende valg: \n";
        utskrift += "Skriv ut fullstendig oversikt (1): \n";
        utskrift += "Opprett og legg til nye elementer [lege / pasient / resept / legemiddel] (2): \n";
        utskrift += "Bruk eksisterende resept (3): \n";
        utskrift += "Hent statistikk (4): \n";
        utskrift += "Skriv all data til fil (5): \n";
        utskrift += "Avslutt programvare (0): \n";
        System.out.println(utskrift);
    }

    // går gjennom menyvalg
    private static void kommandoValg(Scanner tastatur) throws UlovligUtskrift {
        int valgTall = tastatur.nextInt();
        switch (valgTall) {
            case 0:
                System.out.println("Avslutter programvaren.");
                System.exit(0);
                break;

            case 1:
                skrivUtOversikt();
                kommandoMeny();
                kommandoValg(tastatur);

            case 2:
                opprettElement(tastatur);
                kommandoMeny();
                kommandoValg(tastatur);
            
            case 3:
                brukResept(tastatur);
                kommandoMeny();
                kommandoValg(tastatur);

            case 4:
                hentStatistikk(tastatur);
                kommandoMeny();
                kommandoValg(tastatur);
            
            case 5:
                leggInnData(tastatur);
                kommandoMeny();
                kommandoValg(tastatur);
        
            default:
                System.out.println("Feil inntastning, velg et tall mellom 0 og 5.");
                kommandoValg(tastatur);
        }
    }

    // henter fullstendig oversikt over pasienter, leger, legemidler, resepter
    private static void skrivUtOversikt() {

        // skriver ut leger
        System.out.println("\n----------  LEGER  ----------\n");

        // skriver ut leger
        for (Lege lege : legeListe) {
            // skriv ut info om lege
            System.out.println("\n----- Ny lege -----\n");
            System.out.println(lege);

            // skriver ut resept for hver lege
            if (lege.hentAntallResepter() > 0) {
                System.out.println("\n----- Resept(er) -----\n");
                for (Resept resept : lege.hentUtskrevneResepter()) { 
                    System.out.println("\nReseptID: " + resept.hentId());
                    System.out.println(resept);
                }
            }   
        }

    }

    // gir bruker mulighet til å opprette objekt av lege, pasient, resept eller legemiddel
    private static void opprettElement(Scanner tastatur) throws UlovligUtskrift {
        // skriv ut overskrift
        System.out.println("\n----------  OPPRETT ET ELEMENT  ----------\n");
        String utskrift = "";
        utskrift += " - Opprett en lege (1)\n";
        utskrift += " - Opprett en pasient (2)\n";
        utskrift += " - Opprett en resept (3)\n";
        utskrift += " - Opprett et legemiddel (4)\n";
        System.out.println(utskrift);
        int tallValg = tastatur.nextInt();
        tastatur.nextLine();

        switch (tallValg) {
            case 1:
                opprettLege(tastatur);
                break;
            
            case 2:
                opprettPasient(tastatur);
                break;
            
            case 3:
                opprettResept(tastatur);
                break;

            case 4:
                opprettLegemiddel(tastatur);
                break;
        
            default:
                break;
        }

    }

    // oppretter et Lege-objekt
    private static void opprettLege(Scanner tastatur) {
        // spør etter navnet til legen
        System.out.println("\n----- Oppretter en lege -----\n");
        System.out.println("Hva er navnet til legen?");

        String navn = tastatur.nextLine();

        // spør om lege er spesialist
        System.out.println("\nEr legen en spesialist? (J/N): \n");
        String erSpesialist = tastatur.nextLine();

        if (erSpesialist.contains("J")) {
            System.out.println("\nOppgi en kontrollID: ");
            String kontrollID = tastatur.nextLine();
            Spesialist spesialist = new Spesialist(navn, kontrollID);
            legeListe.leggTil(spesialist);
            System.out.println("Din legespesisalist med navn: " + spesialist.hentNavn() + ", og ID: " + spesialist.hentKontrollID() + ", er lagt inn i systemet." );
        } else if(erSpesialist.contains("N")) {
            // oppretter et Lege-objekt
            Lege lege = new Lege(navn);
            legeListe.leggTil(lege);
            System.out.println("Din lege med navn: " + lege.hentNavn() + ", er lagt inn i systemet.");
        }
        
    }

    // oppretter et Pasient-objekt
    private static void opprettPasient(Scanner tastatur) {
        System.out.println("\n----- Oppretter en pasient -----\n");
        System.out.println("Hva er navnet til pasienten?");

        String navn = tastatur.nextLine();

        System.out.println("\nHva er pasienten sitt fodselsnummer?");
        String fodselsnummer = tastatur.nextLine();

        // oppretter et Pasient-objekt
        Pasient pasient = new Pasient(navn, fodselsnummer);
        pasientListe.leggTil(pasient);
        System.out.println("Din pasient med navn: " + pasient.hentNavn() + ", og fodselsnummer: " + pasient.hentFodselsnummer() + ", er lagt inn i systemet.");
    }

    // oppretter et Resept-objekt
    private static void opprettResept(Scanner tastatur) throws UlovligUtskrift {
        // skriver ut overskrift
        System.out.println("\n----- Oppretter en resept -----\n");

        // sjekker om det eksiterer en lege som kan skrive ut resepten
        if (legeListe.stoerrelse() <= 0) System.out.println("Det finnes ingen leger som kan opprette en resept for deg. Opprett en lege først.");
        // sjekker om det eksisterer et legemiddel som kan brukes i resepten
        if (legemiddelListe.stoerrelse() <= 0) System.out.println("Det finnes ikke noe legemiddel som kan brukes i resepten. Opprett et legemiddel først.");
        // sjekker om det eksisterer en pasient som kan brukes i resepten
        if (pasientListe.stoerrelse() <= 0) System.out.println("Det finnes ingen pasient som kan ta imot resepten. Opprett en pasient først.");


        // valg av objekter
        Lege lege = null;
        Legemiddel legemiddel = null;
        Pasient pasient = null;

        // viser frem leger
        System.out.println("\nVelg en lege:");
        for (Lege element : legeListe) {
            // sjekker om lege er spesialist
            boolean erSpesialist = element instanceof Spesialist;
            if (erSpesialist) System.out.println("\n - " + element.hentNavn() + " (spesialist)");
            else System.out.println("\n - " + element.hentNavn() + " (lege)");
        }
        // velger en lege
        String legeValg = tastatur.nextLine();
        for (Lege element : legeListe) {
            String navn = element.hentNavn().strip();
            if (legeValg.contains(navn)) lege = element;
        }
        if (lege != null) System.out.println("\nDu har valgt følgende lege: " + lege.hentNavn() + ".\n");

        // viser frem pasienter
        System.out.println("\nVelg en pasient:");
        for (Pasient element : pasientListe) System.out.println("\n - " + element.hentNavn());

        // velger en pasient
        String pasientValg = tastatur.nextLine();
        for (Pasient element : pasientListe) {
            String navn = element.hentNavn().strip();
            if (pasientValg.contains(navn)) pasient = element;
        }
        if (lege != null) System.out.println("\nDu har valgt følgende pasient: " + pasient.hentNavn() + ".\n");

        // viser frem legemidler
        System.out.println("\nVelg et legemiddel:");
        for (Legemiddel element : legemiddelListe) System.out.println("\n - " + element.hentNavn() + " (" + element.hentType() + ")");
        // velger et legemiddel
        String legemiddelValg = tastatur.nextLine();
        for (Legemiddel element : legemiddelListe) {
            String navn = element.hentNavn().strip();
            System.out.println(navn);
            if (legemiddelValg.contains(navn)) legemiddel = element;
        } 
        if (legemiddel != null) System.out.println("\nDu har valgt følgende legemiddel: " + legemiddel.hentNavn() + ".\n");


        // sjekker hvilken type resept som skal opprettes
        System.out.println("Hvilken type resept vil du opprette?");
        String reseptUtvalg = "";
        reseptUtvalg += "\n - Hvit resept (1)";
        reseptUtvalg += "\n - Blaa resept (2)";
        reseptUtvalg += "\n - P resept (3)";
        reseptUtvalg += "\n - Militaer resept (4)";
        System.out.println(reseptUtvalg);
        int reseptValg = tastatur.nextInt();
        tastatur.nextLine();

        // oppretter de ulike reseptene
        switch (reseptValg) {
            case 1:
                opprettHvitResept(tastatur, lege, legemiddel, pasient);
                break;
            
            case 2:
                opprettBlaaResept(tastatur, lege, legemiddel, pasient);
                break;
            
            case 3:
                opprettPResept(tastatur, lege, legemiddel, pasient);
                break;
            
            case 4:
                opprettMilitaerResept(tastatur, lege, legemiddel, pasient);
                break;
        
            default:
                break;
        }


    }

    // oppretter hvit resept
    private static void opprettHvitResept(Scanner tastatur, Lege lege, Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        System.out.println("\n----- Oppretter hvit resept -----\n");
        System.out.println("Hvor mange reit skal resepten ha?\n");

        int antallReit = tastatur.nextInt();

        // oppretter en hvit resept
        Resept resept = lege.skrivHvitResept(legemiddel, pasient, antallReit);
        reseptListe.leggTil(resept);
        pasient.leggTilResept(resept);

        System.out.println("Det er nå opprettet en ny hvit resept.");

    }
    // oppretter blaa resept
    private static void opprettBlaaResept(Scanner tastatur, Lege lege, Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        System.out.println("\n----- Oppretter blaa resept -----\n");
        System.out.println("Hvor mange reit skal resepten ha?\n");

        int antallReit = tastatur.nextInt();

        // oppretter en blå resept
        Resept resept = lege.skrivBlaaResept(legemiddel, pasient, antallReit);
        reseptListe.leggTil(resept);
        pasient.leggTilResept(resept);

        System.out.println("Det er nå opprettet en ny blaa resept.");
    }
    // oppretter P resept
    private static void opprettPResept(Scanner tastatur, Lege lege, Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        System.out.println("\n----- Oppretter P resept -----\n");
        System.out.println("Hvor mange reit skal resepten ha?\n");

        int antallReit = tastatur.nextInt();

        // oppretter en p resept
        Resept resept = lege.skrivPResept(legemiddel, pasient, antallReit);
        reseptListe.leggTil(resept);
        pasient.leggTilResept(resept);

        System.out.println("Det er nå opprettet en ny P resept.");
    }
    // oppretter militaer resept
    private static void opprettMilitaerResept(Scanner tastatur, Lege lege, Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        System.out.println("\n----- Oppretter militaer resept -----\n");

        // oppretter en militær resept
        Resept resept = lege.skrivMilResept(legemiddel, pasient);
        reseptListe.leggTil(resept);
        pasient.leggTilResept(resept);

        System.out.println("Det er nå opprettet en ny militaer resept.");
    }

    // oppretter et Legemiddel-objekt
    private static void opprettLegemiddel(Scanner tastatur) {
        System.out.println("\n----- Oppretter et legemiddel -----\n");
        // henter navn
        System.out.println("Hva er navnet?");
        String navn = tastatur.nextLine();
        // henter pris
        System.out.println("\nHva er prisen?");
        int pris = tastatur.nextInt();
        // henter virkestoff
        System.out.println("\nHva er mengde virkestoff?");
        int virkestoff = tastatur.nextInt();
        // henter legemiddeltype
        String utskrift = "";
        utskrift += "\nVelg et legemiddel:";
        utskrift += "\n - vanlig (1)";
        utskrift += "\n - vanedannende (2)";
        utskrift += "\n - narkotisk (3)";
        System.out.println(utskrift);
        int tallValg = tastatur.nextInt();

        // oppretter Legemiddel-objekt
        switch (tallValg) {
            case 1:
                Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                legemiddelListe.leggTil(vanlig);
                System.out.println("Ditt legemiddel med navn: " + vanlig.hentNavn() + ", er lagt inn i systemet.");
                break;

            case 2:
                System.out.println("\nHva er styrken?");
                int styrke = tastatur.nextInt();
                Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                legemiddelListe.leggTil(vanedannende);
                System.out.println("Ditt legemiddel med navn: " + vanedannende.hentNavn() + ", er lagt inn i systemet.");
                break;
            
            case 3:
                System.out.println("\nHva er styrken?");
                int styrke_n = tastatur.nextInt();
                Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke_n);
                legemiddelListe.leggTil(narkotisk);
                System.out.println("Ditt legemiddel med navn: " + narkotisk.hentNavn() + ", er lagt inn i systemet.");
                break;
        
            default:
                break;
        }
    }

    // lar bruker bruke en eksisterende resept
    private static void brukResept(Scanner tastatur) {
        System.out.println("\n----------  BRUK RESEPT ----------\n");

        // henter pasienter og gir et valg
        int teller = 0;
        System.out.println("Hvilken pasient vil du se resepter for?\n");
        for (Pasient element : pasientListe) {
            System.out.println(" - [" + teller + "] " + element.hentNavn() + " (" + element.hentFodselsnummer() + ")\n");
            teller++;
        }
        int valg = tastatur.nextInt();
        Pasient pasient = pasientListe.hent(valg);
        System.out.println("Valgt pasient: " + pasient.hentNavn() + " (" + pasient.hentFodselsnummer() + ")");

        // henter resepter til pasienten
        int teller_r = 0;
        System.out.println("Hvilken resept vil du bruke?\n");
        IndeksertListe<Resept> resepter = pasient.hentReseptListe();
        for (Resept element : resepter) {
            Legemiddel legemiddel = element.hentLegemiddel();
            System.out.println(" - [" + teller_r + "] " + legemiddel.hentNavn() + " (" + element.hentReit()+ " reit)\n");
            teller_r++;
        }
        int valg_r = tastatur.nextInt();
        Resept resept = resepter.hent(valg_r);
        // bruker resepten en gang
        Legemiddel legemiddel = resept.hentLegemiddel();
        resept.bruk();
        System.out.println("Brukte resept paa " + legemiddel.hentNavn() + ". Antall gjenvaerende reit: " + resept.hentReit());
        
    }

    // henter statistikk
    private static void hentStatistikk(Scanner tastatur) {
        System.out.println("\n----------  STATISTIKK  ----------\n");

        String utskrift = "";
        utskrift += "Velg statistikk:\n\n";
        utskrift += " - Totalt utskrevne resepter paa vanedannende legemidler (1)\n";
        utskrift += " - Totalt utskrevne resepter paa narkotiske legemidler (2)\n";
        utskrift += " - Leger som har skrevet ut narkotiske legemidler (3)\n";
        utskrift += " - Pasienter som har gyldig resept paa narkotiske legemidler (4)\n";
        System.out.println(utskrift); 
        int tallValg = tastatur.nextInt();

        switch (tallValg) {
            case 1:
                hentResepterMedVannedannendeLegemidler();
                break;
            
            case 2:
                hentResepterMedNarkotiskeLegemidler();
                break;
            
            case 3:
                hentLegerMedUtskrevneNarkotiskeResepter();
                break;

            case 4:
                hentPasienterMedResepterPaaNarkotiskLegemiddel();
                break;
        
            default:
                break;
        }
    }

    // henter totalt antall resepter med vannedannende legemidler
    private static void hentResepterMedVannedannendeLegemidler() {
        // itererer gjennom reseptliste og ser etter vanedannende legemidler
        int teller = 0;
        for (Resept resept : reseptListe) if (resept.hentLegemiddel() instanceof Vanedannende) teller++;
        if (teller > 0) System.out.println("Totalt antall resepter med vannedannende legemidler: " + teller);
        else System.out.println("Fant ingen data.");
    }

    // henter totalt antall resepter med narkotiske legemidler
    private static void hentResepterMedNarkotiskeLegemidler() {
        // itererer gjennom reseptliste og ser etter narkotiske legemidler
        int teller = 0;
        for (Resept resept : reseptListe) if (resept.hentLegemiddel() instanceof Narkotisk) teller++;
        if (teller > 0) System.out.println("Totalt antall resepter med narkotiske legemidler: " + teller);
        else System.out.println("Fant ingen data.");
    }

    // henter alle leger som har skrevet minst en resept med narkotisk legemiddel
    private static void hentLegerMedUtskrevneNarkotiskeResepter() {
        // itererer gjennom legeliste
        for (Lege lege : legeListe) {
            int teller = 0;
            IndeksertListe<Resept> resepter = lege.hentUtskrevneResepter();
            // itererer gjennom resepter for å finne resepter som er narkotiske
            for (Resept resept : resepter) if (resept.hentLegemiddel() instanceof Narkotisk) teller++;
            if (teller > 0) System.out.println("\nLege: " + lege.hentNavn() + " - Antall resepter med narkotisk legemiddel: " + teller);
            else System.out.println("Fant ingen data.");
        }
    }

    // henter alle pasienter som har minst en resept på et narkotisk legemiddel
    private static void hentPasienterMedResepterPaaNarkotiskLegemiddel() {
        // itererer gjennom pasientliste
        for (Pasient pasient : pasientListe) {
            int teller = 0;
            IndeksertListe<Resept> resepter = pasient.hentReseptListe();
            // itererer gjennom resepter for å finne resepter som er narkotiske
            for (Resept resept : resepter) if (resept.hentLegemiddel() instanceof Narkotisk) teller++;
            if (teller > 0) System.out.println("\nPasient: " + pasient.hentNavn() + " (" + pasient.hentFodselsnummer() + ") - Antall resepter med narkotisk legemiddel: " + teller);
            else System.out.println("Fant ingen data.");
        }
    }

    // skriver inn data til fil
    private static void leggInnData(Scanner tastatur) {
        System.out.println("\n----------  LASTER OPP DATA  ----------\n");
        // oppretter fil for opplastning av data
        opprettFil(tastatur);
    }

    // oppretter fil for opplastning av data
    private static void opprettFil(Scanner tastatur) {
        try {
            System.out.println("Skriv inn et filnavn for datafil: \n");
            String filNavn = tastatur.next();
            tastatur.nextLine();
            File fil = new File(filNavn);
            // sjekker om fil allerede eksisterer
            if (fil.createNewFile()) {
                System.out.println("Fil laget: " + fil.getName());
                skrivTilFil(filNavn);
            }
            else {
                System.out.println("Filen " + fil.getName() + " eksisterer allerede. Vil du overskride den (J/N)?\n");
                String valg = tastatur.nextLine();
                if (valg.contains("J")) skrivTilFil(filNavn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // skriver inn data til fil
    private static void skrivTilFil(String filNavn) {
        try {
            // åpner for skriving inn på fil
            FileWriter minSkriver = new FileWriter(filNavn);

            // skriver inn data om pasienter
            minSkriver.write("# Pasienter (navn, fnr)");
            for (Pasient pasient : pasientListe) {
                String navn = pasient.hentNavn();
                String foedselsnummer = pasient.hentFodselsnummer();
                minSkriver.write("\n" + navn + "," + foedselsnummer);
            }

            // skriver inn data om legemidler
            minSkriver.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for (Legemiddel legemiddel : legemiddelListe) {
                String navn = legemiddel.hentNavn();
                String type = legemiddel.hentType();
                int pris = legemiddel.hentPris();
                double virkestoff = legemiddel.hentVirkestoff();

                if (legemiddel instanceof Vanlig) minSkriver.write("\n" + navn + "," + type + "," + pris + "," + virkestoff); 
                else {
                    int styrke = legemiddel.hentStyrke();
                    minSkriver.write("\n" + navn + "," + type + "," + pris + "," + virkestoff + "," + styrke);
                }
            }

            // skriver inn data om leger
            minSkriver.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for (Lege lege : legeListe) {
                String navn = lege.hentNavn();
                
                if (lege instanceof Spesialist) {
                    Spesialist spesialist = (Spesialist) lege;
                    String kontrollID = spesialist.hentKontrollID();
                    minSkriver.write("\n" + navn + "," + kontrollID);
                } else minSkriver.write("\n" + navn + ",0");
            }

            // skriver inn data om resepter
            minSkriver.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for (Resept resept : reseptListe) {
                Legemiddel legemiddel = resept.hentLegemiddel();
                int legemiddelNummer = legemiddel.hentId();
                Lege lege = resept.hentLege();
                String legeNavn = lege.hentNavn();
                Pasient pasient = resept.hentPasient();
                int pasientID = pasient.hentID();
                String type = resept.hentType();

                if (resept instanceof MiliterResept) minSkriver.write("\n" + legemiddelNummer + "," + legeNavn + "," + pasientID + "," + type);
                else {
                    int reit = resept.hentReit();
                    minSkriver.write("\n" + legemiddelNummer + "," + legeNavn + "," + pasientID + "," + type + "," + reit);
                }
            }
            
            // lukker skriving
            minSkriver.close();
            System.out.println("\nAll data er skrevet inn på din datafil.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}