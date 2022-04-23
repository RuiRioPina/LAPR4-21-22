package eapli.base.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Brand implements ValueObject {

    private String brandName;

    public Brand(String brandName) {
        if (!brandName.isEmpty()) {
            this.brandName = brandName;
        }
    }

    public Brand() {
    }
}
