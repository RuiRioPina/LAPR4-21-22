package eapli.base.agv.repositories;

import eapli.base.agv.domain.AGV;

public interface AGVRepository {
    Iterable<AGV> findAllActive();
}
