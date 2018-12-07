package models.entities;

import models.BaseEntity;

import java.awt.*;

public class Device implements BaseEntity {
    private String macAdress;
    private int strength;

    private Color color;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
