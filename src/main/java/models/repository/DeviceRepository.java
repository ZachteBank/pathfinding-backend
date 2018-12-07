package models.repository;

import dbal.repository.AbstractRepository;
import models.entities.Device;

public class DeviceRepository extends AbstractRepository<Device, Integer> {
    public Class<Device> getDomainClass() {
        return Device.class;
    }
}
