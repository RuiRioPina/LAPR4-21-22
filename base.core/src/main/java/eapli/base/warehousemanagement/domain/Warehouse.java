package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.agv.domain.AGV;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.Serializable;
import java.util.List;

public class Warehouse implements AggregateRoot<Long>, Serializable {

    private String jsonPath;

    @SerializedName("Warehouse")
    private String name;

    @SerializedName("Length")
    private Double length;


    @SerializedName("Width")
    private Double width;

    @SerializedName("Square")
    private Double square;

    @SerializedName("Unit")
    private String unit;

    @SerializedName("Aisles")
    private List<Aisle> aisle;

    @SerializedName("WarehouseEmployees")
    private List<SystemUser> lWarehouseEmployee;

    @SerializedName("AGVs")
    private List<AGV> agvs;

    @SerializedName("AGVDocks")
    private List<AGVDocks> agvDocks;



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

    public void setJsonPath(String jsonPath) {
        this.jsonPath = "/warehouse/" + jsonPath;
    }

    @Override
    public String toString() {
        return "Warehouse:\n" +
                "jsonpath=" + jsonPath + "\n" +
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
