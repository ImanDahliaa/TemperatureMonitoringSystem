//SmsAlert
package projectOperation;

public class SmsAlert extends AlertSystem {
    @Override
    public String displayAlert(TemperatureSensor sensor) {
        double temp = sensor.getTemperature();
        if (temp > 40) {
            return "SMS ALERT: High temperature detected (" + temp + "°C)";
        } else {
            return "SMS ALERT: Temperature is safe (" + temp + "°C)";
        }
    }
}
