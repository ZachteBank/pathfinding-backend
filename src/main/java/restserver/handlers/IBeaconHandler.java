package restserver.handlers;

import models.entities.Beacon;
import models.entities.Device;
import restserver.response.Reply;

import java.util.List;
import java.util.Map;

public interface IBeaconHandler extends IHandler<Beacon> {
    Reply addDevices(Beacon beacon);

    Map<Integer, Beacon> getBeacons();
}
