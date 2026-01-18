package projectOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Room extends JFrame {

    private JLabel statusLabel;
    private JLabel emailLabel;
    private JLabel smsLabel;
    private JButton startButton;
    private JButton stopButton;
    private Timer timer;

    public Room() {
        // Window setup
        setTitle("Temperature Monitoring System");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Dark theme background
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridLayout(5, 1, 10, 10));

        // Components (no custom font)
        statusLabel = new JLabel("DHT22 Sensor", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);

        emailLabel = new JLabel("📧 Email Alert: --", SwingConstants.CENTER);
        emailLabel.setForeground(new Color(255, 165, 0));

        smsLabel = new JLabel("📱 SMS Alert: --", SwingConstants.CENTER);
        smsLabel.setForeground(new Color(0, 200, 200));

        // Buttons
        startButton = new JButton("Start Monitoring");
        stopButton = new JButton("Stop Monitoring");
        stopButton.setEnabled(false);

        styleButton(startButton, new Color(0, 153, 76)); // green
        styleButton(stopButton, new Color(204, 0, 0));   // red

        // Add components
        add(statusLabel);
        add(emailLabel);
        add(smsLabel);
        add(startButton);
        add(stopButton);

        // Timer: runs every 5 seconds
        timer = new Timer(5000, e -> {
            double temp = generateTemperature();

            if (temp >= 30.0) {
                statusLabel.setText(
                    "DHT22 Sensor  TEMP: " + String.format("%.2f", temp) + "°C  STATUS: HIGH"
                );
                sendEmailAlert(temp);
                sendSmsAlert(temp);
            } else {
                statusLabel.setText(
                    "DHT22 Sensor  TEMP: " + String.format("%.2f", temp) + "°C  STATUS: NORMAL"
                );
                emailLabel.setText("📧 Email Alert: Temperature is SAFE (" 
                        + String.format("%.2f", temp) + "°C)");
                smsLabel.setText("📱 SMS Alert: Temperature is SAFE (" 
                        + String.format("%.2f", temp) + "°C)");
            }
        });

        // Start button
        startButton.addActionListener(e -> {
            timer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        // Stop button
        stopButton.addActionListener(e -> {
            timer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            statusLabel.setText("DHT22 Sensor - Monitoring stopped");
            emailLabel.setText("📧 Email Alert: --");
            smsLabel.setText("📱 SMS Alert: --");
        });
    }

    // Button styling (removed font customization)
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }

    // Generate random temperature (20–60°C)
    private double generateTemperature() {
        return 20.0 + Math.random() * 40.0;
    }

    // Show email alert
    private void sendEmailAlert(double temp) {
        emailLabel.setText("📧 Email Alert: Warning High Temperature! (" 
                + String.format("%.2f", temp) + "°C)");
    }

    // Show SMS alert
    private void sendSmsAlert(double temp) {
        smsLabel.setText("📱 SMS Alert: Warning High Temperature! (" 
                + String.format("%.2f", temp) + "°C)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Room().setVisible(true));
    }
}
