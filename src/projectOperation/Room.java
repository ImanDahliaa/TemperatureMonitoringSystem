//Room
package projectOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Room extends JFrame {

    private JLabel statusLabel;
    private JLabel emailLabel;
    private JLabel smsLabel;
    private JButton startButton;
    private Timer timer;

    public Room() {
        // Window setup
        setTitle("Temperature Monitoring System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Dark theme background
        getContentPane().setBackground(Color.DARK_GRAY);

        // Components
        statusLabel = new JLabel("Sensor ID: S001");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        emailLabel = new JLabel("📧 Email Alert: --");
        emailLabel.setForeground(Color.ORANGE);
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);

        smsLabel = new JLabel("📱 SMS Alert: --");
        smsLabel.setForeground(Color.CYAN);
        smsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        startButton = new JButton("Start Monitoring");

        // Layout
        setLayout(new GridLayout(4, 1));
        add(statusLabel);
        add(emailLabel);
        add(smsLabel);
        add(startButton);

        // Timer: runs every 5 seconds
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = generateTemperature();

                if (temp >= 30.0) {
                    statusLabel.setText(
                        "Sensor ID: S001   TEMP: " + String.format("%.2f", temp) + "°C  STATUS: HIGH TEMP"
                    );
                    sendEmailAlert(temp);
                    sendSmsAlert(temp);
                } else {
                    statusLabel.setText(
                        "Sensor ID: S001   TEMP: " + String.format("%.2f", temp) + "°C  STATUS: SAFE"
                    );
                    emailLabel.setText("📧 Email Alert: Temperature is SAFE (" 
                        + String.format("%.2f", temp) + "°C)");
                    smsLabel.setText("📱 SMS Alert: Temperature is SAFE (" 
                        + String.format("%.2f", temp) + "°C)");
                }
            }
        });

        // Button starts the loop
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
                startButton.setEnabled(false); // disable button once loop starts
            }
        });
    }

    // Generate random temperature (20–60°C)
    private double generateTemperature() {
        return 20.0 + Math.random() * 40.0;
    }

    // Show email alert in GUI
    private void sendEmailAlert(double temp) {
        emailLabel.setText("📧 Email Alert: HIGH TEMP! Temp: " 
            + String.format("%.2f", temp) + "°C");
    }

    // Show SMS alert in GUI
    private void sendSmsAlert(double temp) {
        smsLabel.setText("📱 SMS Alert: HIGH TEMP! Temp: " 
            + String.format("%.2f", temp) + "°C");
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Room().setVisible(true);
        });
    }
}
