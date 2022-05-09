import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    // konstanter
    private final int BRETTHOYDE = 600;
    private final int BRETTBREDDE = 432;
    private final int GRID = 12;
    private final int ANTALLSKATTER = 10;
    private final int KONTROLLPANELHOYDE = 100;
    private final int RUTENETTHOYDE = 432;

    private Kontroller kontroller;
    private JFrame vindu;
    private JPanel hovedpanel, panel, rutenett, kontroll, info;
    private JButton avslutt, opp, hoyre, ned, venstre;
    private JLabel slangelengde;
    private JLabel ruter[][] = new JLabel[GRID][GRID];

    // setter opp gui
    public GUI(Kontroller kontroller) {
        this.kontroller = kontroller;

        // fallback paa layout for gui
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) { System.exit(9); }

        /* 
            EVENTLISTNERE 
        */

        // avslutt spill
        class Avslutt implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.avsluttSpill();
            }
        }

        // beveg slange oppover
        class Opp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.oppdaterBevegelse("opp");
            }
        }

        // beveg slange til hoyre
        class Hoyre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.oppdaterBevegelse("hoyre");
            }
        }

        // beveg slange nedover
        class Ned implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.oppdaterBevegelse("ned");
            }
        }

        // beveg slange til venstre
        class Venstre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.oppdaterBevegelse("venstre");
            }
        }

        /*
            SETTER OPP SPILLVINDU
        */

        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setter opp hovedpanel
        hovedpanel = new JPanel();
        hovedpanel.setLayout(new BorderLayout());
        vindu.add(hovedpanel);

        // setter opp ovre panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(BRETTBREDDE, KONTROLLPANELHOYDE));
        hovedpanel.add(panel, BorderLayout.NORTH);

        // setter opp infopanel
        info = new JPanel();
        info.setLayout(new GridBagLayout());
        info.setPreferredSize(new Dimension(BRETTBREDDE / 4, KONTROLLPANELHOYDE));
        info.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        slangelengde = new JLabel();
        info.add(slangelengde);
        panel.add(info, BorderLayout.WEST);

        // setter opp kontrollpanel
        kontroll = new JPanel();
        kontroll.setLayout(new BorderLayout());
        kontroll.setPreferredSize(new Dimension(BRETTBREDDE / 2, KONTROLLPANELHOYDE));
        panel.add(kontroll, BorderLayout.CENTER);

        // setter opp styring
        opp = new JButton("Opp");
        opp.addActionListener(new Opp());

        hoyre = new JButton("Hoyre");
        hoyre.addActionListener(new Hoyre());

        ned = new JButton("Ned");
        ned.addActionListener(new Ned());

        venstre = new JButton("Venstre");
        venstre.addActionListener(new Venstre());

        kontroll.add(opp, BorderLayout.NORTH);
        kontroll.add(hoyre, BorderLayout.EAST);
        kontroll.add(ned, BorderLayout.SOUTH);
        kontroll.add(venstre, BorderLayout.WEST);

        // setter opp avslutningspanel
        avslutt = new JButton("AVSLUTT");
        avslutt.setLayout(new BorderLayout());
        avslutt.setPreferredSize(new Dimension(BRETTBREDDE / 4, 100));
        avslutt.addActionListener(new Avslutt());
        panel.add(avslutt, BorderLayout.EAST);

        // setter opp rutenett
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(GRID, GRID));
        rutenett.setPreferredSize(new Dimension(BRETTBREDDE, RUTENETTHOYDE));
        rutenett.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        hovedpanel.add(rutenett, BorderLayout.SOUTH);

        for (int rad = 0; rad < GRID; rad++) {
            for (int kol = 0; kol < GRID; kol++) {
                JLabel rute = new JLabel("", SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                ruter[rad][kol] = rute;
                rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

                rutenett.add(rute);
            }
        }
        
        
        vindu.setSize(BRETTBREDDE, BRETTHOYDE);
        vindu.setVisible(true);

    }

    // tegner startbrett
    public void tegnStartBrett() {
        for (int rad = 0; rad < GRID; rad++) {
            for (int kol = 0; kol < GRID; kol++) {
                ruter[rad][kol].setText("");
            }
        }



        ruter[6][6].setText("O");
        kontroller.forlengSlange(new Slange(6, 6, true));

        // tegn inn skatter
        for (int i = 0; i < ANTALLSKATTER; i++) {
            int skatt_rad = 0;
            int skatt_kolonne = 0;
            while ((skatt_rad != 6 && skatt_kolonne != 6) || ruter[skatt_rad][skatt_kolonne].getText().equals("$")) {
                skatt_rad = Skatt.trekk(0, GRID - 1);
                skatt_kolonne = Skatt.trekk(0, GRID - 1);
                if ((skatt_rad != 6 && skatt_kolonne != 6) || ruter[skatt_rad][skatt_kolonne].getText().equals("$")) break;
            }
            ruter[skatt_rad][skatt_kolonne].setText("$");
            Skatt ny = new Skatt(skatt_rad, skatt_kolonne);
            kontroller.leggTilSkatt(i, ny);
        }
        
    }

    // tegner brettet om igjen
    public void tegnBrettOmIgjen() {
        Koe<Slange> slange = kontroller.hentSlange();
        Skatt[] skatter = kontroller.hentSkatter();

        for (int rad = 0; rad < GRID; rad++) {
            for (int kol = 0; kol < GRID; kol++) {
                ruter[rad][kol].setText("");
            }
        }
        
        for (int i = 0; i < skatter.length; i++) {
            if (skatter[i] != null) ruter[skatter[i].hentRad()][skatter[i].hentKolonne()].setText("$");
        }

        for (Slange del : slange) {
            int slange_rad = del.hentRad();
            int slange_kolonne = del.hentKolonne();
            if (del.erHode()) ruter[slange_rad][slange_kolonne].setText("O");
            else ruter[slange_rad][slange_kolonne].setText("x");
        }
        
        slangelengde.setText(" " + kontroller.hentHalelengde() + " ");
        // System.out.println(slange);
    }
}
