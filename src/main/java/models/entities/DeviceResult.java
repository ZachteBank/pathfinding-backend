package models.entities;

public class DeviceResult {
    private Device device;
    private int strength;

    public DeviceResult() {
    }

    public DeviceResult(Device device, int strength) {
        this.device = device;
        this.strength = strength;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
