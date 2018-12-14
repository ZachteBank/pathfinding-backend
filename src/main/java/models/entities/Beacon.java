package models.entities;

import models.BaseEntity;

import java.util.List;

public class Beacon implements BaseEntity {
    private int id;

    private List<DeviceResult> devices;

    private int width = 3;
    private int height = 3;

    private int x;
    private int y;

    public Beacon() {
    }

    public Beacon(List<DeviceResult> devices) {
        this.devices = devices;
    }

    public List<DeviceResult> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceResult> devices) {
        this.devices = devices;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
