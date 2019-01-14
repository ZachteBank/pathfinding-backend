package models.entities;

import models.BaseEntity;

import java.awt.*;

public class Device implements BaseEntity {
    private String macAdress;
    private Color color;

    @Override
    public int getId() {
        return 0;
    }

    public Device() {
    }

    public Device(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress.toUpperCase();
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
