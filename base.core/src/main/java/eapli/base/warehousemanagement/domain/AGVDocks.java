package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.agv.domain.AGV;

import javax.persistence.*;

public class AGVDocks {

    @SerializedName("id")
    private int id;

    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;

    @SerializedName("begin")
    private Begin begin;

    @SerializedName("end")
    private End end;

    @SerializedName("depth")
    private Depth depth;

    @SerializedName("accessibility")
    private String accessibility;

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nAGVDock: \n" +
                begin + "\n" +
                end + "\n" +
                depth + "\n" +
                "accessibility=" + accessibility;
    }
}
