//EmailAlert
package projectOperation;

public class EmailAlert extends AlertSystem {
    @Override
    public String displayAlert(TemperatureSensor sensor) {
        double temp = sensor.getTemperature();
        if (temp > 40) {
            return "EMAIL ALERT: High temperature detected (" + temp + "°C)";
        } else {
            return "EMAIL ALERT: Temperature is safe (" + temp + "°C)";
        }
    }
}
