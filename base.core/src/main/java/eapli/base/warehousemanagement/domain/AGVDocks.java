package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import javax.persistence.*;

public class AGVDocks {

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


    @Override
    public String toString() {
        return "\nAGVDock: \n" +
                begin + "\n" +
                end + "\n" +
                depth + "\n" +
                "accessibility=" + accessibility;
    }
}
