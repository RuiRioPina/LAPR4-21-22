package eapli.base.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

public enum Shipment implements ValueObject, Serializable {
    GREEN,
    BLUE,
    STANDARD;
}
