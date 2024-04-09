import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MainWindow {
    private JPanel main;
    private JTextField aufgabe4Breite;
    private JTextField aufgabe4Hoehe;
    private JButton aufgabe4Button;
    private JComboBox aufgabe4Einheit;
    private JLabel umfangLabel;
    private JPanel aufgabe3Panel;

    private ArrayList<JButton> buttons = new ArrayList<>();
    private JButton ersterKnopfGedrückt;
    private JButton zweiterKnopfGedrückt;

    public MainWindow() {
        aufgabe3Panel.setLayout(new GridLayout(2, 4));
        ArrayList<ImageIcon> icons = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            icons.add(new ImageIcon("./images/Chip_" + i + ".png"));
        }
        Collections.shuffle(icons); // ...zufälligen Anordnung

        for (ImageIcon icon : icons) {
            JButton button = new JButton(icon);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButton(button);
                }
            });
            aufgabe3Panel.add(button);
            buttons.add(button);
        }

        aufgabe4Button.addActionListener(e -> umfangBerechnen());
        // IDE hat mit lambda vorgeschlagen
    }

    private void handleButton(JButton button) {
        if (ersterKnopfGedrückt == null) {
            ersterKnopfGedrückt = button;
        } else {
            zweiterKnopfGedrückt = button;
            bilderTausch();
        }
    }

    private void bilderTausch() {
        Icon icon = ersterKnopfGedrückt.getIcon();
        ersterKnopfGedrückt.setIcon(zweiterKnopfGedrückt.getIcon());
        zweiterKnopfGedrückt.setIcon(icon);
        ersterKnopfGedrückt = null;
        zweiterKnopfGedrückt = null;
    }
    private void umfangBerechnen() {
        try {
            int breite = Integer.parseInt(aufgabe4Breite.getText());
            int hoehe = Integer.parseInt(aufgabe4Hoehe.getText());
            String einheit = (String) aufgabe4Einheit.getSelectedItem();
            int umfang = 2 * breite + 2 * hoehe;
            umfangLabel.setText(umfang + " " + einheit);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(main, "Bitte nur Zahlen eingeben!");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}