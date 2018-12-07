package restserver.handlers;

import dbal.repository.IRepository;
import models.entities.Device;
import restserver.response.Reply;

import java.util.List;

public class DeviceHandler extends AbstractHandler<Device> implements IDeviceHandler {
    public DeviceHandler(IRepository repo) {
        super(repo);
    }

    @Override
    public Reply addDevices(List<Device> devices) {
        return null;
    }
}
