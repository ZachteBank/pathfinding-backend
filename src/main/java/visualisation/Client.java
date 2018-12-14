package visualisation;

import models.entities.Beacon;
import models.entities.Device;
import models.entities.DeviceResult;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client extends PApplet {

    private Map<Integer, Beacon> beacons;
    private Map<String, Integer> devices;

    public void settings() {
        size(750, 750, P2D);
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

    private void getDuplicates(Map<Integer, Beacon> beacons) {
        //return beacons;

        devices = new ConcurrentHashMap<>();
        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            for (DeviceResult deviceResult : beaconEntry.getValue().getDevices()) {
                if (devices.containsKey(deviceResult.getDevice().getMacAdress())) {
                    Integer amount = devices.get(deviceResult.getDevice().getMacAdress()) + 1;
                    devices.put(deviceResult.getDevice().getMacAdress(), amount);
                } else {
                    devices.put(deviceResult.getDevice().getMacAdress(), 1);
                }
            }
        }
    }

    public void mouseDragged() {
        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            Beacon beacon = beaconEntry.getValue();
            if (mouseX > beacon.getX() - 30 && mouseX < beacon.getX() + 30 &&
                    mouseY > beacon.getY() - 30 && mouseY < beacon.getY() + 30) {
                beacon.setX(mouseX);
                beacon.setY(mouseY);
                break;
            }
        }
    }

    private void drawLegend(Map<String, DeviceResult> deviceResults){
        fill(255, 255, 255);
        textSize(10);

        int i = 0;
        fill(255, 255, 255);
        textSize(10);

        for (Map.Entry<String, DeviceResult> entry : deviceResults.entrySet()) {
            i++;
            fill(255, 255, 255);
            text(entry.getValue().getDevice().getMacAdress(), 30, 20*i);

            fill(entry.getValue().getDevice().getColor().getRed(), entry.getValue().getDevice().getColor().getGreen(), entry.getValue().getDevice().getColor().getBlue());
            rect(15,(20*i)-5, 10, 10);
        }

    }

    public void draw() {
        getDuplicates(beacons);
        background(0);

        Map<String, DeviceResult> drawedDevices = new HashMap<>();

        for (Map.Entry<Integer, Beacon> beaconEntry : beacons.entrySet()) {
            for (DeviceResult deviceResult : beaconEntry.getValue().getDevices()) {
                if (deviceResult.getDevice().getMacAdress().equals("C8:21:58:9E:DC:FB") || devices.containsKey(deviceResult.getDevice().getMacAdress()) && devices.get(deviceResult.getDevice().getMacAdress()) > 5) {
                    int size = deviceResult.getStrength() < 0 ? -deviceResult.getStrength() : deviceResult.getStrength();
                    size = (size - 30) * 11;
                    stroke(deviceResult.getDevice().getColor().getRed(), deviceResult.getDevice().getColor().getGreen(), deviceResult.getDevice().getColor().getBlue(), 100);
                    strokeWeight(4);
                    fill(0, 0, 0, 0);
                    ellipse(beaconEntry.getValue().getX(), beaconEntry.getValue().getY(), size, size);

                    drawedDevices.put(deviceResult.getDevice().getMacAdress(), deviceResult);
                }
            }
            strokeWeight(0);
            fill(255, 0, 0);
            rect(beaconEntry.getValue().getX(), beaconEntry.getValue().getY(), beaconEntry.getValue().getWidth(), beaconEntry.getValue().getHeight());

            fill(255, 255, 255);
            textSize(14);
            text(beaconEntry.getKey(), beaconEntry.getValue().getX(), beaconEntry.getValue().getY() + 20);
        }

        drawLegend(drawedDevices);
    }
}
