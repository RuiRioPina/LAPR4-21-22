package eapli.base.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

public enum Payment implements ValueObject, Serializable {
    PAYPAL;
}
