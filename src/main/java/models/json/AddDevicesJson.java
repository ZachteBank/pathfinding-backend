package models.json;

import models.entities.Device;

import java.util.List;

public class AddDevicesJson {
    private DevicesJson[] devices;
    private Integer beacon;

    public AddDevicesJson() {
    }

    public AddDevicesJson(DevicesJson[] devices, Integer beacon) {
        this.devices = devices;
        this.beacon = beacon;
    }

    public DevicesJson[] getDevices() {
        return devices;
    }

    public void setDevices(DevicesJson[] devices) {
        this.devices = devices;
    }

    public Integer getBeacon() {
        return beacon;
    }

    public void setBeacon(Integer beacon) {
        this.beacon = beacon;
    }
}
