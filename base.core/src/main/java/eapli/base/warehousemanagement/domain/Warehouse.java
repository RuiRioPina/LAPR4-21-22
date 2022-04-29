package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.agv.domain.AGV;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Warehouse implements AggregateRoot<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    @Column
    @SerializedName("Warehouse")
    private String name;

    @SerializedName("Length")
    @Column(name = "length", nullable = false)
    private Double length;


    @SerializedName("Width")
    @Column(name = "width", nullable = false)
    private Double width;

    @SerializedName("Square")
    @Column(name = "square", nullable = false)
    private Double square;

    @SerializedName("Unit")
    @Column(name = "unit", nullable = false)
    private String unit;

    @OneToMany
    @SerializedName("Aisles")
    private List<Aisle> aisle;

    @OneToMany
    @SerializedName("WarehouseEmployees")
    private List<SystemUser> lWarehouseEmployee;

    @Transient
    @SerializedName("AGVs")
    private List<AGV> agvs;

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
        return "Warehouse:\n" +
                "name='" + name + "\n" +
                "length=" + length + "\n" +
                "width=" + width + "\n" +
                "square=" + square + "\n" +
                "unit=" + unit + "\n" +
                "-----------------------\n" +
                "Aisles:" + aisle + "\n" +
                "-----------------------\n" +
                "AGV Dock:" + agvDocks + "\n" +
                "-----------------------\n" +
                "AGV:" + agvs +"\n";

    }
}
