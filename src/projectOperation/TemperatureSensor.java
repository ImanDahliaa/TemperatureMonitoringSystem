//TemperatureSensor
package projectOperation;

public class TemperatureSensor {
    private String sensorid;
    private double temperature;

    public TemperatureSensor(String sensorid) {
        this.sensorid = sensorid;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
