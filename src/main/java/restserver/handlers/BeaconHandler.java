package restserver.handlers;

import dbal.repository.IRepository;
import models.entities.Beacon;
import models.entities.Device;
import restserver.response.Reply;
import restserver.response.Status;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BeaconHandler extends AbstractHandler<Beacon> implements IBeaconHandler {
    public BeaconHandler(IRepository repo) {
        super(repo);
    }

    private Map<Integer, Beacon> beacons = new ConcurrentHashMap<>();

    @Override
    public Reply addDevices(Beacon beacon) {
        switch (beacon.getId()){
            case 1:
                beacon.setX(40);
                beacon.setY(40);
                break;
            case 3:
                beacon.setX(420);
                beacon.setY(40);
                break;
            case 2:
                beacon.setX(420);
                beacon.setY(420);
                break;
            case 4:
                beacon.setX(165);
                beacon.setY(354);
                break;
            case 5:
                beacon.setX(432);
                beacon.setY(276);
                break;
        }
        for (Device device : beacon.getDevices()) {
            Random r = new Random();
            device.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        }
        beacons.put(beacon.getId(), beacon);

        return new Reply(Status.OK, "success");
    }

    @Override
    public Map<Integer, Beacon> getBeacons() {
        return beacons;
    }
}
