package eapli.base.clientusermanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.FormattedString;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;


@Embeddable
public class Vat implements Comparable<Vat>, Serializable, ValueObject, FormattedString {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private String vatId;

    protected Vat() {
        this.vatId = null;
    }

    public Vat(final String vatId) {
        setVat(vatId);
    }

    public static Vat valueOf(String vatId) {
       return new Vat(vatId);
    }


    public String vatId() {
        return vatId;
    }


    public int compareTo(final Vat arg) {
        return this.vatId.compareTo(arg.vatId);
    }

    private static boolean vatMeetsMinimumRequirements(final String vat) {
        return !StringPredicates.isNullOrEmpty(vat);
    }

    /**
     * Sets and validates vat.
     *
     * @param vat
     *            The new Vat id.
     */
    private void setVat(final String vat) {
        if (vatMeetsMinimumRequirements(vat)) {
            this.vatId = vat;
        } else {
            throw new IllegalArgumentException("Invalid Vat");
        }
    }


    public boolean isGreaterThan(final Vat arg) {
        return this.compareTo(arg) > 0;
    }

    public boolean isLessThan(final Vat arg) {
        return this.compareTo(arg) < 0;
    }

    public boolean isGreaterThanOrEqual(final Vat arg) {
        return this.compareTo(arg) >= 0;
    }

    public boolean isLessThanOrEqual(final Vat arg) {
        return this.compareTo(arg) <= 0;
    }

    public boolean equals(final Object arg) {
        if (!(arg instanceof Vat)) {
            return false;
        } else {
            Vat other = (Vat) arg;
            return this.vatId.equals(other.vatId);
        }
    }

    public int hashCode() {
        int result = 11;
        result = 37 * result + this.vatId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vat{" + "amount=" + vatId +
                '}';
    }

}
