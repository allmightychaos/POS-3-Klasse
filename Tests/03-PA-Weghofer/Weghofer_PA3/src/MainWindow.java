import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainWindow extends JFrame {
    private JPanel main;
    private JTextArea aufgabe2textArea;
    private JPanel aufgabe3Panel;
    private JButton aufgabe2Button;

    // Aufgabe 3
    private JTable table;
    private DefaultTableModel tableModel;

    public MainWindow() throws HeadlessException {
        setTitle("3. praktische Arbeit");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(main);

        aufgabe3Panel.setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Buchstabe");
        // tableModel.addColumn("Anzahl"); - Original (BEFORE)
        tableModel.addColumn("Kleinbuchstaben"); // - After
        tableModel.addColumn("Großbuchstaben"); // - After
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        aufgabe3Panel.add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Datei");
        menu.setMnemonic(KeyEvent.VK_D);

        JMenuItem load = new JMenuItem("Laden");
        load.setMnemonic(KeyEvent.VK_L);
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    java.nio.file.Path path = fileChooser.getSelectedFile().toPath();
                    String content = new String(java.nio.file.Files.readAllBytes(path));
                    aufgabe2textArea.setText(content);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Fehler beim Laden der Datei", "Fehler", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        JMenuItem save = new JMenuItem("Speichern");
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    JAXBContext context = JAXBContext.newInstance(TextAnalysis.class);
                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    TextAnalysis analysis = new TextAnalysis();

                    analysis.addText(aufgabe2textArea.getText());

                    Map<Character, Integer> letters = calculateLetters(aufgabe2textArea.getText());
                    for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
                        analysis.addLetterCount(new LetterCount(entry.getKey(), entry.getValue()));
                    }

                    marshaller.marshal(analysis, fileChooser.getSelectedFile());
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.add(load);
        menu.add(save);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        aufgabe2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evText();
            }
        });
    }

    private Map<Character, Integer> calculateLetters(String text) {
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : text.replaceAll("[^a-zA-Z]","").toCharArray()) {
            char key = Character.toLowerCase(c);
            letters.put(key, letters.getOrDefault(key, 0) + 1);
        }
        return letters;
    }

    // BEFORE

    /* private void evText() {
        String text = aufgabe2textArea.getText();
        text = text.replaceAll("[^a-zA-Z]","");
        Map<String, Integer> letters = new HashMap<>();

        for (char c : text.toCharArray()) {
            String key = String.valueOf(c);
            letters.put(key, letters.getOrDefault(key, 0) + 1);

        }
        updateTable(letters);
    }

    private void updateTable(Map<String, Integer> letters) {
        tableModel.setRowCount(0);
        letters.forEach((letter, count) -> {
            tableModel.addRow(new Object[]{letter, count});
        });
    }*/

    // AFTER
    private void evText() {
        String text = aufgabe2textArea.getText();
        text = text.replaceAll("[^a-zA-Z]","");
        Map<String, int[]> letters = new HashMap<>();

        for (char c : text.toCharArray()) {
            char key = Character.toLowerCase(c);
            int index = Character.isLowerCase(c) ? 0 : 1;

            letters.computeIfAbsent(String.valueOf(key), k -> new int[2])[index]++;

        }
        updateTable(letters);
    }

    private void updateTable(Map<String, int[]> letters) {
        tableModel.setRowCount(0);

        letters.forEach((character, counts) -> {
            tableModel.addRow(new Object[]{character, counts[0], counts[1]});
        });
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}
