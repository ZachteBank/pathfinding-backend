package visualisation;
import models.entities.Beacon;
import models.entities.Device;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client extends PApplet {

    private Map<Integer, Beacon> beacons;
    private Map<String, Integer> devices;

    public void settings() {
        size(500, 500, P2D);
    }

    public Client(Map<Integer, Beacon> beacons) {
        this.beacons = beacons;
    }

    public void setup() {
        //surface.setResizable(true);
        frameRate(10);
        rectMode(CENTER);
        ellipseMode(CENTER);
    }

    private void getDuplicates(Map<Integer, Beacon> beacons){
        //return beacons;

        devices = new ConcurrentHashMap<>();
        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            for (Device device : beaconEntry.getValue().getDevices()) {
                if(devices.containsKey(device.getMacAdress())){
                    Integer amount = devices.get(device.getMacAdress()) + 1;
                    devices.put(device.getMacAdress(), amount);
                }
                else{
                    devices.put(device.getMacAdress(), 1);
                }
            }
        }
    }

    public void mouseDragged() {
        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            Beacon beacon = beaconEntry.getValue();
            if(mouseX > beacon.getX()-30 && mouseX < beacon.getX()+30 &&
                mouseY > beacon.getY()-30 && mouseY < beacon.getY()+30){
                beacon.setX(mouseX);
                beacon.setY(mouseY);
                break;
            }
        }
    }

    public void draw() {
        getDuplicates(beacons);
        background(0);

        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            for (Device device : beaconEntry.getValue().getDevices()) {
                if(devices.containsKey(device.getMacAdress()) && devices.get(device.getMacAdress()) > 4) {
                    int size = device.getStrength() < 0 ? -device.getStrength() : device.getStrength();
                    size = (size - 50) * 11;
                    stroke(device.getColor().getRed(), device.getColor().getGreen(), device.getColor().getBlue(), 100);
                    strokeWeight(4);
                    fill(0, 0, 0, 0);
                    ellipse(beaconEntry.getValue().getX(), beaconEntry.getValue().getY(), size, size);
                }
            }

            fill(255,0,0);
            rect(beaconEntry.getValue().getX(),beaconEntry.getValue().getY(),beaconEntry.getValue().getWidth(),beaconEntry.getValue().getHeight());

            fill(255,255,255);
            textSize(14);
            text(beaconEntry.getKey(), beaconEntry.getValue().getX(),beaconEntry.getValue().getY()+20);
        }

    }
}
