import javax.swing.*;
import java.awt.*;

public class SemaphoreGui extends Thread {

    enum SemaphoreColor {GREEN, RED;}

    private JPanel semaphore;
    private JLabel stateLabel;

    public SemaphoreGui() {

        JFrame jFrameSemaphore = new JFrame("Semaphore");
        jFrameSemaphore.setContentPane(semaphore);
        jFrameSemaphore.setPreferredSize(new Dimension(300, 100));
        jFrameSemaphore.setDefaultCloseOperation(jFrameSemaphore.EXIT_ON_CLOSE);
        jFrameSemaphore.pack();
        jFrameSemaphore.setVisible(true);
        jFrameSemaphore.setLocation(0, 0);
    }

    public void changeSemaphoreColor(SemaphoreColor semaphoreColor) {

        if (semaphoreColor.equals(SemaphoreColor.RED)) {
            stateLabel.setText("Red");
            semaphore.setBackground(new Color(255, 0, 0));
        } else {
            stateLabel.setText("Green");
            semaphore.setBackground(new Color(35, 65, 34));
        }
    }
}
