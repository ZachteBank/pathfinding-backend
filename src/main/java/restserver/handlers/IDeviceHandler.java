package restserver.handlers;

import models.entities.Device;
import restserver.response.Reply;

import java.util.List;

public interface IDeviceHandler extends IHandler<Device> {
    Reply addDevices(List<Device> devices);
}
