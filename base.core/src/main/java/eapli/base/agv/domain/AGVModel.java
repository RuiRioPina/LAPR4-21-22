package eapli.base.agv.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class AGVModel implements ValueObject {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
