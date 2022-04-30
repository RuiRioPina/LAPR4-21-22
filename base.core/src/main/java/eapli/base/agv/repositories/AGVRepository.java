package eapli.base.agv.repositories;

import eapli.base.agv.domain.AGV;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<Long, AGV> {
    Iterable<AGV> findAllActive();
}
