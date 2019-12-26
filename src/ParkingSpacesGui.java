import javax.swing.*;
import java.awt.*;

public class ParkingSpacesGui extends Thread {

    private JPanel parkingSpacesPanel;
    private JTextPane[] jTextPanes;

    public ParkingSpacesGui(int permits) {
        JFrame parkingSpacesJFrame = new JFrame("Parking Spaces");
        parkingSpacesJFrame.setContentPane(parkingSpacesPanel);
        parkingSpacesJFrame.setPreferredSize(new Dimension(60 * permits, 100));
        parkingSpacesJFrame.setDefaultCloseOperation(parkingSpacesJFrame.EXIT_ON_CLOSE);
        parkingSpacesJFrame.pack();
        parkingSpacesJFrame.setVisible(true);
        parkingSpacesJFrame.setLocation(300, 0);
        jTextPanes = new JTextPane[permits];

        //create the parking places in gui
        for (int i = 0; i < jTextPanes.length; i++) {
            jTextPanes[i] = new JTextPane();
            jTextPanes[i].setPreferredSize(new Dimension(50, 50));
            jTextPanes[i].setBackground(Color.GREEN);
            jTextPanes[i].setText(String.valueOf(i + 1));
            jTextPanes[i].setForeground(Color.WHITE);
            jTextPanes[i].setVisible(true);
            jTextPanes[i].setEditable(false);
            parkingSpacesPanel.add(jTextPanes[i]);
        }
    }

    public JTextPane[] getTextPanes() {
        return jTextPanes;
    }
}
