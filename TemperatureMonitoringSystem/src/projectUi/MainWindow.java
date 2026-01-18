package projectUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import projectOperation.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class MainWindow extends JFrame {

    private JButton btnCheck;
    private JLabel statusLabel, sensorLabel;

    private TemperatureSensor t1;

    public MainWindow() {
        // Apply FlatLaf Dark Theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            // Global UI tweaks for readability
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.background", new Color(60, 60, 60));
            UIManager.put("Panel.background", new Color(30, 30, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }

        t1 = new TemperatureSensor("DHT 22 Sensor");

        setTitle("Temperature Monitoring System");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(30, 30, 30)); // Frame background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        sensorLabel = new JLabel("DHT 22 Sensor " + t1.getSensorid(), SwingConstants.CENTER);
        sensorLabel.setForeground(Color.WHITE);
        add(sensorLabel, gbc);

        gbc.gridy++;
        btnCheck = new JButton("Check Temperature");
        btnCheck.setBackground(new Color(60, 60, 60));
        btnCheck.setForeground(Color.WHITE);
        add(btnCheck, gbc);

        gbc.gridy++;
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel, gbc);

        // Button action: generate random temperature + show both alerts
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = 20 + Math.random() * 40; // random between 20°C and 60°C
                t1.setTemperature(temp);

                AlertSystem emailAlert = new EmailAlert();
                AlertSystem smsAlert = new SmsAlert();

                // Format temperature with 2 decimal places
                String formattedTemp = String.format("%.2f", temp);

                String result = "<html>" 
                        + emailAlert.displayAlert(t1).replace(String.valueOf(temp), formattedTemp) + "<br>"
                        + smsAlert.displayAlert(t1).replace(String.valueOf(temp), formattedTemp) + "</html>";

                statusLabel.setText(result);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
