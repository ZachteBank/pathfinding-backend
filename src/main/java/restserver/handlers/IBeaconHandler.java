package restserver.handlers;

import models.entities.Beacon;
import models.entities.Device;
import models.json.AddDevicesJson;
import restserver.response.Reply;

import java.util.List;
import java.util.Map;

public interface IBeaconHandler extends IHandler<Beacon> {
    Reply addDevices(Beacon beacon);
    Reply addDevices(AddDevicesJson devicesJson);

    Map<Integer, Beacon> getBeacons();
}
