package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.agv.domain.AGV;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Warehouse implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    @Column
    @SerializedName("Warehouse")
    private String name;

    @Column
    @SerializedName("Length")
    private Double length;

    @Column
    @SerializedName("Width")
    private Double width;

    @Column
    @SerializedName("Square")
    private Double square;

    @Column
    @SerializedName("Unit")
    private String unit;

    @OneToMany
    @SerializedName("Aisles")
    private List<Aisle> aisle;

    @OneToMany
    @SerializedName("WarehouseEmployees")
    private List<SystemUser> lWarehouseEmployee;

    @OneToMany
    @SerializedName("AGVs")
    private List<AGV> lAGV;

    @Transient
    @SerializedName("AGVDocks")
    private List<AGVDocks> agvDocks;

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

    public List<Aisle> aisles() {
        return aisle;
    }

    public List<SystemUser> getlWarehouseEmployee(){
        return this.lWarehouseEmployee;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Warehouse{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", length=").append(length);
        sb.append(", width=").append(width);
        sb.append(", square=").append(square);
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", aisle=").append(aisle);
        sb.append(", agvDocks=").append(agvDocks);
        sb.append('}');
        return sb.toString();
    }
}
