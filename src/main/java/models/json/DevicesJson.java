package models.json;

import models.entities.Device;

import java.util.List;

public class DevicesJson {
    private String mac;
    private int strength;

    public DevicesJson() {
    }

    public DevicesJson(String mac, int strength) {
        this.mac = mac;
        this.strength = strength;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
