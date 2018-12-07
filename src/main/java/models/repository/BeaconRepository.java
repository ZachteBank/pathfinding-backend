package models.repository;

import dbal.repository.AbstractRepository;
import models.entities.Beacon;
import models.entities.Device;

public class BeaconRepository extends AbstractRepository<Beacon, Integer> {
    public Class<Beacon> getDomainClass() {
        return Beacon.class;
    }
}
