package eapli.base.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Barcode implements ValueObject {

    private String barcode;

    public Barcode(String barcode) {
        if (!barcode.isEmpty()) {
            this.barcode = barcode;
        }
    }

    public Barcode() {}
}
