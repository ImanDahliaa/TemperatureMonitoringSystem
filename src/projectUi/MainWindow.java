package projectUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import projectOperation.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class MainWindow extends JFrame {

    private JLabel statusLabel, emailLabel, smsLabel, thresholdValueLabel;
    private JButton startButton, stopButton, clearLogButton;
    private JSlider thresholdSlider;
    private JTextArea logArea;
    private Timer timer;
    private TemperatureSensor sensor;

    public MainWindow() {
        // Apply FlatLaf Dark Theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.background", new Color(60, 60, 60));
            UIManager.put("Panel.background", new Color(30, 30, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sensor = new TemperatureSensor("DHT22 Sensor");

        setTitle("Temperature Monitoring System");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Sensor status label
        gbc.gridy = 0;
        statusLabel = new JLabel("DHT22 Sensor", SwingConstants.CENTER);
        add(statusLabel, gbc);

        // Email alert label
        gbc.gridy++;
        emailLabel = new JLabel("📧 Email Alert: --", SwingConstants.CENTER);
        emailLabel.setForeground(new Color(255, 165, 0));
        add(emailLabel, gbc);

        // SMS alert label
        gbc.gridy++;
        smsLabel = new JLabel("📱 SMS Alert: --", SwingConstants.CENTER);
        smsLabel.setForeground(new Color(0, 200, 200));
        add(smsLabel, gbc);

        // Threshold slider (30–60°C)
        gbc.gridy++;
        thresholdSlider = new JSlider(30, 45, 30);
        thresholdSlider.setMajorTickSpacing(5);
        thresholdSlider.setPaintTicks(true);
        thresholdSlider.setPaintLabels(true);
        thresholdSlider.setForeground(Color.WHITE);
        thresholdSlider.setBackground(new Color(30, 30, 30));
        thresholdSlider.setBorder(BorderFactory.createTitledBorder("Alert Threshold (°C)"));
        add(thresholdSlider, gbc);

        // Threshold value label
        gbc.gridy++;
        thresholdValueLabel = new JLabel("Current Threshold: 30°C", SwingConstants.CENTER);
        thresholdValueLabel.setForeground(Color.WHITE);
        add(thresholdValueLabel, gbc);

        // Update label when slider moves
        thresholdSlider.addChangeListener(e -> {
            thresholdValueLabel.setText("Current Threshold: " + thresholdSlider.getValue() + "°C");
        });

        // Start button
        gbc.gridy++;
        startButton = new JButton("Start Monitoring");
        styleButton(startButton, new Color(0, 153, 76));
        add(startButton, gbc);

        // Stop button
        gbc.gridy++;
        stopButton = new JButton("Stop Monitoring");
        styleButton(stopButton, new Color(204, 0, 0));
        stopButton.setEnabled(false);
        add(stopButton, gbc);

        // Clear Log button
        gbc.gridy++;
        clearLogButton = new JButton("Clear Log");
        styleButton(clearLogButton, new Color(100, 100, 100));
        add(clearLogButton, gbc);

        // Log area
        gbc.gridy++;
        logArea = new JTextArea(8, 40);
        logArea.setEditable(false);
        logArea.setBackground(new Color(20, 20, 20));
        logArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, gbc);

        // Timer: updates every 5 seconds
        timer = new Timer(5000, e -> {
            double temp = 20 + Math.random() * 40;
            sensor.setTemperature(temp);

            double threshold = thresholdSlider.getValue();
            String formattedTemp = String.format("%.2f", temp);
            String statusText;
            String emailText;
            String smsText;

            if (temp >= threshold) {
                statusText = sensor.getSensorid() + " TEMP: " + formattedTemp + "°C STATUS: HIGH";
                emailText = "📧 Email Alert: Warning High Temperature! (" + formattedTemp + "°C)";
                smsText = "📱 SMS Alert: Warning High Temperature! (" + formattedTemp + "°C)";
            } else {
                statusText = sensor.getSensorid() + " TEMP: " + formattedTemp + "°C STATUS: NORMAL";
                emailText = "📧 Email Alert: Temperature is SAFE (" + formattedTemp + "°C)";
                smsText = "📱 SMS Alert: Temperature is SAFE (" + formattedTemp + "°C)";
            }

            statusLabel.setText(statusText);
            emailLabel.setText(emailText);
            smsLabel.setText(smsText);

            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            logArea.append("[" + time + "] " + statusText + "\n");
        });

        // Start button action
        startButton.addActionListener(e -> {
            timer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        // Stop button action
        stopButton.addActionListener(e -> {
            timer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            statusLabel.setText(sensor.getSensorid() + " - Monitoring stopped");
            emailLabel.setText("📧 Email Alert: --");
            smsLabel.setText("📱 SMS Alert: --");
            logArea.append("[STOPPED] Monitoring stopped\n");
        });

        // Clear log action
        clearLogButton.addActionListener(e -> {
            logArea.setText("");
        });

        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}