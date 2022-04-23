package eapli.base.clientusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.apache.commons.lang3.NotImplementedException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PhoneNumber implements ValueObject, Serializable {
    private int prefix;
    private int number;

    public PhoneNumber(int prefix, int number) {
        this.prefix = prefix;
        this.number = number;
    }

    public PhoneNumber() {

    }

    public static PhoneNumber valueOf(String prefix, String phoneNumber) {
        return new PhoneNumber(Integer.parseInt(prefix), Integer.parseInt(phoneNumber));
    }
}
