import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindowGui {

    private JButton ok;
    private JPanel inputPanel;
    private JTextField textField;

    public InputWindowGui() {

        JFrame inputFrame = new JFrame("Enter Code");
        inputFrame.setContentPane(this.inputPanel);
        inputFrame.setPreferredSize(new Dimension(300, 100));
        inputFrame.setDefaultCloseOperation(inputFrame.EXIT_ON_CLOSE);
        inputFrame.pack();
        inputFrame.setVisible(true);
        inputFrame.setLocation(500,250);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 CarParkingSystem.getSystem().enterCode(textField.getText());
                 inputFrame.dispose();
            }
        });
    }
}
