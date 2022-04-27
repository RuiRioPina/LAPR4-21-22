package eapli.base.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Price implements ValueObject {

    private Double priceWoTaxes;

    private Double priceWiTaxes;

    public Price(Double priceWoTaxes, Double priceWiTaxes) {
        this.priceWoTaxes = priceWoTaxes;
        this.priceWiTaxes = priceWiTaxes;
    }

    public Price() {

    }

    public Double priceWithTaxes(){
        return this.priceWiTaxes;
    }

    public Double priceWithoutTaxes(){
        return this.priceWoTaxes;
    }

}
