import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PoolCalculatorView extends JFrame {

    private Color lightBlue = new Color(160,226,255);

    private JLabel volumeLabel = new JLabel("Pool Volume (gal)");
    private JTextField volumeTextField = new JTextField(5);
    private JLabel freeChlorineLabel = new JLabel("Free Chlorine (ppm)");
    private JTextField freeChlorineTextField = new JTextField(5);
    private JLabel totalChlorineLabel = new JLabel("Total Chlorine (ppm)");
    private JTextField totalChlorineTextField = new JTextField(5);
    private JLabel phLabel = new JLabel("pH");
    private JTextField phTextField = new JTextField(5);
    private JLabel alkalinityLabel = new JLabel("Alkalinity (ppm)");
    private JTextField alkalinityTextField = new JTextField(5);
    private JLabel calciumHardnessLabel = new JLabel("Calcium Hardness (ppm)");
    private JTextField calciumHardnessTextField = new JTextField(5);
    private JLabel cyanuricAcidLabel = new JLabel("Cyanuric Acid (ppm)");
    private JTextField cyanuricAcidTextField = new JTextField(5);
    private JButton generateButton = new JButton("Generate report");


    PoolCalculatorView() {
        JPanel backgroundPanel = new JPanel();
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        this.setName("Pool Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        formPanel.add(volumeLabel);
        formPanel.add(volumeTextField);
        formPanel.add(freeChlorineLabel);
        formPanel.add(freeChlorineTextField);
        formPanel.add(totalChlorineLabel);
        formPanel.add(totalChlorineTextField);
        formPanel.add(phLabel);
        formPanel.add(phTextField);
        formPanel.add(alkalinityLabel);
        formPanel.add(alkalinityTextField);
        formPanel.add(calciumHardnessLabel);
        formPanel.add(calciumHardnessTextField);
        formPanel.add(cyanuricAcidLabel);
        formPanel.add(cyanuricAcidTextField);
        formPanel.add(generateButton);


        formPanel.setBackground(lightBlue);
        backgroundPanel.setBackground(lightBlue);

        backgroundPanel.add(BorderLayout.CENTER, formPanel);
        this.add(backgroundPanel);
    }

    public Float getVolume() {
        return Float.parseFloat(volumeTextField.getText());
    }

    public Float getFreeChlorine() {
        return Float.parseFloat(freeChlorineTextField.getText());
    }

    public Float getTotalChlorine() {
        return Float.parseFloat(totalChlorineTextField.getText());
    }

    public Float getPh() {
        return Float.parseFloat(phTextField.getText());
    }

    public Float getAlkalinity() {
        return Float.parseFloat(alkalinityTextField.getText());
    }

    public Float getCalciumHardness() {
        return Float.parseFloat(calciumHardnessTextField.getText());
    }

    public Float getCyanuricAcid() {
        return Float.parseFloat(cyanuricAcidTextField.getText());
    }

    void addGenerateButtonListener(ActionListener listenForGenerateButton) {
        generateButton.addActionListener(listenForGenerateButton);
    }

    void displayReport(String[] report) {
        // display the chemicals needed on the screen
        JFrame resultsFrame = new JFrame("Results");

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(lightBlue);



        resultsPanel.add(new JLabel(report[0]));
        resultsPanel.add(new JLabel(report[1]));
        resultsPanel.add(new JLabel(report[2]));
        resultsPanel.add(new JLabel(report[3]));
        resultsPanel.add(new JLabel(report[4]));

        resultsFrame.getContentPane().add(resultsPanel);
        resultsFrame.setSize(600, 150);
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setVisible(true);
    }

    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
