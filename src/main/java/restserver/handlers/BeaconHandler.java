package restserver.handlers;

import dbal.repository.IRepository;
import models.entities.Beacon;
import models.entities.Device;
import models.entities.DeviceResult;
import models.json.AddDevicesJson;
import models.json.DevicesJson;
import restserver.response.Reply;
import restserver.response.Status;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeaconHandler extends AbstractHandler<Beacon> implements IBeaconHandler {
    public BeaconHandler(IRepository repo) {
        super(repo);
    }

    private Map<Integer, Beacon> beacons = new ConcurrentHashMap<>();
    private List<Device> devices = new ArrayList<>();
    private Random r = new Random();

    public Reply addDevices(AddDevicesJson devicesJson){
        Beacon beacon = new Beacon();
        List<DeviceResult> deviceResults = new ArrayList<>();

        for (DevicesJson device : devicesJson.getDevices()) {
            DeviceResult deviceResult = new DeviceResult();
            Device tmpDevice = addDeviceToList(device.getMac());

            deviceResult.setDevice(tmpDevice);
            deviceResult.setStrength(device.getStrength());

            deviceResults.add(deviceResult);
        }

        beacon.setDevices(deviceResults);
        beacon.setId(devicesJson.getBeacon());

        return addDevices(beacon);
    }

    private Device addDeviceToList(String address){
        for (Device device : devices) {
            if(device.getMacAdress().equals(address)){
                return device;
            }
        }
        Device device = new Device(address);

        device.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        devices.add(device);
        return device;
    }

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
        beacons.put(beacon.getId(), beacon);

        return new Reply(Status.OK, "success");
    }

    @Override
    public Map<Integer, Beacon> getBeacons() {
        return beacons;
    }
}
