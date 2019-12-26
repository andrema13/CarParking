import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class GateGui {

    enum GateState {OPEN, CLOSED}

    private JPanel gate;
    private JLabel stateLabel;
    private GateState gateState;
    private Semaphore SEMAPHORE = new Semaphore(1);

    public GateGui() {
        JFrame jFrameGate = new JFrame("Gate");
        jFrameGate.setContentPane(gate);
        jFrameGate.setPreferredSize(new Dimension(300, 100));
        jFrameGate.setDefaultCloseOperation(jFrameGate.EXIT_ON_CLOSE);
        jFrameGate.pack();
        jFrameGate.setVisible(true);
        jFrameGate.setLocation(0, 100);

        gateState = GateState.CLOSED;
    }

    public void changeGateState() {
        new Thread() {
            public void run() {
                if (gateState.equals(GateState.OPEN)) {
                    closeGate();
                } else {
                    openGate();
                }
            }
        }.start();
    }

    public void closeGate() {
        try {
            Log.print("Gate is closing...");
            Thread.sleep(1500);
            gateState = GateState.CLOSED;
            stateLabel.setText("CLOSED");
            Log.print("Gate is CLOSED");
            SEMAPHORE.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openGate() {
        try {
            SEMAPHORE.acquire();
            Log.print("Gate is opening...");
            Thread.sleep(1500);
            gateState = GateState.OPEN;
            stateLabel.setText("OPENED");
            Log.print("Gate is OPENED");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
