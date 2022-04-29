import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class Main extends JFrame {

    private final int rute_storrelse = 32;
    private final int dimensjon = 640;

    public Main() {
        super("Snake");
        JPanel hoved_panel = new JPanel();
        hoved_panel.setBackground(Color.black);
        setSize(dimensjon + 20, dimensjon + 20);

        // Avslutter vindu naar man trykker paa exit-knappen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int i = rute_storrelse; i <= dimensjon; i += rute_storrelse) {
            Line2D linje = new Line2D.Float(rute_storrelse, i, dimensjon, i);
            g2.draw(linje);
        }

        for (int i = rute_storrelse; i <= dimensjon; i += rute_storrelse) {
            Line2D linje = new Line2D.Float(i, rute_storrelse, i, dimensjon);
            g2.draw(linje);
        }
        // g2.draw(linje);
    }

    public static void main(String[] args) {
        // setter opp fallback for vindu-display
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.exit(1);
        }

        Main main = new Main();
        main.setVisible(true);
    }
}