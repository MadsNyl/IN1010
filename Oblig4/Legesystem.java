import java.io.*;
import java.util.Scanner;

public class Legesystem {
    
    // oppretter indekserte lister for de ulike objektene
    IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    IndeksertListe<Lege> legeListe = new IndeksertListe<>();
    IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public void lesFil(String filnavn) {
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
                    for (int i = 0; i < legeListe.stoerrelse(); i++) {
                        Lege lege = legeListe.hent(i);
                        legeNavn = lege.hentNavn();
                    }

                    // hvis legenavnet i resepten er lik en av legene i listen, så er det match
                    if (legeNavn != null & legeNavn.equals(legeNavn)) {
                        // legen oppretter en resept med informasjon fra reseptlinjen

                    }
                }
                

            }

            leser.close();
        } catch (FileNotFoundException e) {
            System.out.println("En feil oppsto.");
            e.printStackTrace();
        }

    }
}
