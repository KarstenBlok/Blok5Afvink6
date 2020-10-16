import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PercentageBerekenen extends JFrame implements ActionListener {
    private JButton InfoButton, BladerButton;
    private JTextField textfield1;
    private JTextArea textarea1;
    private JLabel label1, label2;

    public static void main(String[] args) {
        PercentageBerekenen frame = new PercentageBerekenen();
        frame.setSize(600, 400);
        frame.createGUI();
        frame.setVisible(true);
    }

    void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        InfoButton = new JButton("Analyseer");
        textfield1 = new JTextField("bestandsnaam.txt");
        //BladerButton = new JButton("Blader");
        label1 = new JLabel("Bestand");
        textarea1 = new JTextArea();
        textarea1.setPreferredSize(new Dimension(550, 300));
        window.add(label1);
        window.add(textfield1);
        window.add(InfoButton);
        //window.add(BladerButton);
        window.add(textarea1);
        InfoButton.addActionListener(this);
    }

    private static String ReadFiles(String x) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(x), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private static void checkaminoacids(String v) {
        String[] niets = {"B", "C", "F", "J", "O", "U", "X", "Z"};
        for (String s : niets) {
            if (v.equals(s)) {
                System.out.println("1 van de letters codeert niet voor een aminozuur");
                System.exit(0);};
            }
    }



    private static float Percentages(String y) {
        String[] Polair = {"R", "N", "D", "Q", "E", "G", "H", "K", "S", "T", "Y"};
        int countpolair = 0;
        int countapolair = 0;
        for (int z = 0; z < y.length(); z++) {
            char w = y.charAt(z);
            String v = String.valueOf(w);
            checkaminoacids(v);
            for (String s : Polair)
                if (v.equals(s)) {
                    countpolair++;
                }
        }return countpolair;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String x = textfield1.getText();
        String y = ReadFiles(x);
        float countpolair = Percentages(y);
        float countapolair = y.length() - countpolair;
        float percentagepolair = countpolair/y.length()*100;
        float percentageapolair = countapolair/y.length()*100;
        textarea1.setText("Alle aminozuren kloppen\nHet totaal aantal aminozuren is:" + y.length() +"\n"
                + percentageapolair+" is het" +
                "percentage apolaire aminozuren\n" + percentagepolair+ " is het percentage polaire aminozuren");
    }
}

// polair: R, N, D, Q, E, G, H, K, S, T en Y
// apolair: A, F, I, L, M, P, W en V