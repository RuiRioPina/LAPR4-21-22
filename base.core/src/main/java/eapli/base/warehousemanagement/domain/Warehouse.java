package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @SerializedName("Warehouse")
    private String name;
    @SerializedName("Length")
    private double length;
    @SerializedName("Width")
    private double width;
    @SerializedName("Square")
    private double square;
    @SerializedName("Unit")
    private String unit;
    @SerializedName("Aisles")
    private List<Aisle> aisle;

    public Long getId() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Warehouse{");
        sb.append("name=").append(name);
        sb.append(", length=").append(length);
        sb.append(", width=").append(width);
        sb.append(", square=").append(square);
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", aisle=").append(aisle);
        sb.append('}');
        return sb.toString();
    }
}
