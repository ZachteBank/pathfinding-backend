package models.entities;

import models.BaseEntity;

public class Device implements BaseEntity {
    private String macAdress;
    private int strength;

    @Override
    public int getId() {
        return 0;
    }

    public Device() {
    }

    public Device(String macAdress, int strength) {
        this.macAdress = macAdress;
        this.strength = strength;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
