package eapli.base.agv.domain;

import eapli.framework.domain.model.ValueObject;

public enum AGVState implements ValueObject {
    INACTIVE,
    FREE,
    CHARGING,
    OCCUPIED_SERVING_A_GIVEN_ORDER;
}
