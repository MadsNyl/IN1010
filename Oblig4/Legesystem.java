import java.io.*;
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
                                                lege.skrivHvitResept(legemiddel, pasient, reit);
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
                                                lege.skrivBlaaResept(legemiddel, pasient, reit);
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
                                                lege.skrivPResept(legemiddel, pasient, reit);
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
                                                lege.skrivMilResept(legemiddel, pasient);
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
        }

    }
    
    private static void kjorProgram() throws UlovligUtskrift { 
        // starter opp program
        System.out.println("----------  LEGESYSTEM  ----------\n");
        System.out.println("Skriv inn et filnavn for opplastning: ");
        Scanner tastatur = new Scanner(System.in);
        String filnavn = tastatur.nextLine();
        lesFil(filnavn);
        kommandoMeny();
        kommandoValg(tastatur);
        tastatur.close();
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
        utskrift += "Hent fildata (5): \n";
        utskrift += "Avslutt programvare (0): \n";
        System.out.println(utskrift);
    }

    // går gjennom menyvalg
    private static void kommandoValg(Scanner tastatur) {
        int valgTall = tastatur.nextInt();
        switch (valgTall) {
            case 0:
                System.out.println("Avslutter programvaren.");
                break;

            case 1:
                skrivUtOversikt();
                break;

            case 2:
                opprettElement(tastatur);
                break;
            
            case 3:
                System.out.println(3);
                break;

            case 4:
                System.out.println(4);
                break;
            
            case 5:
                System.out.println(5);
                break;
        
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
    private static void opprettElement(Scanner tastatur) {
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
    private static void opprettResept(Scanner tastatur) {
        
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
                break;

            case 2:
                System.out.println("\nHva er styrken?");
                int styrke = tastatur.nextInt();
                Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                legemiddelListe.leggTil(vanedannende);
                break;
            
            case 3:
                System.out.println("\nHva er styrken?");
                int styrke_n = tastatur.nextInt();
                Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke_n);
                legemiddelListe.leggTil(narkotisk);
                break;
        
            default:
                break;
        }
    }

}


