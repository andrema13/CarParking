import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingPanelGui implements ActionListener {

    private JPanel parkingPanel;
    private JButton E;
    private JButton S;
    private JButton AF;
    private JButton C;
    private JButton R;
    private JButton P;
    private JFrame parkedCarJFrame;


    public ParkingPanelGui() {

        parkedCarJFrame = new JFrame("Parking Panel");
        parkedCarJFrame.setContentPane(parkingPanel);
        parkedCarJFrame.setDefaultCloseOperation(parkedCarJFrame.EXIT_ON_CLOSE);
        parkedCarJFrame.setPreferredSize(new Dimension(300, 150));
        parkedCarJFrame.pack();
        parkedCarJFrame.setVisible(true);
        parkedCarJFrame.setLocation(300,100);

        C.addActionListener(this);
        E.addActionListener(this);
        S.addActionListener(this);
        AF.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Card Ticket":
                new InputWindowGui();
                break;
            case "Entry":
                CarParkingSystem.getSystem().carEntry();
                break;
            case "Exit":
                CarParkingSystem.getSystem().carExit();
                break;
            case "Open/Close Gate":
                CarParkingSystem.getSystem().changeGatePosition();
                break;
            default:
                break;
        }
    }

}
