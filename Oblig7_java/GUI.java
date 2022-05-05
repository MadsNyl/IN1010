import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class GUI {
    
    private JFrame vindu;
    private Kontroller kontroller;
    private JPanel hovedpanel, panel, rutenett, kontroll, info; 
    private JButton avslutt, opp, hoyre, ned, venstre;
    private JLabel slangelengde;
    private int halelengde = 1;
    private JLabel ruter[][] = new JLabel[12][12];

    // konstanter
    private final int BRETTHOYDE = 600;
    private final int BRETTBREDDE = 432;
    private final int RUTESTORRELSE = 36;
    private final int GRID = 12;
    private final int ANTALLSKATTER = 10;

    // setter opp GUI
    public GUI(Kontroller kontroller) {
        this.kontroller = kontroller;

        // fallback paa layout for gui
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) { System.exit(9); }

        // sett opp eventlistnere

        // event listener for aa avslutte spillet
        class Avslutt implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.avsluttSpill();
            }
        }

        // event listener for aa bevege slangen oppover
        class Opp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.bevegOpp();
            }
        }
        // event listener for aa bevege slangen til hoyre
        class Hoyre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.bevegHoyre();
            }
        }
        // event listener for aa bevege slangen nedover
        class Ned implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.bevegNed();
            }
        }
        // event listener for aa bevege slangen til venstre
        class Venstre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.bevegVenstre();
            }
        }

        // setter opp vindu
        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setter opp hovedpanel
        hovedpanel = new JPanel();
        hovedpanel.setLayout(new BorderLayout());
        vindu.add(hovedpanel);

        // setter opp ovre panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(BRETTBREDDE, 100));
        hovedpanel.add(panel, BorderLayout.NORTH);


        // setter opp panel for info av slangelengde
        info = new JPanel();
        info.setLayout(new GridBagLayout());
        info.setPreferredSize(new Dimension(BRETTBREDDE / 4, 100));
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        slangelengde = new JLabel(" " + halelengde + " ");
        info.add(slangelengde);

        panel.add(info, BorderLayout.WEST);

        // setter opp kontrollpanel
        kontroll = new JPanel();
        kontroll.setLayout(new BorderLayout());
        kontroll.setPreferredSize(new Dimension(BRETTBREDDE / 2, 100));
        panel.add(kontroll, BorderLayout.CENTER);
        // setter opp styringsknapper
        opp = new JButton();
        opp.setText("Opp");
        opp.addActionListener(new Opp());

        hoyre = new JButton();
        hoyre.setText("Hoyre");
        hoyre.addActionListener(new Hoyre());

        ned = new JButton();
        ned.setText("Ned");
        ned.addActionListener(new Ned());

        venstre = new JButton();
        venstre.setText("Venstre");
        venstre.addActionListener(new Venstre());

        kontroll.add(opp, BorderLayout.NORTH);
        kontroll.add(hoyre, BorderLayout.EAST);
        kontroll.add(ned, BorderLayout.SOUTH);
        kontroll.add(venstre, BorderLayout.WEST);
        

        // setter opp panel for aa avslutte program
        avslutt = new JButton();
        avslutt.setLayout(new BorderLayout());
        avslutt.setPreferredSize(new Dimension(BRETTBREDDE / 4, 100));
        avslutt.setText("AVSLUTT");
        
        avslutt.addActionListener(new Avslutt());
        panel.add(avslutt, BorderLayout.EAST);



        // setter opp panel for brett
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(GRID, GRID));
        rutenett.setPreferredSize(new Dimension(BRETTBREDDE, 432));
        rutenett.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        hovedpanel.add(rutenett, BorderLayout.SOUTH);

        for (int rad = 0; rad < GRID; rad++) {
            for (int kol = 0; kol < GRID; kol++) {
                JLabel rute = new JLabel(" ");
                Border kanter = BorderFactory.createLineBorder(Color.BLACK, 1);
                rute.setBorder(kanter);
                ruter[rad][kol] = rute;
                rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

                rutenett.add(rute);
            }
        }


        // pakker sammen vindu og gjør synlig for bruker
        vindu.setSize(BRETTBREDDE, BRETTHOYDE);
        vindu.setVisible(true);
    }

    // oek halelengde
    public void oekHalelengde() { halelengde++; }

    // tegn ny slangelengde
    public void tegnSlangelengde() { slangelengde.setText(" " + halelengde + " "); }

    // tegn slange
    public void tegnSlange(int rad, int kol) {
        try { 
            ruter[rad][kol].setText("x"); 
        } catch (Exception e) { 
            kontroller.kollisjon();
        }
    }

    // visk ut slange
    public void viskUtSlange(int rad, int kol) { ruter[rad][kol].setText(" "); }

    // tegn skatt
    public Skatt tegnSkatt() { 
        int rad = 0;
        int kol = 0;
        while ((rad != 6 && kol != 6) || ruter[rad][kol].getText().equals("$")) {
            rad = Skatt.trekk(0, GRID - 1);
            kol = Skatt.trekk(0, GRID - 1); 

            if ((rad != 6 && kol != 6) || ruter[rad][kol].getText().equals("$")) break;
        }

        ruter[rad][kol].setText("$");
        return new Skatt(rad, kol);
    }

    // tegn alle skatter
    public Skatt[] tegnSkatter() {
        Skatt[] skatter = new Skatt[ANTALLSKATTER];
        int rad = 0;
        int kol = 0;
        for (int i = 0; i < ANTALLSKATTER; i++) {
            while ((rad != 6 && kol != 6) || ruter[rad][kol].getText().equals("$")) {
                rad = Skatt.trekk(0, GRID - 1);
                kol = Skatt.trekk(0, GRID - 1); 

                if ((rad != 6 && kol != 6) || ruter[rad][kol].getText().equals("$")) break;
            }   

            ruter[rad][kol].setText("$");
            skatter[i] = new Skatt(rad, kol);   
        }  
        return skatter;
    }
}
