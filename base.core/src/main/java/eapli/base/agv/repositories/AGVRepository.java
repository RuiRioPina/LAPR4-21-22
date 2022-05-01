package eapli.base.agv.repositories;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.DockingPoint;
import eapli.base.product.domain.StorageArea;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<Long, AGV> {
    Iterable<AGV> findAllActive();
    boolean validateAgvBaseLocation (String dockingPoint);
}
